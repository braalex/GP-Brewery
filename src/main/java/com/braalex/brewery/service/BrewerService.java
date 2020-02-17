package com.braalex.brewery.service;

import com.braalex.brewery.dto.StaffDto;
import com.braalex.brewery.dto.UserSignInRequestDto;
import com.braalex.brewery.dto.UserSignInResponseDto;
import com.braalex.brewery.entity.AuthInfoEntity;
import com.braalex.brewery.entity.UserEntity;
import com.braalex.brewery.exception.SuchUserAlreadyExistException;
import com.braalex.brewery.mapper.StaffMapper;
import com.braalex.brewery.repository.AuthInfoRepository;
import com.braalex.brewery.repository.UserRepository;
import com.braalex.brewery.security.JwtUtil;
import com.braalex.brewery.security.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BrewerService {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final StaffMapper staffMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthInfoRepository authInfoRepository;

    @Transactional
    public UserSignInResponseDto signUp(final StaffDto staffDtoRequest)
            throws SuchUserAlreadyExistException {
        if (authInfoRepository.findByLogin(staffDtoRequest.getEmail()).isPresent()) {
            throw new SuchUserAlreadyExistException("User with email=" + staffDtoRequest.getEmail() + " already exists");
        }
        saveUser(staffDtoRequest);
        return signIn(new UserSignInRequestDto(staffDtoRequest.getEmail(), staffDtoRequest.getPassword()));
    }

    private void saveUser(final StaffDto staffDtoRequest) {
        final UserEntity userEntity = staffMapper.sourceToDestination(staffDtoRequest);
        userEntity.setUserRole(UserRole.BREWER);
        final UserEntity savedUser = userRepository.save(userEntity);
        saveAuthInfo(staffDtoRequest, savedUser);
    }

    private void saveAuthInfo(final StaffDto staffDtoRequest, final UserEntity savedUser) {
        final AuthInfoEntity authInfoEntity = new AuthInfoEntity();
        authInfoEntity.setLogin(staffDtoRequest.getEmail());
        authInfoEntity.setPassword(passwordEncoder.encode(staffDtoRequest.getPassword()));
        authInfoEntity.setUser(savedUser);
        authInfoRepository.save(authInfoEntity);
    }

    public UserSignInResponseDto signIn(final UserSignInRequestDto userSignInRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userSignInRequest.getEmail(), userSignInRequest.getPassword()));
        return new UserSignInResponseDto(jwtUtil.generateToken(
                new User(userSignInRequest.getEmail(),
                        userSignInRequest.getPassword(),
                        List.of(new SimpleGrantedAuthority("BREWER")))));
    }
}

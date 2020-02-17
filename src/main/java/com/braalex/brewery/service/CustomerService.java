package com.braalex.brewery.service;

import com.braalex.brewery.dto.CustomerDto;
import com.braalex.brewery.dto.UserSignInRequestDto;
import com.braalex.brewery.dto.UserSignInResponseDto;
import com.braalex.brewery.entity.AuthInfoEntity;
import com.braalex.brewery.entity.UserEntity;
import com.braalex.brewery.exception.SuchUserAlreadyExistException;
import com.braalex.brewery.mapper.CustomerMapper;
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
public class CustomerService {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final CustomerMapper customerMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthInfoRepository authInfoRepository;

    @Transactional
    public UserSignInResponseDto signUp(final CustomerDto customerDtoRequest)
            throws SuchUserAlreadyExistException {
        if (authInfoRepository.findByLogin(customerDtoRequest.getEmail()).isPresent()) {
            throw new SuchUserAlreadyExistException("User with email=" + customerDtoRequest.getEmail() + " already exists");
        }
        saveUser(customerDtoRequest);
        return signIn(new UserSignInRequestDto(customerDtoRequest.getEmail(), customerDtoRequest.getPassword()));
    }

    private void saveUser(final CustomerDto customerDtoRequest) {
        final UserEntity userEntity = customerMapper.sourceToDestination(customerDtoRequest);
        userEntity.setUserRole(UserRole.CUSTOMER);
        final UserEntity savedUser = userRepository.save(userEntity);
        saveAuthInfo(customerDtoRequest, savedUser);
    }

    private void saveAuthInfo(final CustomerDto customerDtoRequest, final UserEntity savedUser) {
        final AuthInfoEntity authInfoEntity = new AuthInfoEntity();
        authInfoEntity.setLogin(customerDtoRequest.getEmail());
        authInfoEntity.setPassword(passwordEncoder.encode(customerDtoRequest.getPassword()));
        authInfoEntity.setUser(savedUser);
        authInfoRepository.save(authInfoEntity);
    }

    public UserSignInResponseDto signIn(final UserSignInRequestDto userSignInRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userSignInRequest.getEmail(), userSignInRequest.getPassword()));
        return new UserSignInResponseDto(jwtUtil.generateToken(
                new User(userSignInRequest.getEmail(),
                        userSignInRequest.getPassword(),
                        List.of(new SimpleGrantedAuthority("CUSTOMER")))));
    }
}

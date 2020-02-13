package com.braalex.brewery.service;

import com.braalex.brewery.dto.StaffDto;
import com.braalex.brewery.dto.UserSignInRequestDto;
import com.braalex.brewery.dto.UserSignInResponseDto;
import com.braalex.brewery.entity.UserEntity;
import com.braalex.brewery.exception.SuchUserAlreadyExistException;
import com.braalex.brewery.mapper.StaffMapper;
import com.braalex.brewery.repository.UserRepository;
import com.braalex.brewery.security.JwtUtil;
import com.braalex.brewery.security.UserRole;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class DirectorService {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final StaffMapper staffMapper;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserSignInResponseDto signUp(final StaffDto request)
            throws SuchUserAlreadyExistException {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new SuchUserAlreadyExistException("User with email=" + request.getEmail() + " already exists");
        }
        saveUser(request);
        return signIn(new UserSignInRequestDto(request.getEmail(), request.getPassword()));
    }

    private void saveUser(final StaffDto request) {
        final UserEntity userEntity = staffMapper.sourceToDestination(request);
        userEntity.setUserRole(UserRole.DIRECTOR);
        userEntity.setEmail(request.getEmail());
        userEntity.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(userEntity);
    }

    public UserSignInResponseDto signIn(final UserSignInRequestDto request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        return new UserSignInResponseDto(jwtUtil.generateToken(
                new User(request.getEmail(),
                        request.getPassword(),
                        List.of(new SimpleGrantedAuthority("DIRECTOR")))));
    }
}

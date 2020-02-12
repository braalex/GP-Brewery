package com.braalex.brewery.service;

import com.braalex.brewery.dto.*;
import com.braalex.brewery.exception.SuchUserAlreadyExistException;
import com.braalex.brewery.security.JwtUtil;
import com.braalex.brewery.security.LoadUserDetailService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BrewerService {
    private final LoadUserDetailService loadUserDetailService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public UserSignInResponseDto signUp(final BrewerSignUpRequestDto request)
            throws SuchUserAlreadyExistException {
        if (loadUserDetailService.loadUserByUsername(request.getEmail()) != null) {
            throw new SuchUserAlreadyExistException();
        }
        loadUserDetailService.saveUser(request.getEmail(), request.getPassword());
        return signIn(new UserSignInRequestDto(request.getEmail(), request.getPassword()));
    }

    public UserSignInResponseDto signIn(final UserSignInRequestDto request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        return new UserSignInResponseDto(jwtUtil.generateToken(
                new User(request.getEmail(),
                        request.getPassword(),
                        List.of(new SimpleGrantedAuthority("BREWER")))));
    }
}

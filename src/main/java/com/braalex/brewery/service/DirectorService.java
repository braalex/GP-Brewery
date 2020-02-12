package com.braalex.brewery.service;

import com.braalex.brewery.dto.UserSignInRequestDto;
import com.braalex.brewery.dto.UserSignInResponseDto;
import com.braalex.brewery.security.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DirectorService {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public UserSignInResponseDto signIn(final UserSignInRequestDto request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        return new UserSignInResponseDto(jwtUtil.generateToken(
                new User(request.getEmail(),
                        request.getPassword(),
                        List.of(new SimpleGrantedAuthority("DIRECTOR")))));
    }
}

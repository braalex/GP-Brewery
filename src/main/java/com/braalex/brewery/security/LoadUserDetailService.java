package com.braalex.brewery.security;

import com.braalex.brewery.entity.UserEntity;
import com.braalex.brewery.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoadUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        final Optional<UserEntity> userEntity = userRepository.findByEmail(username);
        if (userEntity.isEmpty()) {
            throw new UsernameNotFoundException("User with email: " + username + " not found");
        } else {
            final SimpleGrantedAuthority authority = new SimpleGrantedAuthority(
                    "ROLE_" + userEntity.get().getUserRole().name());
            return new User(username, userEntity.get().getPassword(), List.of(authority));
        }
    }
}

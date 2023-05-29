package com.hridoykrisna.me.BDNews.auth.service.impl;

import com.hridoykrisna.me.BDNews.auth.config.JwtService;
import com.hridoykrisna.me.BDNews.auth.repository.UserRepository;
import com.hridoykrisna.me.BDNews.auth.service.AuthService;
import com.hridoykrisna.me.BDNews.auth.user.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthorizationDTO register(RegisterDTO request) {
        var user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ADMIN)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthorizationDTO.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public AuthorizationDTO authenticate(AuthenticationDTO request) {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );

            var user = userRepository.findByEmail(request.getEmail()).orElseThrow();

            var jwtToken = jwtService.generateToken(user);

            return AuthorizationDTO.builder()
                    .token(jwtToken)
                    .build();
        } catch (Exception ex){
            return AuthorizationDTO.builder()
                    .token(ex.getMessage()+", Please Login With Correct Email & Password")
                    .build();
        }

    }
}

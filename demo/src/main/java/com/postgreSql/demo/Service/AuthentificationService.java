package com.postgreSql.demo.Service;

import com.postgreSql.demo.Controller.AuthentificationRequest;
import com.postgreSql.demo.Controller.AuthentificationResponse;
import com.postgreSql.demo.Controller.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
@RequiredArgsConstructor
public class AuthentificationService {

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;

    public AuthentificationResponse authentification(AuthentificationRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var jwtToken = jwtService.generateToken((UserDetails) authentication.getPrincipal());
        return AuthentificationResponse.builder()
                .token(jwtToken)
                .build();
    }
}

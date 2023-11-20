package com.postgreSql.demo.Service;

import com.postgreSql.demo.Controller.AuthenticationRequest;
import com.postgreSql.demo.Controller.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthentificationService {

    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    public AuthenticationResponse authentification(AuthenticationRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        String jwtToken = jwtService.generateToken((UserDetails) authentication.getPrincipal());
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}

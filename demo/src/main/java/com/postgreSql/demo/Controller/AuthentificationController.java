package com.postgreSql.demo.Controller;

import com.postgreSql.demo.Service.AuthentificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/login")
@RequiredArgsConstructor
public class AuthentificationController {
    private final AuthentificationService authentificationService ;

    // recuperation du token pour une authentification
    @PostMapping()
    public ResponseEntity<AuthenticationResponse> authentificate(
            @RequestBody AuthenticationRequest request
    ){
        return ResponseEntity.ok(authentificationService.authentification(request));

    }
}

package com.postgreSql.demo.Controller;

import com.postgreSql.demo.Service.AuthentificationService;
import com.postgreSql.demo.model.Joueurs;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/login")
@RequiredArgsConstructor
public class AuthentificationController {

    private final AuthentificationService authentificationService ;

    // recuperation du token pour une authentification
    @PostMapping("/authenticate")
    public ResponseEntity<AuthentificationResponse> authentificate(
            @RequestBody AuthentificationRequest request
    ){
        return ResponseEntity.ok(authentificationService.authentification(request));
    }
}

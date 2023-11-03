package com.postgreSql.demo.Controller;

import com.postgreSql.demo.Service.AuthentificationService;
import com.postgreSql.demo.model.Joueurs;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthentificationController {

    private final AuthentificationService authentificationService ;

    @PostMapping("/register")
    public ResponseEntity<AuthentificationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(authentificationService.register(request));
    }

    @PostMapping("/authentification")
    public ResponseEntity<AuthentificationResponse> authentificate(
            @RequestBody AuthentificationRequest request
    ){
        return ResponseEntity.ok(authentificationService.authentification(request));
    }

    @GetMapping("/allJoueur")
    public ResponseEntity<List<Joueurs>> getAllJoueurs(){
        return ResponseEntity.ok(authentificationService.getAllJoueurs());
    }
}
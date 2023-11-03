package com.postgreSql.demo.Service;

import com.postgreSql.demo.Controller.AuthentificationRequest;
import com.postgreSql.demo.Controller.AuthentificationResponse;
import com.postgreSql.demo.Controller.RegisterRequest;
import com.postgreSql.demo.Repository.JoueurRepository;
import com.postgreSql.demo.model.Joueurs;
import com.postgreSql.demo.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthentificationService {

    private final AuthenticationManager authenticationManager;
    private final JoueurRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;

    public AuthentificationResponse register(RegisterRequest request)  {
        var joueur  = Joueurs.builder()
                        .nom(request.getNom())
                        .username(request.getUsername())
                        .age(request.getAge())
                        .club(request.getClub())
                        .password(passwordEncoder.encode( request.getPassword()))
                        .role(Role.USER)
                        .build();
        repository.save(joueur);
        var jwtToken = jwtService.generateToken(joueur);
        return AuthentificationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthentificationResponse authentification(AuthentificationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var joueur = repository.findByUsername(request.getUsername()).orElseThrow();
        var jwtToken = jwtService.generateToken(joueur);
        return AuthentificationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public List<Joueurs> getAllJoueurs() {
        List<Joueurs> joueurs = new ArrayList<>();
        repository.findAll().forEach((joueur)-> {
            joueurs.add(joueur);
        });
       return joueurs;
    }

    public List<Joueurs> getsAllJoueurs(){
        List<Joueurs> joueurs = new ArrayList<>();
        repository.findAll().forEach(joueur -> {
            joueurs.add(joueur);
        });
        return joueurs;
    }
}

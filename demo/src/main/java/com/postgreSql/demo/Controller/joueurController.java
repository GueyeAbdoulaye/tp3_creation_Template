package com.postgreSql.demo.Controller;

import com.postgreSql.demo.Service.JoueurService;
import com.postgreSql.demo.model.Joueurs;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/auth/joueur")
@RequiredArgsConstructor
public class joueurController {
    @Autowired
    JoueurService  joueurService ;

    @RequestMapping(method = RequestMethod.POST  , value = "/addjoueur")
    public  void addJoueur(@RequestBody Joueurs joueurs){
        joueurService.addJoueur(joueurs);
    }

    @GetMapping
    public ResponseEntity<List<Joueurs>> getAllJoueurs(@RequestHeader("Authorization") String tokenAuth){
       List<Joueurs> joueurs = joueurService.getsAllJoueurs();
        if(tokenAuth != null && tokenAuth.startsWith("Bearer ")){
            String token = tokenAuth.substring(7);
            return ResponseEntity.ok(joueurs);
        }
            return null;
        }

    @RequestMapping(method = RequestMethod.GET , value = "/getjoueur/{id}")
    public Joueurs getJoueur (@PathVariable Long id){return joueurService.getJoueur(id);}



}

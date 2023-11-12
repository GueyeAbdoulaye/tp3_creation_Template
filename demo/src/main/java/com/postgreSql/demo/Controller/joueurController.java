package com.postgreSql.demo.Controller;

import com.postgreSql.demo.Service.JoueurService;
import com.postgreSql.demo.model.Joueurs;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/players")
@RequiredArgsConstructor
public class joueurController {
    @Autowired
    private final JoueurService  joueurService ;

    //Donner les infos de tous les joueurs
    @RequestMapping(method = RequestMethod.GET )
    public List<Joueurs> getAllPlayers(@RequestHeader("Authorization") String token){

        if(token == null || !token.startsWith("Bearer")){
            System.out.println("Token pas valide");
            throw new RuntimeException("Token pas valide");
        }
        return joueurService.getsAllJoueurs();
    }

    //Donner les infos d'un joueur en particulier
    @RequestMapping(method = RequestMethod.GET , value = "/{id}")
    public ResponseEntity<Joueurs> getJoueur (@RequestHeader("Authorization") String token ,
                                              @PathVariable Long id){

        if(token == null || !token.startsWith("Bearer")){
            throw new RuntimeException("Token pas valide");
        }
        return ResponseEntity.ok(joueurService.getJoueur(id));
    }

    // Ajouter un joueur
    @RequestMapping(method = RequestMethod.POST  , value = "/addPlayer")
    public  void addJoueur(@RequestHeader("Authorization") String token, @RequestBody Joueurs joueurs){
        if (token == null || !token.startsWith("Bearer")){
            throw new RuntimeException("Token pas valide");
        }
        joueurService.addJoueur(joueurs);
    }

    // Modifier un joueur
    @RequestMapping(method = RequestMethod.PUT , value = "/{id}")
    public void updateJoueur(@RequestHeader("Authorization") String token , @RequestBody Joueurs joueurs , @PathVariable Long id){
        if (token == null || !token.startsWith("Bearer")){
            throw new RuntimeException("Token pas valide");
        }
        joueurService.updateJoueur(joueurs , id);
    }

    // Supprimer un joueur
    @RequestMapping(method = RequestMethod.DELETE , value = "/{id}")
    public void deleteJoueur(@RequestHeader("Authorization") String token , @PathVariable Long id){
        if (token == null || !token.startsWith("Bearer")){
            throw new RuntimeException("Token pas valide");
        }
        joueurService.deleteJoueur(id);
    }
}

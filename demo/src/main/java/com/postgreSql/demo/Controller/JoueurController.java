package com.postgreSql.demo.Controller;

import com.postgreSql.demo.Service.JoueurService;
import com.postgreSql.demo.Model.Joueur;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/players")
@RequiredArgsConstructor
public class JoueurController {
    @Autowired
    private final JoueurService  joueurService ;

    //Donner les infos de tous les joueurs
    @RequestMapping(method = RequestMethod.GET )
    public List<Joueur> getAllPlayers(){
        return joueurService.getsAllJoueurs();
    }

    //Donner les infos d'un joueur en particulier
    @RequestMapping(method = RequestMethod.GET , value = "/{id}")
    public Joueur getJoueur (@PathVariable Long id){

        return joueurService.getJoueur(id);
    }

    // Ajouter un joueur
    @RequestMapping(method = RequestMethod.POST)
    public  void addJoueur(@RequestBody Joueur joueur){
        joueurService.addJoueur(joueur);
    }

    // Modifier un joueur
    @RequestMapping(method = RequestMethod.PUT , value = "/{id}")
    public void updateJoueur(@RequestBody Joueur joueur, @PathVariable Long id){
        joueurService.updateJoueur(joueur, id);
    }

    // Supprimer un joueur
    @RequestMapping(method = RequestMethod.DELETE , value = "/{id}")
    public void deleteJoueur(@PathVariable Long id){
        joueurService.deleteJoueur(id);
    }
}

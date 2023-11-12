package com.postgreSql.demo.Service;

import com.postgreSql.demo.Repository.JoueurRepository;
import com.postgreSql.demo.model.Joueur;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JoueurService {

    private JoueurRepository joueurRepository;

    @Autowired
    public JoueurService(JoueurRepository joueurRepository) {
        this.joueurRepository = joueurRepository;
    }

    public List<Joueur> getsAllJoueurs(){
        List<Joueur> joueurs = new ArrayList<>();
        joueurRepository.findAll().forEach(joueur -> {
            joueurs.add(joueur);
        });
        return joueurs;
    }

    public Joueur getJoueur(Long id){
        return joueurRepository.findById(id).orElse(null);
    }

    public void deleteJoueur(Long id){
        joueurRepository.deleteById(id);
    }

    public void addJoueur(Joueur joueur) {
        joueurRepository.save(joueur);
    }

    public void updateJoueur(Joueur joueur, Long id) {
        joueurRepository.save(joueur);
    }

}

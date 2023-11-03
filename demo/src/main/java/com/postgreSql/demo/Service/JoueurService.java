package com.postgreSql.demo.Service;

import com.postgreSql.demo.Repository.JoueurRepository;
import com.postgreSql.demo.model.Joueurs;
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

    public List<Joueurs> getsAllJoueurs(){
        List<Joueurs> joueurs = new ArrayList<>();
        joueurRepository.findAll().forEach(joueur -> {
            joueurs.add(joueur);
        });
        return joueurs;
    }

    public Joueurs getJoueur(Long id){
        return joueurRepository.findById(id).orElse(null);
    }

    public void deleteJoueur(Long id){
        joueurRepository.deleteById(id);
    }

    public void addJoueur(Joueurs joueurs) {
        joueurRepository.save(joueurs);
    }

    public void updateJoueur(Joueurs joueurs, Long id) {
        joueurRepository.save(joueurs);
    }

}

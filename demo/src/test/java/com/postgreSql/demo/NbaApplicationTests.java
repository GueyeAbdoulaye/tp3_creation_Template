package com.postgreSql.demo;

import com.postgreSql.demo.Controller.AuthenticationRequest;
import com.postgreSql.demo.Controller.AuthenticationResponse;
import com.postgreSql.demo.Controller.AuthentificationController;
import com.postgreSql.demo.Controller.JoueurController;
import com.postgreSql.demo.model.Joueur;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD) // ceci permet d'etre sur un nouveau context (2 joueurs en base) pour chaque test
class NbaApplicationTests {

    @Autowired
    private JoueurController joueurController;

    @Autowired
    private AuthentificationController authentificationController;


    @Test
    void contextLoads() {
        Assertions.assertThat(joueurController).isNotNull();
        Assertions.assertThat(authentificationController).isNotNull();
    }

    @Test
    void authentificate() {
        AuthenticationResponse authenticationResponse = authentificationController.authentificate(new AuthenticationRequest("admin", "admin"));
        Assertions.assertThat(authenticationResponse).isNotNull();
        Assertions.assertThat(authenticationResponse.getToken()).isNotBlank();
    }

    @Test
    void getAllPlayers() {
        List<Joueur> joueurs = joueurController.getAllPlayers();
        Assertions.assertThat(joueurs).containsExactlyInAnyOrder(
                Joueur.builder().prenom("Lebron").nom("JAMES").age(38).club("Lakers").build(),
                Joueur.builder().prenom("Stephan").nom("CURRY").age(30).club("Warriors").build());
    }

    @Test
    void getJoueur() {
        Joueur joueur = joueurController.getJoueur(1l);
        Assertions.assertThat(joueur).isEqualTo(Joueur.builder().prenom("Lebron").nom("JAMES").age(38).club("Lakers").build());
    }

    @Test
    void addJoueur() {
        Assertions.assertThat(joueurController.getAllPlayers()).hasSize(2);
        joueurController.addJoueur(Joueur.builder().prenom("Test").nom("INTEGRATION").age(28).club("TI").build());
        Assertions.assertThat(joueurController.getAllPlayers()).hasSize(3);
    }

    @Test
    void updateJoueur() {
        Joueur oldJoueur = joueurController.getJoueur(1l);
        Assertions.assertThat(oldJoueur.getClub()).isEqualTo("Lakers");

        oldJoueur.setClub("Asvel");
        joueurController.updateJoueur(oldJoueur, oldJoueur.getId());

        Assertions.assertThat(joueurController.getJoueur(1l).getClub()).isEqualTo("Asvel");
    }

    @Test
    void deleteJoueur() {
        Assertions.assertThat(joueurController.getAllPlayers()).hasSize(2);
        joueurController.deleteJoueur(2l);
        Assertions.assertThat(joueurController.getAllPlayers()).hasSize(1);
    }
}

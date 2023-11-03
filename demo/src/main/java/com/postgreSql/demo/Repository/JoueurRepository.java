package com.postgreSql.demo.Repository;

import com.postgreSql.demo.model.Joueurs;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface JoueurRepository extends JpaRepository<Joueurs, Long> {
    Optional<Joueurs> findByUsername(String username);
}

package com.postgreSql.demo.Repository;

import com.postgreSql.demo.model.Joueurs;
import com.postgreSql.demo.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

    Optional<Users> findUsersByUsername(String username);

}

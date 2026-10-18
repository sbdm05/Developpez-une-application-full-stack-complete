package com.openclassrooms.mddapi.repository;

import com.openclassrooms.mddapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    // Connexion par e-mail OU nom d'utilisateur
    Optional<User> findByEmailOrUsername(String email, String username);

    // Vérifier à l'inscription / modification du profil que c'est libre
    boolean existsByEmail(String email);

    boolean existsByUsername(String username);
}
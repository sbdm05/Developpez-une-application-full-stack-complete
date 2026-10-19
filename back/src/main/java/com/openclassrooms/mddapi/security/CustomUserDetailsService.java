package com.openclassrooms.mddapi.security;

import com.openclassrooms.mddapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    /** Utilisé à la connexion : l'identifiant peut être un e-mail OU un nom d'utilisateur. */
    @Override
    public UserDetails loadUserByUsername(String identifier) {
        return userRepository.findByEmailOrUsername(identifier, identifier)
                .map(UserPrincipal::from)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur introuvable"));
    }

    /** Utilisé par le filtre JWT : le token contient l'id de l'utilisateur. */
    public UserPrincipal loadUserById(Long id) {
        return userRepository.findById(id)
                .map(UserPrincipal::from)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur introuvable"));
    }
}
package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.dto.ProfileResponse;
import com.openclassrooms.mddapi.dto.UpdateProfileRequest;
import com.openclassrooms.mddapi.exception.ConflictException;
import com.openclassrooms.mddapi.exception.NotFoundException;
import com.openclassrooms.mddapi.mapper.UserMapper;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    @Transactional(readOnly = true)
    public ProfileResponse getProfile(Long userId) {
        return userMapper.toProfile(getUser(userId));
    }

    @Override
    @Transactional
    public ProfileResponse updateProfile(Long userId, UpdateProfileRequest request) {
        User user = getUser(userId);
        String email = request.email().trim().toLowerCase();
        String username = request.username().trim();

        // On ne vérifie l'unicité que si la valeur change : garder son propre e-mail n'est pas un conflit
        if (!email.equalsIgnoreCase(user.getEmail()) && userRepository.existsByEmail(email)) {
            throw new ConflictException("Cet e-mail est déjà utilisé");
        }
        if (!username.equalsIgnoreCase(user.getUsername()) && userRepository.existsByUsername(username)) {
            throw new ConflictException("Ce nom d'utilisateur est déjà utilisé");
        }

        user.setEmail(email);
        user.setUsername(username);

        // Mot de passe facultatif : vide ou absent = inchangé
        if (request.password() != null && !request.password().isBlank()) {
            user.setPassword(passwordEncoder.encode(request.password()));
        }

        return userMapper.toProfile(user);
    }

    private User getUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Utilisateur introuvable"));
    }
}
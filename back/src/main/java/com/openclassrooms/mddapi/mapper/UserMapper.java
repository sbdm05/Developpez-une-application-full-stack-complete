package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.dto.ProfileResponse;
import com.openclassrooms.mddapi.dto.TopicResponse;
import com.openclassrooms.mddapi.model.Topic;
import com.openclassrooms.mddapi.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

/** Convertit une entité User en profil renvoyé au front (sans mot de passe). */
@Component
@RequiredArgsConstructor
public class UserMapper {

    private final TopicMapper topicMapper;

    public ProfileResponse toProfile(User user) {
        List<TopicResponse> subscriptions = user.getSubscriptions().stream()
                .sorted(Comparator.comparing(Topic::getName))
                .map(topic -> topicMapper.toResponse(topic, true))
                .toList();

        return new ProfileResponse(user.getId(), user.getEmail(), user.getUsername(), subscriptions);
    }
}
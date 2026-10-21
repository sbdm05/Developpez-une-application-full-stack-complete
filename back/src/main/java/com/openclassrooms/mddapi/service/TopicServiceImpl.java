package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.dto.TopicResponse;
import com.openclassrooms.mddapi.exception.NotFoundException;
import com.openclassrooms.mddapi.mapper.TopicMapper;
import com.openclassrooms.mddapi.model.Topic;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.TopicRepository;
import com.openclassrooms.mddapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TopicServiceImpl implements TopicService {

    private final TopicRepository topicRepository;
    private final UserRepository userRepository;
    private final TopicMapper topicMapper;

    @Override
    @Transactional(readOnly = true)
    public List<TopicResponse> findAll(Long userId) {
        User user = getUser(userId);

        // Ids des thèmes auxquels l'utilisateur est abonné (lus dans la table subscriptions)
        Set<Long> subscribedIds = user.getSubscriptions().stream()
                .map(Topic::getId)
                .collect(Collectors.toSet());

        return topicRepository.findAll(Sort.by("name")).stream()
                .map(topic -> topicMapper.toResponse(topic, subscribedIds.contains(topic.getId())))
                .toList();
    }

    @Override
    @Transactional
    public void subscribe(Long userId, Long topicId) {
        User user = getUser(userId);
        Topic topic = getTopic(topicId);
        user.getSubscriptions().add(topic);
    }

    @Override
    @Transactional
    public void unsubscribe(Long userId, Long topicId) {
        User user = getUser(userId);
        Topic topic = getTopic(topicId);
        user.getSubscriptions().remove(topic);
    }

    private User getUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Utilisateur introuvable"));
    }

    private Topic getTopic(Long topicId) {
        return topicRepository.findById(topicId)
                .orElseThrow(() -> new NotFoundException("Thème introuvable"));
    }
}
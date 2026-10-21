package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.dto.TopicResponse;

import java.util.List;

public interface TopicService {

    List<TopicResponse> findAll(Long userId);

    void subscribe(Long userId, Long topicId);

    void unsubscribe(Long userId, Long topicId);
}
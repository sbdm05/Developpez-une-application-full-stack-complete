package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.dto.TopicResponse;
import com.openclassrooms.mddapi.model.Topic;
import org.springframework.stereotype.Component;

/** Convertit une entité Topic en DTO renvoyé au front. */
@Component
public class TopicMapper {

    public TopicResponse toResponse(Topic topic, boolean subscribed) {
        return new TopicResponse(topic.getId(), topic.getName(), topic.getDescription(), subscribed);
    }
}
package com.openclassrooms.mddapi.dto;

import java.time.LocalDateTime;

public record PostResponse(
        Long id,
        String title,
        String content,
        String authorUsername,
        Long topicId,
        String topicName,
        LocalDateTime createdAt
) {
}
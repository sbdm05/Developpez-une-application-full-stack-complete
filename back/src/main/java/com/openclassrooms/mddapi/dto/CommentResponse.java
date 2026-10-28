package com.openclassrooms.mddapi.dto;

import java.time.LocalDateTime;

public record CommentResponse(Long id, String content, String authorUsername, LocalDateTime createdAt) {
}
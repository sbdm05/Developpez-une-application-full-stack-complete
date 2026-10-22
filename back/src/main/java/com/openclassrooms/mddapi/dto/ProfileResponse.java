package com.openclassrooms.mddapi.dto;

import java.util.List;

public record ProfileResponse(Long id, String email, String username, List<TopicResponse> subscriptions) {
}
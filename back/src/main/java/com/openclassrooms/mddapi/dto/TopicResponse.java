package com.openclassrooms.mddapi.dto;

public record TopicResponse(Long id, String name, String description, boolean subscribed) {
}
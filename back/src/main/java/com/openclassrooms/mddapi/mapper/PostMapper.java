package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.dto.PostResponse;
import com.openclassrooms.mddapi.model.Post;
import org.springframework.stereotype.Component;

/** Convertit une entité Post en DTO "aplati" pour le front. */
@Component
public class PostMapper {

    public PostResponse toResponse(Post post) {
        return new PostResponse(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getAuthor().getUsername(),
                post.getTopic().getId(),
                post.getTopic().getName(),
                post.getCreatedAt()
        );
    }
}
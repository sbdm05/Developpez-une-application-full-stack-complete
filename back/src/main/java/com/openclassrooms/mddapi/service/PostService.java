package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.dto.CreatePostRequest;
import com.openclassrooms.mddapi.dto.PostResponse;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface PostService {

    List<PostResponse> getFeed(Long userId, Sort.Direction direction);

    PostResponse create(Long authorId, CreatePostRequest request);

    PostResponse getById(Long postId);
}
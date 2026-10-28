package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.dto.CommentResponse;
import com.openclassrooms.mddapi.dto.CreateCommentRequest;

import java.util.List;

public interface CommentService {

    List<CommentResponse> findByPost(Long postId);

    CommentResponse create(Long postId, Long authorId, CreateCommentRequest request);
}
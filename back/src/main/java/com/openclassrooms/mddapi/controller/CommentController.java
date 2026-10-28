package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.CommentResponse;
import com.openclassrooms.mddapi.dto.CreateCommentRequest;
import com.openclassrooms.mddapi.security.UserPrincipal;
import com.openclassrooms.mddapi.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/posts/{postId}/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping
    public ResponseEntity<List<CommentResponse>> findByPost(@PathVariable Long postId) {
        return ResponseEntity.ok(commentService.findByPost(postId));
    }

    @PostMapping
    public ResponseEntity<CommentResponse> create(@PathVariable Long postId,
                                                  @AuthenticationPrincipal UserPrincipal principal,
                                                  @Valid @RequestBody CreateCommentRequest request) {
        CommentResponse created = commentService.create(postId, principal.id(), request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
}
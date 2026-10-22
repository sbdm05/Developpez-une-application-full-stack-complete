package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.TopicResponse;
import com.openclassrooms.mddapi.security.UserPrincipal;
import com.openclassrooms.mddapi.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/topics")
@RequiredArgsConstructor
public class TopicController {

    private final TopicService topicService;

    @GetMapping
    public ResponseEntity<List<TopicResponse>> findAll(@AuthenticationPrincipal UserPrincipal principal) {
        return ResponseEntity.ok(topicService.findAll(principal.id()));
    }

    @PostMapping("/{id}/subscribe")
    public ResponseEntity<Void> subscribe(@PathVariable Long id,
                                          @AuthenticationPrincipal UserPrincipal principal) {
        topicService.subscribe(principal.id(), id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}/subscribe")
    public ResponseEntity<Void> unsubscribe(@PathVariable Long id,
                                            @AuthenticationPrincipal UserPrincipal principal) {
        topicService.unsubscribe(principal.id(), id);
        return ResponseEntity.noContent().build();
    }
}
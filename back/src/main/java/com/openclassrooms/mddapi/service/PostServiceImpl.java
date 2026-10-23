package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.dto.CreatePostRequest;
import com.openclassrooms.mddapi.dto.PostResponse;
import com.openclassrooms.mddapi.exception.NotFoundException;
import com.openclassrooms.mddapi.mapper.PostMapper;
import com.openclassrooms.mddapi.model.Post;
import com.openclassrooms.mddapi.model.Topic;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.PostRepository;
import com.openclassrooms.mddapi.repository.TopicRepository;
import com.openclassrooms.mddapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final TopicRepository topicRepository;
    private final UserRepository userRepository;
    private final PostMapper postMapper;

    @Override
    @Transactional(readOnly = true)
    public List<PostResponse> getFeed(Long userId, Sort.Direction direction) {
        Set<Topic> topics = getUser(userId).getSubscriptions();

        // Aucun abonnement : fil vide, inutile d'interroger la base
        if (topics.isEmpty()) {
            return List.of();
        }

        // Tri par date, puis par id pour départager deux articles créés au même instant
        Sort sort = Sort.by(direction, "createdAt").and(Sort.by(direction, "id"));

        return postRepository.findByTopicIn(topics, sort).stream()
                .map(postMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public PostResponse create(Long authorId, CreatePostRequest request) {
        User author = getUser(authorId);
        Topic topic = topicRepository.findById(request.topicId())
                .orElseThrow(() -> new NotFoundException("Thème introuvable"));

        Post post = new Post();
        post.setTitle(request.title().trim());
        post.setContent(request.content().trim());
        post.setAuthor(author);   // auteur = l'utilisateur du token
        post.setTopic(topic);     // la date est remplie par @CreationTimestamp

        return postMapper.toResponse(postRepository.save(post));
    }

    @Override
    @Transactional(readOnly = true)
    public PostResponse getById(Long postId) {
        return postRepository.findWithAuthorAndTopicById(postId)
                .map(postMapper::toResponse)
                .orElseThrow(() -> new NotFoundException("Article introuvable"));
    }

    private User getUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Utilisateur introuvable"));
    }
}
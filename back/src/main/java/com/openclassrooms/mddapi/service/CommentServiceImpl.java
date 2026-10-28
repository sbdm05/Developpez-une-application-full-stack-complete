package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.dto.CommentResponse;
import com.openclassrooms.mddapi.dto.CreateCommentRequest;
import com.openclassrooms.mddapi.exception.NotFoundException;
import com.openclassrooms.mddapi.mapper.CommentMapper;
import com.openclassrooms.mddapi.model.Comment;
import com.openclassrooms.mddapi.model.Post;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.CommentRepository;
import com.openclassrooms.mddapi.repository.PostRepository;
import com.openclassrooms.mddapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommentMapper commentMapper;

    @Override
    @Transactional(readOnly = true)
    public List<CommentResponse> findByPost(Long postId) {
        // Distinguer "article inexistant" (404) de "article sans commentaire" ([])
        if (!postRepository.existsById(postId)) {
            throw new NotFoundException("Article introuvable");
        }

        return commentRepository.findByPostIdOrderByCreatedAtAscIdAsc(postId).stream()
                .map(commentMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public CommentResponse create(Long postId, Long authorId, CreateCommentRequest request) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException("Article introuvable"));
        User author = userRepository.findById(authorId)
                .orElseThrow(() -> new NotFoundException("Utilisateur introuvable"));

        Comment comment = new Comment();
        comment.setContent(request.content().trim());
        comment.setAuthor(author);   // auteur = l'utilisateur du token
        comment.setPost(post);       // article = celui de l'URL ; la date vient de @CreationTimestamp

        return commentMapper.toResponse(commentRepository.save(comment));
    }
}
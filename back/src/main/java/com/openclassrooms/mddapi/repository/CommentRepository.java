package com.openclassrooms.mddapi.repository;

import com.openclassrooms.mddapi.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    // Commentaires d'un article, du plus ancien au plus récent
    List<Comment> findByPostIdOrderByCreatedAtAsc(Long postId);
}
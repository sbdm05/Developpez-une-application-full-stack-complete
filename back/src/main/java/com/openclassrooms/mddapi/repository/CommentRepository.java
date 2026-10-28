package com.openclassrooms.mddapi.repository;

import com.openclassrooms.mddapi.model.Comment;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    // Commentaires d'un article, du plus ancien au plus récent, auteur chargé dans la même requête
    @EntityGraph(attributePaths = {"author"})
    List<Comment> findByPostIdOrderByCreatedAtAscIdAsc(Long postId);
}
package com.openclassrooms.mddapi.repository;

import com.openclassrooms.mddapi.model.Post;
import com.openclassrooms.mddapi.model.Topic;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    // Fil d'actualité : auteur et thème chargés dans la MÊME requête (évite le N+1)
    @EntityGraph(attributePaths = {"author", "topic"})
    List<Post> findByTopicIn(Collection<Topic> topics, Sort sort);

    // Détail d'un article, avec son auteur et son thème
    @EntityGraph(attributePaths = {"author", "topic"})
    Optional<Post> findWithAuthorAndTopicById(Long id);
}
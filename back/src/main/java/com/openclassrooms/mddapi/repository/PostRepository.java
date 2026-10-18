package com.openclassrooms.mddapi.repository;

import com.openclassrooms.mddapi.model.Post;
import com.openclassrooms.mddapi.model.Topic;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    // Fil d'actualité : articles des thèmes abonnés, tri choisi par l'appelant
    List<Post> findByTopicIn(Collection<Topic> topics, Sort sort);
}
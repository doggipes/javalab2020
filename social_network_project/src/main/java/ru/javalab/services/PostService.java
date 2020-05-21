package ru.javalab.services;

import org.springframework.stereotype.Service;
import ru.javalab.dto.PostDto;
import ru.javalab.models.entity.Post;

import java.util.List;
import java.util.Optional;

@Service
public interface PostService {
    void addPost(String text, Long user_id);

    List<Post> getAllPosts();

    List<Post> getPostByUserId(Long id);

    Optional<Post> getPostById(Long id);
}

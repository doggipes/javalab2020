package ru.javalab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javalab.dto.PostDto;
import ru.javalab.models.entity.Post;
import ru.javalab.models.entity.User;
import ru.javalab.repositories.PostRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    PostRepository postRepository;

    @Override
    public void addPost(String text, Long user_id) {
        Post post = Post.builder()
                        .text(text)
                        .userId(user_id)
                        .build();

        postRepository.save(post);
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public Optional<Post> getPostById(Long id) {
        return postRepository.find(id);
    }

    @Override
    public List<Post> getPostByUserId(Long userId) {
        List<Post> posts = postRepository.findPostsByUser(User.builder()
                .id(userId)
                .build());

        if(!posts.isEmpty())
            return posts;
        else
            return null;
    }
}

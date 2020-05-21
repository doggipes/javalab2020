package ru.javalab.repositories;

import ru.javalab.models.entity.Post;
import ru.javalab.models.entity.User;

import java.util.List;

public interface PostRepository extends CrudRepository<Long, Post> {
    List<Post> findPostsByUser(User user);
}

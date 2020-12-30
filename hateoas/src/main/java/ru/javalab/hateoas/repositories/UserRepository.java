package ru.javalab.hateoas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.javalab.hateoas.models.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long>{
    List<User> findAllByName(String name);

    List<User> findAllByEmail(String email);
}

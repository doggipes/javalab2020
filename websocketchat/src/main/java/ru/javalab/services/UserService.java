package ru.javalab.services;

import org.springframework.stereotype.Service;
import ru.javalab.dto.SignUpDto;
import ru.javalab.models.User;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    void register(SignUpDto signUpDto);

    void login(SignUpDto signUpDto);

    List<User> getAllUsers();

    Optional<User> getUserById(Long id);

    boolean checkCookie(String token);
}

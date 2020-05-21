package ru.javalab.services;

import org.springframework.stereotype.Service;
import ru.javalab.dto.SignUpDto;
import ru.javalab.dto.UserDto;
import ru.javalab.models.entity.User;
import ru.javalab.models.entity.VerificationToken;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    VerificationToken register(SignUpDto signUpDto);

    VerificationToken createVerificationToken(User user);

    boolean confirmAccount(String token, String email);

    List<User> getAllUsers();

    Optional<User> getUserById(Long id);
}

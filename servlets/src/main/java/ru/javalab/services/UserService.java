package ru.javalab.services;

import org.springframework.stereotype.Service;
import ru.javalab.dto.SignUpDto;
import ru.javalab.dto.UserDto;
import ru.javalab.models.User;

@Service
public interface UserService {
    void register(SignUpDto signUpDto);
}

package ru.javalab.services;

import org.springframework.stereotype.Service;
import ru.javalab.dto.SignUpDto;
import ru.javalab.dto.UserDto;
import ru.javalab.models.Role;
import ru.javalab.models.State;
import ru.javalab.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.javalab.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void register(SignUpDto signUpDto) {
        User user = User.builder()
                .email(signUpDto.getEmail())
                .password(signUpDto.getPassword())
                .name(signUpDto.getName())
                .state(State.CONFIRMED)
                .role(Role.USER)
                .build();

        userRepository.save(user);
    }
}

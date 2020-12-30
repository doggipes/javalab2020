package ru.javalab.hateoas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javalab.hateoas.models.User;
import ru.javalab.hateoas.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User changeEmail(Long id, String email) {
        User user = userRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        user.setEmail(email);
        userRepository.save(user);
        return user;
    }
}

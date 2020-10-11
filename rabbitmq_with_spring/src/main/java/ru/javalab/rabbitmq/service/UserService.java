package ru.javalab.rabbitmq.service;

import org.springframework.stereotype.Service;
import ru.javalab.rabbitmq.model.User;

@Service
public interface UserService {

    void createAkademCertificate(User user);
}

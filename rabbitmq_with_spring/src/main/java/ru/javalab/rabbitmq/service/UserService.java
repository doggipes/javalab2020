package ru.javalab.rabbitmq.service;

import org.springframework.stereotype.Service;
import ru.javalab.rabbitmq.model.entity.User;

@Service
public interface UserService {

    void createAkademCertificate(User user);

    void createUvolnenieCertificate(User user);

    void createOtchislenieCertificate(User user);
}

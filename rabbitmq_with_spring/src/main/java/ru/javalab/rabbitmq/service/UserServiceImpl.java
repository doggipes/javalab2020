package ru.javalab.rabbitmq.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javalab.rabbitmq.model.User;
import ru.javalab.rabbitmq.model.enums.Certificate;
import ru.javalab.rabbitmq.util.DocumentCreator;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    EmailService emailService;

    @Override
    public void createAkademCertificate(User user) {
        DocumentCreator documentCreator = new DocumentCreator();

        emailService.sendEmail(user.getEmail(), "Akadem", "test", documentCreator.createDocument(Certificate.AKADEM,user));
    }
}

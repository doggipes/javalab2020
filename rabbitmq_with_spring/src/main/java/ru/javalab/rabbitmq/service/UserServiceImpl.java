package ru.javalab.rabbitmq.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javalab.rabbitmq.model.entity.User;
import ru.javalab.rabbitmq.model.enums.Certificate;
import ru.javalab.rabbitmq.util.DocumentCreator;

@Service
public class UserServiceImpl implements UserService {
    private final EmailService emailService;
    private final DocumentCreator documentCreator;

    public UserServiceImpl(EmailService emailService) {
        this.emailService = emailService;
        documentCreator = new DocumentCreator();
    }

    @Override
    public void createAkademCertificate(User user) {
        emailService.sendEmail(user.getEmail(), "Akadem", "test", documentCreator.createDocument(Certificate.AKADEM,user));
    }

    @Override
    public void createUvolnenieCertificate(User user) {
        emailService.sendEmail(user.getEmail(), "Uvolnenie", "test", documentCreator.createDocument(Certificate.UVOLNENIE,user));
    }

    @Override
    public void createOtchislenieCertificate(User user) {
        emailService.sendEmail(user.getEmail(), "Otchislenie", "test", documentCreator.createDocument(Certificate.OTCHISLENIE,user));
    }
}

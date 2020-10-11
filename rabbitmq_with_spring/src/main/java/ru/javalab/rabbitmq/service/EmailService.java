package ru.javalab.rabbitmq.service;

import org.springframework.stereotype.Service;

@Service
public interface EmailService {

    void sendEmail(String to, String subject, String body, String fileToAttach);
}

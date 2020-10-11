package ru.javalab.rabbitmq.service;

import org.springframework.stereotype.Service;
import ru.javalab.rabbitmq.util.EmailSender;

@Service
public class EmailServiceImpl implements EmailService {

    @Override
    public void sendEmail(String to, String subject, String body, String fileToAttach) {
        EmailSender emailSender = new EmailSender();
        emailSender.sendMailWithAttachment(to, subject, body, fileToAttach);
    }
}

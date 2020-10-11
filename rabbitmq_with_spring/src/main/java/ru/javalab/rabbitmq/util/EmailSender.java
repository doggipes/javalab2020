package ru.javalab.rabbitmq.util;

import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

@Component
public class EmailSender {
    private JavaMailSender mailSender;

    public EmailSender(){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(465);
        mailSender.setUsername("idzhalil@gmail.com");
        mailSender.setPassword("wdcaygbqfquhuvvx");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.debug", "true");

        this.mailSender = mailSender;
    }



    public void sendMailWithAttachment(String to, String subject, String body, String fileToAttach)
    {
        MimeMessagePreparator preparator = mimeMessage -> {

            mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            mimeMessage.setFrom(new InternetAddress("idzhalil@gmail.com"));
            mimeMessage.setSubject(subject);
            mimeMessage.setText(body);

            MimeBodyPart pdfPart = new MimeBodyPart();
            pdfPart.attachFile(fileToAttach);

            MimeMultipart mimeMultipart = new MimeMultipart();
            mimeMultipart.addBodyPart(pdfPart);

            mimeMessage.setContent(mimeMultipart);
        };

        try {
            mailSender.send(preparator);
        }
        catch (MailException ex) {
            System.err.println(ex.getMessage());
        }
    }
}

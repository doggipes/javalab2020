package ru.javalab.hw2.util;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Properties;

@Component
public class SendEmailWithAttachment {
    @Autowired
    private Properties properties;
    @Autowired
    private FreeMarkerConfigurer freeMarkerConfig;

    public void sendEmail(String to) throws MessagingException, IOException, TemplateException {
        MimeMessage message = new MimeMessage(createSession());
        System.out.println(to);
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
        message.setFrom(new InternetAddress(properties.getProperty("mail.smtp.user")));

        Template t = freeMarkerConfig.getConfiguration().getTemplate("mail.ftlh");
        HashMap<String, String> root = new HashMap<>();
        root.put("email", to);
        Writer out = new StringWriter();
        t.process(root, out);

        BodyPart body = new MimeBodyPart();
        body.setContent(out.toString(), "text/html");
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(body);
        message.setContent(multipart);
        Transport.send(message);
    }

    public Session createSession() {
        return Session.getDefaultInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(properties.getProperty("mail.smtp.user"),
                        properties.getProperty("mail.smtp.password"));
            }
        });
    }
}

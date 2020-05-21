package ru.javalab.aspect;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import ru.javalab.models.entity.VerificationToken;

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

@Aspect
@Component
@EnableAspectJAutoProxy
public class EmailAspect {
    @Autowired
    private Properties properties;

    @Autowired
    private FreeMarkerConfigurer freeMarkerConfig;

    @AfterReturning(pointcut = "execution(* ru.javalab.services.UserService.register(..))", returning = "verificationToken")
    public void sendEmail(JoinPoint joinPoint, VerificationToken verificationToken){
        System.out.println(joinPoint);
        System.out.println("email: " + verificationToken.getUser().getEmail());
        System.out.println("token: " + verificationToken.getToken());

        MimeMessage message = new MimeMessage(createSession());
        try {
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(verificationToken.getUser().getEmail()));
            message.setFrom(new InternetAddress(properties.getProperty("mail.smtp.user")));

            Template t = freeMarkerConfig.getConfiguration().getTemplate("util/mail.ftlh");
            HashMap<String, String> root = new HashMap<>();
            root.put("email", verificationToken.getUser().getEmail());
            root.put("token", verificationToken.getToken());
            Writer out = new StringWriter();
            t.process(root, out);

            BodyPart body = new MimeBodyPart();
            body.setContent(out.toString(), "text/html");
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(body);
            message.setContent(multipart);

            Transport.send(message);

        } catch (MessagingException | IOException | TemplateException e) {
            e.printStackTrace();
        }
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

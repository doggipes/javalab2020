package ru.javalab.hw2.services;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import ru.javalab.hw2.dto.UserDto;
import ru.javalab.hw2.models.User;
import ru.javalab.hw2.repositories.RegisterReposiroty;
import ru.javalab.hw2.util.SendEmailWithAttachment;

import javax.mail.MessagingException;
import javax.servlet.ServletContext;
import java.io.IOException;

@Component
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    private Environment environment;
    @Autowired
    private RegisterReposiroty registerReposiroty;
    @Autowired
    private SendEmailWithAttachment sendEmailWithAttachment;


    @Override
    public UserDto signUp(UserDto userDto) {
        User user = User.builder()
                            .email(userDto.getEmail())
                            .name(userDto.getName())
                            .password(userDto.getPassword())
                            .build();
        registerReposiroty.save(user);
        userDto.setId(user.getId());
        return userDto;
    }

    @Override
    public void confirmedEmail(String email) {
        registerReposiroty.confirmedEmail(email);
    }

    @Override
    public void sendEmail(UserDto userDto) throws MessagingException, IOException, TemplateException {
        sendEmailWithAttachment.sendEmail(userDto.getEmail());
    }
}

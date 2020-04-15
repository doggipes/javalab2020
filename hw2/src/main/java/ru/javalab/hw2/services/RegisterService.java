package ru.javalab.hw2.services;

import freemarker.template.TemplateException;
import ru.javalab.hw2.dto.UserDto;

import javax.mail.MessagingException;
import javax.servlet.ServletContext;
import java.io.IOException;

public interface RegisterService {
    UserDto signUp(UserDto userDto);

    void confirmedEmail(String email);

    void sendEmail(UserDto userDto) throws MessagingException, IOException, TemplateException;
}

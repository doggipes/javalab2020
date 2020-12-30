package ru.javalab.annotations.generator.model;

import ru.javalab.annotations.generator.annotation.HtmlForm;
import ru.javalab.annotations.generator.annotation.HtmlInput;

@HtmlForm(method = "post", action = "/users")
public class User {
    @HtmlInput(type = "text", name = "first_name", placeholder = "Имя")
    private String firstName;
    @HtmlInput(type = "text", name = "class_name", placeholder = "Фамилия")
    private String lastName;
    @HtmlInput(type = "email", name = "email", placeholder = "Почта")
    private String email;
    @HtmlInput(type = "password", name = "password", placeholder = "Пароль")
    private String password;
}

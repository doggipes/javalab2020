package ru.javalab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.javalab.dto.SignUpDto;
import ru.javalab.services.UserService;

import javax.servlet.http.Cookie;

@Controller
public class SignUpController {
    @Autowired
    UserService userService;

    @GetMapping("/signUp")
    public String getSignUpPage(@CookieValue(value = "token", required = false) Cookie cookieToken){
        if (cookieToken != null && userService.checkCookie(cookieToken.getValue()))
            return "redirect:/chat";
        else
            return "signUp";
    }

    @PostMapping("/signUp")
    public String postSignUpPage(SignUpDto signUpDto){
        userService.register(signUpDto);
        return "signUp";
    }
}

package ru.javalab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.javalab.dto.SignUpDto;
import ru.javalab.services.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {
    @Autowired
    UserService userService;

    @GetMapping("/")
    public String getRootPage(@CookieValue(value = "token", required = false) Cookie cookieToken){
        if (cookieToken != null && userService.checkCookie(cookieToken.getValue()))
            return "redirect:/chat";
        else
            return "redirect:/login";
    }

    @GetMapping("/login")
    public String getLoginPage(@CookieValue(value = "token", required = false) Cookie cookieToken){
        if (cookieToken != null && userService.checkCookie(cookieToken.getValue()))
            return "redirect:/chat";
        else
            return "login";
    }

    @PostMapping("/login")
    public String postLoginPage(SignUpDto signUpDto, HttpServletResponse response){
        userService.login(signUpDto);
        System.out.println(signUpDto.getToken());
        if(signUpDto.getToken() != null) {
            response.addCookie(new Cookie("token", signUpDto.getToken()));
            return "redirect:/chat";
        }
        else
            return "redirect:/signUp";
    }
}

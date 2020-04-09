package ru.javalab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.javalab.dto.SignUpDto;
import ru.javalab.services.UserService;

@Controller
public class SignUpController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/signUp", method = RequestMethod.GET)
    public String getSignUpPage(Authentication authentication){
        if(authentication != null )
            return "redirect:/";
        else
            return "signUp";
    }

    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    public String signUp(SignUpDto signUpDto){
        userService.register(signUpDto);
        return "signUp";
    }
}

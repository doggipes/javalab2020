package ru.javalab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.javalab.dto.SignUpDto;
import ru.javalab.dto.SignUpForm;
import ru.javalab.services.UserService;

import javax.validation.Valid;

@Controller
public class SignUpController {
    @Autowired
    private UserService userService;


    @RequestMapping(value = "/signUp", method = RequestMethod.GET)
    public String getSignUpPage(Authentication authentication, Model model){
        if(authentication != null )
            return "redirect:/";
        else {
            model.addAttribute("SignUpForm", new SignUpForm());
            return "signUp";
        }
    }

    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    public String postSignUpPage(@Valid SignUpForm form, BindingResult bindingResult, Model model){
        if(!bindingResult.hasErrors())
            userService.register(SignUpDto.builder()
                                            .email(form.getEmail())
                                            .name(form.getName())
                                            .password(form.getPassword())
                                            .build());
        System.out.println(form);
        System.out.println(bindingResult.getAllErrors());
        model.addAttribute("SignUpForm", form);
        return "signUp";
    }
}

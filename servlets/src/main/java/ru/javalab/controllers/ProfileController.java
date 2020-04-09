package ru.javalab.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {

    @GetMapping("/profile")
    public String getProfilePage(Model model, Authentication authentication){
        model.addAttribute("name", authentication.getName());
        return "profile";
    }
}

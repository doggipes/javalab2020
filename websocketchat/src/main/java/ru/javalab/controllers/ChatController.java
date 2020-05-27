package ru.javalab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.javalab.services.UserService;
import ru.javalab.util.JwtToken;

import javax.servlet.http.Cookie;

@Controller
public class ChatController {
    @Autowired
    UserService userService;

    @GetMapping("/chat")
    public String getIndexPage(Model model, @CookieValue(value = "token", required = false) Cookie cookieToken, @RequestParam(defaultValue = "1") String room) {
        if (cookieToken != null && userService.checkCookie(cookieToken.getValue())) {
            model.addAttribute("room", room);
            model.addAttribute("pageId", JwtToken.getIdFromJwt(cookieToken.getValue()));
            return "chat";
        }
        else
            return "redirect:/login";
    }
}

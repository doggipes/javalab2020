package ru.javalab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.javalab.services.UserService;

@Controller
public class ConfirmController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/confirm", method = RequestMethod.GET)
    public @ResponseBody
    String getConfirmPage(@RequestParam("email") String email, @RequestParam("token") String token){
        if(userService.confirmAccount(token, email))
            return "ok";
        else
            return "Oops, something went wrong";
    }
}

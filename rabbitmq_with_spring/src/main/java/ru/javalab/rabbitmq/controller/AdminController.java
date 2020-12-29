package ru.javalab.rabbitmq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String getAdminPage(Model model, @RequestParam String id){

        model.addAttribute("image_src");
        return "admin_page";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.POST)
    public String postAdminPage(){
        return "admin_page";
    }
}

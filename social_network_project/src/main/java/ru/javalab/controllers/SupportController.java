package ru.javalab.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.javalab.models.enums.Role;
import ru.javalab.security.details.UserDetailsImpl;
import ru.javalab.services.SupportService;

@Controller
public class SupportController {
    @Autowired
    SupportService supportService;

    @GetMapping("/support")
    public String getChatPage(Model model, Authentication authentication) {
        if(authentication != null) {
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            String uuid = supportService.identify(userDetails.getUser().getId());

            model.addAttribute("pageId", uuid);
            model.addAttribute("messages", supportService.getMessages(uuid));
            return "support";
        }
        else
            return "redirect:/login";
    }

    @GetMapping("/support-admin")
    public String getAdminPage(Model model, Authentication authentication){
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
           if(!userDetails.getUser().getRole().equals(Role.SUPPORT))
               return "redirect:/profile";


                model.addAttribute("tickets", supportService.getByUniqueToken());
               return "support-admin";
    }

    @GetMapping("/support-admin/{userId}")
    public String getAdminWithUserPage(@PathVariable String userId, Model model, Authentication authentication){
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        if(!userDetails.getUser().getRole().equals(Role.SUPPORT))
            return "redirect:/profile";

        String uuid = supportService.identify(Long.valueOf(userId));

        model.addAttribute("pageId", uuid);
        model.addAttribute("messages", supportService.getMessages(uuid));
        return "support";
    }
}

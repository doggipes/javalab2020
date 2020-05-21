package ru.javalab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.javalab.dto.PostDto;
import ru.javalab.security.details.UserDetailsImpl;
import ru.javalab.services.PostService;

@Controller
public class FeedController {
    @Autowired
    PostService postService;

    @GetMapping("/feed")
    public String getFeedPage(Authentication authentication, Model model){
        if(authentication != null) {
            model.addAttribute("posts", postService.getAllPosts());
            return "feed";
        }
        else
            return "redirect:/login";
    }

    @PostMapping("/feed")
    public String postFeedPage(String text, Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        postService.addPost(text, userDetails.getUser().getId());
        return "feed";
    }
}

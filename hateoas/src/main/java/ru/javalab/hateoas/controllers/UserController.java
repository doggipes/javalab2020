package ru.javalab.hateoas.controllers;

import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.javalab.hateoas.services.UserService;

@RepositoryRestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/users/{user-id}/email", method = RequestMethod.PUT)
    public ResponseEntity<?> change_email
            (@PathVariable("user-id") Long userId, @RequestParam("email") String email) {
        return ResponseEntity.ok(
                EntityModel.of(userService.changeEmail(userId, email)));
    }
}

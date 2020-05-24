package ru.javalab.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.javalab.dto.PostDto;
import ru.javalab.dto.UserDto;
import ru.javalab.services.PostService;
import ru.javalab.services.UserService;

import java.util.NoSuchElementException;

@RestController
public class UserRestController {
    @Autowired
    UserService userService;

    @Autowired
    PostService postService;

    @RequestMapping(value = "/api/users", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Object> getAllUsers() {
        return ResponseEntity.ok(UserDto.from(userService.getAllUsers()));
    }

    @RequestMapping(value = "/api/users/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Object> getUserById(@PathVariable String id){
        try {
            return ResponseEntity.ok(UserDto.from(userService.getUserById(Long.valueOf(id)).get()));
        }
        catch (NoSuchElementException e){
            return new ResponseEntity<>("User Not Found", HttpStatus.NOT_FOUND);
        }
        catch (NumberFormatException e){
            return new ResponseEntity<>("Illegal Argument", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/api/users/{id}/posts", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Object> getPostsByUser(@PathVariable String id){
        try {
            return ResponseEntity.ok(PostDto.from(postService.getPostByUserId(Long.valueOf(id))));
        }
        catch (NoSuchElementException | NullPointerException e){
            return new ResponseEntity<>("User Not Found", HttpStatus.NOT_FOUND);
        }
        catch (NumberFormatException e){
            return new ResponseEntity<>("Illegal Argument", HttpStatus.BAD_REQUEST);
        }
    }
}

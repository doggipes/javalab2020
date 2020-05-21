package ru.javalab.controllers.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    public ResponseEntity getAllUsers() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return ResponseEntity.ok(objectMapper.writeValueAsString(UserDto.from(userService.getAllUsers())));
    }

    @RequestMapping(value = "/api/users/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getUserById(@PathVariable String id){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return ResponseEntity.ok(objectMapper.writeValueAsString(UserDto.from(userService.getUserById(Long.valueOf(id)).get())));
        }
        catch (NoSuchElementException | JsonProcessingException e){
            return new ResponseEntity<>("User Not Found", HttpStatus.NOT_FOUND);
        }
        catch (NumberFormatException e){
            return new ResponseEntity<>("Illegal Argument", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/api/users/{id}/posts", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getPostsByUser(@PathVariable String id){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return ResponseEntity.ok(objectMapper.writeValueAsString(PostDto.from(postService.getPostByUserId(Long.valueOf(id)))));
        }
        catch (NoSuchElementException | NullPointerException | JsonProcessingException e){
            return new ResponseEntity<>("User Not Found", HttpStatus.NOT_FOUND);
        }
        catch (NumberFormatException e){
            return new ResponseEntity<>("Illegal Argument", HttpStatus.BAD_REQUEST);
        }
    }
}

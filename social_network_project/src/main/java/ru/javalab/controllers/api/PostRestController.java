package ru.javalab.controllers.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.javalab.dto.PostDto;
import ru.javalab.services.PostService;

import java.util.NoSuchElementException;

@RestController
public class PostRestController {
    @Autowired
    PostService postService;

    @RequestMapping(value = "/api/posts", method = RequestMethod.GET)
    @ResponseBody
    public String getAllPosts() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(PostDto.from(postService.getAllPosts()));
    }

    @RequestMapping(value = "/api/posts/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getPostById(@PathVariable String id){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return ResponseEntity.ok(objectMapper.writeValueAsString(PostDto.from(postService.getPostById(Long.valueOf(id)).get())));
        }
        catch (NoSuchElementException | JsonProcessingException e){
            return new ResponseEntity<>("Post Not Found", HttpStatus.NOT_FOUND);
        }
        catch (NumberFormatException e){
            return new ResponseEntity<>("Illegal Argument", HttpStatus.BAD_REQUEST);
        }
    }

}

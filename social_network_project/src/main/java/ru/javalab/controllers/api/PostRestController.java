package ru.javalab.controllers.api;

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
    public ResponseEntity<Object> getAllPosts() {
        return ResponseEntity.ok(PostDto.from(postService.getAllPosts()));
    }

    @RequestMapping(value = "/api/posts/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Object> getPostById(@PathVariable String id){
        try {
            return ResponseEntity.ok(PostDto.from(postService.getPostById(Long.valueOf(id)).get()));
        }
        catch (NoSuchElementException e){
            return new ResponseEntity<>("Post Not Found", HttpStatus.NOT_FOUND);
        }
        catch (NumberFormatException e){
            return new ResponseEntity<>("Illegal Argument", HttpStatus.BAD_REQUEST);
        }
    }

}

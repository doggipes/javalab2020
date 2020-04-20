package ru.javalab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.javalab.services.FileService;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@MultipartConfig
@Controller
public class FileController {
    @Autowired
    FileService fileService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getUploadPage(){
        return "fileupload";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public @ResponseBody String uploadFile(@RequestParam("name") String name, @RequestParam("file") MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            fileService.upload(file, name);
            return "ok";
//            redirectAttributes.addFlashAttribute("message",
//                    "You successfully uploaded " + file.getOriginalFilename() + "!");
        } else {
            return "ne ok";
//            redirectAttributes.addFlashAttribute("message",
//                    "Failed upload " + file.getOriginalFilename() + "!");
        }
        //return "fileUpload";
    }
}

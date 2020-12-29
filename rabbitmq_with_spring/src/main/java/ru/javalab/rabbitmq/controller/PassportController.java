package ru.javalab.rabbitmq.controller;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.javalab.rabbitmq.model.entity.File;

import java.io.IOException;

@Controller
public class PassportController {

    private final AmqpTemplate amqpTemplate;

    @Value("${rabbitmq.exchange.direct}")
    private String exchange_direct;

    @Value("${rabbitmq.exchange.topic}")
    private String exchange_topic;

    @Value("${rabbitmq.routingkey.passport}")
    private String routingkey;

    public PassportController(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    @RequestMapping(value = "/passport", method = RequestMethod.GET)
    public String getPassportPage(Model model){
        return "passport_upload_page";
    }

    @RequestMapping(value = "/passport", method = RequestMethod.POST)
    public String postPassportPage(@RequestParam("file") MultipartFile file, Model model) throws IOException {
        System.out.println(file.getOriginalFilename());
        File new_file = File.builder()
                            .name(file.getName())
                            .nameWithExtension(file.getOriginalFilename())
                            .size(file.getSize())
                            .type(file.getContentType())
                            .bytes(file.getBytes())
                            .build();
        amqpTemplate.convertAndSend(exchange_direct, routingkey, new_file);
        amqpTemplate.convertAndSend(exchange_topic, routingkey, new_file);
        return "redirect:/passport";
    }
}

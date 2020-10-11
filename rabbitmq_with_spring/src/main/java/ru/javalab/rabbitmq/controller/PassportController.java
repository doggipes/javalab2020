package ru.javalab.rabbitmq.controller;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class PassportController {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Value("${rabbitmq.exchange.direct}")
    private String exchange;

    @Value("${rabbitmq.routingkey.passport}")
    private String routingkey;

    @RequestMapping(value = "/passport", method = RequestMethod.GET)
    public String getPassportPage(Model model){

        return "passport_upload_page";
    }

    @RequestMapping(value = "/passport", method = RequestMethod.POST)
    public String postPassportPage(@RequestParam("file") MultipartFile file, Model model) throws IOException {
        System.out.println(file.getOriginalFilename());
        byte[] bytes = file.getBytes();
        //Message message = MessageBuilder.withBody(bytes).setHeader("ContentType", file.getContentType()).build();
        amqpTemplate.convertAndSend(exchange, routingkey, bytes);
        return "passport_upload_page";
    }
}

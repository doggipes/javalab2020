package ru.javalab.rabbitmq.controller;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.javalab.rabbitmq.dto.form.CertificateForm;
import ru.javalab.rabbitmq.model.entity.User;

@Controller
public class AkademController {

    private final AmqpTemplate amqpTemplate;

    @Value("${rabbitmq.exchange.direct}")
    private String exchange_direct;

    @Value("${rabbitmq.exchange.topic}")
    private String exchange_topic;

    @Value("${rabbitmq.routingkey.akadem}")
    private String routingkey;

    public AkademController(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getPage(Model model) {
        System.out.println("Test message on Spring");
        model.addAttribute("CertificateForm", new CertificateForm());
        return "certificate_of_akadem_create_page";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String postPage(CertificateForm form){
        User user = User.builder()
                        .name(form.getName())
                        .surname(form.getSurname())
                        .age(form.getAge())
                        .date(form.getDate())
                        .email(form.getEmail())
                        .pass_number(form.getPass_number())
                .build();
        amqpTemplate.convertAndSend(exchange_direct, routingkey, user);
        amqpTemplate.convertAndSend(exchange_topic, routingkey, user);
        return "redirect:/";
    }
}

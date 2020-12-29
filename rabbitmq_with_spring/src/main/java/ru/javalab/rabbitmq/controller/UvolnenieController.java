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
public class UvolnenieController {

    private final AmqpTemplate amqpTemplate;

    @Value("${rabbitmq.exchange.fanout}")
    private String exchange_fanout;

    @Value("${rabbitmq.exchange.topic}")
    private String exchange_topic;

    @Value("${rabbitmq.routingkey.exit}")
    private String routingKey;

    public UvolnenieController(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    @RequestMapping(value = "/uvol", method = RequestMethod.GET)
    public String getPage(Model model) {
        System.out.println("Test message on Spring");
        model.addAttribute("CertificateForm", new CertificateForm());
        return "certificate_of_uvolnenie_create_page";
    }

    @RequestMapping(value = "/uvol", method = RequestMethod.POST)
    public String postPage(CertificateForm form){
        User user = User.builder()
                .name(form.getName())
                .surname(form.getSurname())
                .age(form.getAge())
                .date(form.getDate())
                .email(form.getEmail())
                .pass_number(form.getPass_number())
                .build();
        amqpTemplate.convertAndSend(exchange_fanout, routingKey, user);
        amqpTemplate.convertAndSend(exchange_topic, routingKey, user);
        return "redirect:/uvol";
    }
}

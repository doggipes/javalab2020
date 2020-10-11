package ru.javalab.rabbitmq.controller;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.javalab.rabbitmq.dto.form.AkademForm;
import ru.javalab.rabbitmq.model.User;

@Controller
public class AkademController {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Value("${rabbitmq.exchange.direct}")
    private String exchange;

    @Value("${rabbitmq.routingkey.akadem}")
    private String routingkey;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getPage(Model model) {
        System.out.println("Test message on Spring");
        model.addAttribute("AkademForm", new AkademForm());
        return "certificate_of_akadem_create_page";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String postPage(AkademForm form){
        User user = User.builder()
                        .name(form.getName())
                        .surname(form.getSurname())
                        .age(form.getAge())
                        .date(form.getDate())
                        .email(form.getEmail())
                        .pass_number(form.getPass_number())
                .build();
        amqpTemplate.convertAndSend(exchange, routingkey, user);
        return "redirect:/";
    }
}

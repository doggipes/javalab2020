package ru.javalab.rabbitmq.consumer;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.javalab.rabbitmq.model.User;
import ru.javalab.rabbitmq.service.UserService;

import java.io.IOException;


@Component
public class SpringConsumer {
    @Autowired
    UserService userService;

    @RabbitListener(queues = "queue1")
    public void processQueue1(User user) {
        System.out.println("Received from queue 1: " + user.getName());
        userService.createAkademCertificate(user);
    }

    @RabbitListener(queues = "queue2")
    public void processQueue2(byte[] bytes){
        System.out.println("Received from queue 2: ");
    }
}

package ru.javalab.rabbitmq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import ru.javalab.rabbitmq.model.entity.File;
import ru.javalab.rabbitmq.model.entity.User;
import ru.javalab.rabbitmq.service.FileService;
import ru.javalab.rabbitmq.service.StatisticService;
import ru.javalab.rabbitmq.service.UserService;

import java.io.IOException;


@Component
public class SpringConsumer {
    private final UserService userService;
    private final FileService fileService;
    private final StatisticService statisticService;

    public SpringConsumer(UserService userService, FileService fileService, StatisticService statisticService) {
        this.userService = userService;
        this.fileService = fileService;
        this.statisticService = statisticService;
    }

    @RabbitListener(queues = "queue1")
    public void processQueue1(User user) {
        System.out.println("Received from queue 1: " + user.getName());
        userService.createAkademCertificate(user);
    }

    @RabbitListener(queues = "queue2")
    public void processQueue2(File file) throws IOException {
        System.out.println("Received from queue 2: " );
        fileService.save(file);
    }

    @RabbitListener(queues = "queue3")
    public void processQueue3(User user) throws IOException {
        System.out.println("Received from queue 3: " + user.getName());
        userService.createOtchislenieCertificate(user);
    }

    @RabbitListener(queues = "queue4")
    public void processQueue4(User user) throws IOException {
        System.out.println("Received from queue 4: " + user.getName());
        userService.createUvolnenieCertificate(user);
    }

    @RabbitListener(queues = "queue5")
    public void processQueue5(User user) throws IOException {
        statisticService.addLog(new String(user.getName() + " zakazal spravku na akadem\n"));
    }

    @RabbitListener(queues = "queue6")
    public void processQueue6(User user) throws IOException {
        statisticService.addLog(new String(user.getName() + " zakazal spravku na uvolnenie i otchislenie\n"));
    }

    @RabbitListener(queues = "queue7")
    public void processQueue7(File file) throws IOException {
        statisticService.addLog(new String("User zagruzil passport " + file.getNameWithExtension() + "\n"));
    }
}

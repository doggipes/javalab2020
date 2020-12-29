package ru.javalab.rabbitmq.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import org.springframework.stereotype.Component;
import ru.javalab.rabbitmq.model.entity.User;
import ru.javalab.rabbitmq.model.enums.Certificate;
import ru.javalab.rabbitmq.service.EmailService;
import ru.javalab.rabbitmq.service.EmailServiceImpl;
import ru.javalab.rabbitmq.util.DocumentCreator;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

// читает сообщения из очереди
@Component
public class AkademConsumer {
    private final static String EXCHANGE_NAME = "images";
    private final static String EXCHANGE_TYPE = "fanout";

    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");

        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            channel.basicQos(3);

            channel.exchangeDeclare(EXCHANGE_NAME, EXCHANGE_TYPE);
            String queue = channel.queueDeclare().getQueue();

            channel.queueBind(queue, EXCHANGE_NAME, "");

            DeliverCallback deliverCallback = (consumerTag, message) -> {
                ObjectMapper objectMapper = new ObjectMapper();
                DocumentCreator documentCreator = new DocumentCreator();
                User user = objectMapper.readValue(message.getBody(), User.class);

                EmailService emailService = new EmailServiceImpl();
                emailService.sendEmail("idzhalil@gmail.com", "test", "test", documentCreator.createDocument(Certificate.AKADEM,user));

                    channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
            };

            channel.basicConsume(queue, false, deliverCallback, consumerTag -> {});
        } catch (IOException | TimeoutException e) {
            throw new IllegalArgumentException(e);
        }
    }
}

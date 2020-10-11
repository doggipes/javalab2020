package ru.javalab.rabbitmq.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import ru.javalab.rabbitmq.model.User;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

// приложение, которое отправляет сообщения в очереди
public class Producer {
    // есть EXCHANGE - images НЕ ОЧЕРЕДЬ
    private final static String EXCHANGE_NAME = "images";
    // тип FANOUT
    private final static String EXCHANGE_TYPE = "fanout";

    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            channel.exchangeDeclare(EXCHANGE_NAME, EXCHANGE_TYPE);
            Scanner in = new Scanner(System.in);
            while (true) {
                System.out.println("Do you want stop? y/n");
                User user = new User();
                String string = in.nextLine();
                if(string.equals("y"))
                    break;
                else {
                    System.out.println("Name: ");
                    if (!in.equals("") && !in.equals("!stop"))
                        user.setName(in.nextLine());

                    System.out.println("Surname: ");
                    if (!in.equals("") && !in.equals("!stop"))
                        user.setSurname(in.nextLine());

                    System.out.println("Age: ");
                    if (!in.equals("") && !in.equals("!stop"))
                        user.setAge(in.nextLine());

                    System.out.println("Pass number: ");
                    if (!in.equals("") && !in.equals("!stop"))
                        user.setPass_number(in.nextLine());

                    System.out.println("Date: ");
                    if (!in.equals("") && !in.equals("!stop"))
                        user.setDate(in.nextLine());

                    ObjectMapper objectMapper = new ObjectMapper();
                    channel.basicPublish(EXCHANGE_NAME, "", null, objectMapper.writeValueAsBytes(user));
                }
            }
            System.out.println("done");
        } catch (IOException | TimeoutException e) {
            throw new IllegalArgumentException(e);
        }
    }

}

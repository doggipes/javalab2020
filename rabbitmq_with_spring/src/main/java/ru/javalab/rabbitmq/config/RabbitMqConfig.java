package ru.javalab.rabbitmq.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
@EnableRabbit
public class RabbitMqConfig {
    @Autowired
    private Environment environment;

    @Bean
    public ConnectionFactory connectionFactory()
    {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setUsername( environment.getProperty( "rabbitmq.username" ) );
        connectionFactory.setPassword( environment.getProperty( "rabbitmq.password" ) );
        connectionFactory.setVirtualHost( environment.getProperty( "rabbitmq.virtualHost" ) );
        connectionFactory.setHost( environment.getProperty( "rabbitmq.hostName" ) );
        connectionFactory.setPort( Integer.parseInt( environment.getProperty( "rabbitmq.portNumber" ) ) );
        return connectionFactory;
    }
    @Bean
    public AmqpAdmin amqpAdmin()
    {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory());
        rabbitAdmin.setIgnoreDeclarationExceptions(true);
        return rabbitAdmin;
    }

    @Bean
    public RabbitTemplate rabbitTemplate()
    {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }

    @Bean(name = "rabbitListenerContainerFactory")
    public SimpleRabbitListenerContainerFactory listenerFactory()
    {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        factory.setMessageConverter(jsonMessageConverter());

        factory.setConcurrentConsumers(3);
        factory.setMaxConcurrentConsumers(10);
        return factory;
    }

    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter()
    {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public Queue queue1()
    {
        return new Queue(environment.getProperty("rabbitmq.queue.1"));
    }

    @Bean
    public Queue queue2(){
        return new Queue(environment.getProperty("rabbitmq.queue.2"));
    }

    @Bean
    public DirectExchange directExchange()
    {
        return new DirectExchange(environment.getProperty("rabbitmq.exchange.direct"));
    }

    @Bean
    public Binding binding_akadem()
    {
        return BindingBuilder.bind(queue1()).to(directExchange()).with(environment.getProperty("rabbitmq.routingkey.akadem"));
    }

    @Bean
    public Binding binding_passport(){
        return BindingBuilder.bind(queue2()).to(directExchange()).with(environment.getProperty("rabbitmq.routingkey.passport"));
    }
}

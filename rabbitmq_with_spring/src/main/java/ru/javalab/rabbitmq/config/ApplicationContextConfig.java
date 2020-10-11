package ru.javalab.rabbitmq.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import java.util.Properties;

@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = "ru.javalab.rabbitmq")
public class ApplicationContextConfig {
    public static final String QUEUE_NAME1 = "test1";
    public static final String QUEUE_NAME2 = "test2";

    @Autowired
    private Environment environment;

    @Bean
    public ViewResolver viewResolver() {
        FreeMarkerViewResolver viewResolver = new FreeMarkerViewResolver();
        viewResolver.setContentType("text/html; charset=UTF-8");
        viewResolver.setCache(true);
        viewResolver.setPrefix("");
        viewResolver.setSuffix(".ftlh");
        return viewResolver;
    }


    @Bean
    public FreeMarkerConfigurer freemarkerConfig() {
        FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
        freeMarkerConfigurer.setTemplateLoaderPath("WEB-INF/templates");
        freeMarkerConfigurer.setDefaultEncoding("UTF-8");
        return freeMarkerConfigurer;
    }


    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver resolver=new CommonsMultipartResolver();
        resolver.setDefaultEncoding("utf-8");
        resolver.setMaxUploadSize(1000000000);
        return resolver;
    }
//    @Bean
//    public ConnectionFactory connectionFactory(){
//        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("localhost");
//        return connectionFactory;
//    }

//    @Bean
//    public AmqpAdmin amqpAdmin(){
//        return new RabbitAdmin(connectionFactory());
//    }
//
//    @Bean
//    public RabbitTemplate rabbitTemplate(){
//        return new RabbitTemplate(connectionFactory());
//    }
//
//    @Bean
//    public Queue myQueue1(){
//        return new Queue(QUEUE_NAME1);
//    }
//
//    @Bean
//    public Queue myQueue2(){
//        return new Queue(QUEUE_NAME2);
//    }
//
//    @Bean
//    public FanoutExchange fanoutExchangeA(){
//        return new FanoutExchange("exchange-example-3");
//    }
//
//    @Bean
//    public Binding binding1(){
//        return BindingBuilder.bind(myQueue1()).to(fanoutExchangeA());
//    }
//
//    @Bean
//    public Binding binding2(){
//        return BindingBuilder.bind(myQueue2()).to(fanoutExchangeA());
//    }

    @Bean
    public Properties properties() {
        Properties properties = System.getProperties();
        properties.put("spring.mail.port", environment.getProperty("spring.mail.port"));
        properties.put("spring.mail.host", environment.getProperty("spring.mail.host"));
        properties.put("spring.mail.properties.mail.smtp.auth", environment.getProperty("spring.mail.properties.mail.smtp.auth"));
        properties.put("spring.mail.username", environment.getProperty("spring.mail.username"));
        properties.put("spring.mail.password", environment.getProperty("spring.mail.password"));
        properties.put("spring.mail.properties.mail.smtp.starttls.enable", environment.getProperty("spring.mail.properties.mail.smtp.starttls.enable"));
        properties.put("mail.debug", environment.getProperty("mail.debug"));
        return properties;
    }

    @Bean
    public JavaMailSender getMailSender(){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setHost(environment.getProperty("spring.mail.host"));
        mailSender.setPort(Integer.parseInt(environment.getProperty("spring.mail.port")));
        mailSender.setUsername(environment.getProperty("spring.mail.username"));
        mailSender.setPassword(environment.getProperty("spring.mail.password"));

        return mailSender;
    }
}

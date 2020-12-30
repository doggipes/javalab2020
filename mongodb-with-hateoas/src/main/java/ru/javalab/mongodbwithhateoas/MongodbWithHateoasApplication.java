package ru.javalab.mongodbwithhateoas;

import com.mongodb.client.MongoClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@SpringBootApplication
@EnableMongoRepositories(basePackages = "ru.javalab.mongodbwithhateoas")
public class MongodbWithHateoasApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(MongodbWithHateoasApplication.class, args);
//        CarRepository carRepository = new CarRepository(applicationContext.getBean(MongoTemplate.class));
//        carRepository.save(Car.builder().color("red").cost("123").name("123").build());
         // UserRepository userRepository = applicationContext.getBean(UserRepository.class);
    //    System.out.println(userRepository.findByEmailAndPassword("artem@mail.ru", "qwerty123").getName());
    }



    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(MongoClients.create(), "education");
    }

}

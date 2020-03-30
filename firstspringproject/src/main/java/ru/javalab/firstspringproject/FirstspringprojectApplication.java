package ru.javalab.firstspringproject;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.javalab.firstspringproject.models.Course;
import ru.javalab.firstspringproject.repository.CourseRepository;
import ru.javalab.firstspringproject.repository.CourseRepositoryImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class FirstspringprojectApplication {

    public static void main(String[] args) throws ClassNotFoundException {
        SpringApplication.run(FirstspringprojectApplication.class, args);

        HikariConfig config = new HikariConfig();
        config.setUsername("root");
        config.setPassword("Qwerty123");
        config.setJdbcUrl("jdbc:mysql://localhost:3306/informatics?serverTimezone=UTC");
        HikariDataSource dataSource = new HikariDataSource(config);

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        CourseRepository coursesRepository = new CourseRepositoryImpl(jdbcTemplate);
        Optional<Course> courseOptional = coursesRepository.find(1L);
        List<Course> qqq = coursesRepository.findAll();
        Course course = Course.builder()
                                .name("Norm")
                                .lessons(new ArrayList<>())
                                .build();
        coursesRepository.save(course);
        System.out.println(courseOptional.get());
    }

}

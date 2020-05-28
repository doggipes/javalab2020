package ru.javalab.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = "ru.javalab")
@EnableTransactionManagement
public class ApplicationContextConfig {
    @Autowired
    private Environment environment;

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(hikariDataSource());
    }

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
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();


        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(hikariDataSource());
        entityManagerFactory.setPackagesToScan("ru.javalab");
        entityManagerFactory.setJpaVendorAdapter(hibernateJpaVendorAdapter);
        entityManagerFactory.setJpaProperties(additionalProperties());

        return entityManagerFactory;
    }

    private Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        properties.setProperty("hibernate.show_sql", "true");

        return properties;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);

        return transactionManager;
    }

    @Bean
    public HikariConfig hikariConfig() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(environment.getProperty("db.url"));
        config.setUsername(environment.getProperty("db.user"));
        config.setPassword(environment.getProperty("db.password"));
        config.setDriverClassName(environment.getProperty("db.driver"));
        return config;
    }

    @Bean
    public DataSource hikariDataSource() {
        return new HikariDataSource(hikariConfig());
    }

    @Bean
    public Properties properties() {
        Properties properties = System.getProperties();
        properties.put("mail.smtp.port", environment.getProperty("mail.smtp.port"));
        properties.put("mail.smtp.host", environment.getProperty("mail.smtp.host"));
        properties.put("mail.smtp.auth", environment.getProperty("mail.smtp.auth"));
        properties.put("mail.smtp.user", environment.getProperty("mail.smtp.user"));
        properties.put("mail.smtp.password", environment.getProperty("mail.smtp.password"));
        properties.put("mail.smtp.ssl.enable", environment.getProperty("mail.smtp.ssl.enable"));
        properties.put("mail.debug", environment.getProperty("mail.debug"));
        return properties;
    }
}

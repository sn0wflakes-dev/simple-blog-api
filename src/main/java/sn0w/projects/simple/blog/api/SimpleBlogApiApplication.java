package sn0w.projects.simple.blog.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.net.URL;

@SpringBootApplication
@EnableJpaRepositories(
        basePackages = "sn0w.projects.simple.blog.api.infrastructure.jpa.repository"
)
@EnableMongoRepositories(
        basePackages = "sn0w.projects.simple.blog.api.infrastructure.mongodb.repository"
)
public class SimpleBlogApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleBlogApiApplication.class, args);
    }

}

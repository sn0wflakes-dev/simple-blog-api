package sn0w.projects.simple.blog.api.infrastructure.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoConfig {
    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create(
                "mongodb://root:root@localhost:27017/simple_blogdb_demo?authSource=admin"
        );
    }
}

package com.estore.estorebackend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration

@EnableJpaRepositories(
        basePackages = "com.estore.estorebackend.repository.jpa"
)

@EnableMongoRepositories(
        basePackages = "com.estore.estorebackend.repository.mongo"
)

public class DatabaseConfig {
}
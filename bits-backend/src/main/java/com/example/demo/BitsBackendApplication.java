package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.demo", "com.example.inventory"})
@EntityScan(basePackages = "entities")
@EnableJpaRepositories(basePackages = "com.example.inventory.repository")
public class BitsBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(BitsBackendApplication.class, args);
    }
}
package com.example.course_listing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.course_listing") // ✅ Ensure this matches CourseRepo package
@EnableMongoRepositories(basePackages = "com.example.course_listing.CourseRepository") // ✅ Ensure this matches CourseRepo package
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}

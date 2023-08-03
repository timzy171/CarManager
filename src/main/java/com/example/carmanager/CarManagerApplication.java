package com.example.carmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class},scanBasePackages = {"java"})
@EnableJpaRepositories("com.example.carmanager.repo")
public class CarManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarManagerApplication.class, args);
    }

}

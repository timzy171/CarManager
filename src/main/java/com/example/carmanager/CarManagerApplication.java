package com.example.carmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class CarManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarManagerApplication.class, args);
    }

}

package com.example.socialmediabackenddemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class SocialMediaBackendDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SocialMediaBackendDemoApplication.class, args);
    }
    @GetMapping("/hello")
    public String sayHello() {
        return String.format("Hello again!");
    }

}

package com.example.springbootgradle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
@SpringBootApplication
public class DemoApplication {

    @GetMapping("/")
    private String html(Map<String, Object> map) {
        map.put("key", "springboot-gradle");
        return "templates";
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}

package com.com.example.logging_middleware.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    private final LoggingService loggingService;

    public TestController(LoggingService loggingService) {
        this.loggingService = loggingService;
    }

    @GetMapping("/test")
    public String test() {

        loggingService.log(
                "backend",
                "info",
                "handler",
                "Testing logger"
        );

        return "Logger working";
    }
}
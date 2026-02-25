package com.example.rest_service.getMessage;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstController {

    @GetMapping("/message")
    public String message(){
        return "Hello, API is working.";
    }

}

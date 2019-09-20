package com.escuelaing.arep.awsgatewaylambda.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class userController {

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }
}
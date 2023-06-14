package com.example.SwipeRest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CheckController {
    @GetMapping("/")
    public String helloTest(){
        return "Hello";
    }
    @GetMapping("/1")
    public String byeTest(){
        return "Bye";
    }
}

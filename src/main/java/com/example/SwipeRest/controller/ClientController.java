package com.example.SwipeRest.controller;

import com.example.SwipeRest.entity.User;
import com.example.SwipeRest.entity.UserAddInfo;
import com.example.SwipeRest.enums.TypeUser;
import com.example.SwipeRest.repository.UserRepo;
import com.example.SwipeRest.service.impl.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/client")
public class ClientController {
    private final UserAddInfoServiceImpl apartmentService;
    private final UserServiceImpl userService;
    private final NewsServiceImpl newsService;
    private final AgentServiceImpl agentService;
//    @GetMapping("/all")
//    public ResponseEntity fc(){
//        return ResponseEntity.ok(userService.findAllByType(TypeUser.CLIENT));
//    }

    @GetMapping("/all")
    public ResponseEntity fc(){
        return ResponseEntity.ok(userService.findAll());
    }

}

package com.example.SwipeRest.controller;

import com.example.SwipeRest.service.impl.ApartmentServiceImpl;
import com.example.SwipeRest.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/black/list")
@RequiredArgsConstructor
public class BlackListController {
    private final UserServiceImpl userService;

    @GetMapping("/all")
    public ResponseEntity findAllBlackList(){
        return ResponseEntity.ok(userService.blackListDTO());
    }

}

package com.example.SwipeRest.controller;

import com.example.SwipeRest.mapper.LcdMapper;
import com.example.SwipeRest.service.impl.LCDServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/lcd")
@RequiredArgsConstructor
public class LcdController {
    private final LCDServiceImpl lcdService;

    @GetMapping("/all")
    public ResponseEntity findAllLcd(){
        return ResponseEntity.ok(lcdService.findAllDTO());
    }

    @GetMapping("/{id}")
    public ResponseEntity findByIdLcd(@PathVariable int id){
        return ResponseEntity.ok(lcdService.findByIdDTO(id));
    }
}

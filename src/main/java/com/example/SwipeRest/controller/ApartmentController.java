package com.example.SwipeRest.controller;

import com.example.SwipeRest.service.impl.ApartmentServiceImpl;
import com.example.SwipeRest.service.impl.LCDServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/apartment")
@RequiredArgsConstructor
public class ApartmentController {
    private final ApartmentServiceImpl apartmentService;

    @GetMapping("/all")
    public ResponseEntity findAllApartment(){
        return ResponseEntity.ok(apartmentService.findAllDTO());
    }

    @GetMapping("/{id}")
    public ResponseEntity findByIdLcd(@PathVariable int id){
        return ResponseEntity.ok(apartmentService.findByIdDTO(id));
    }
}

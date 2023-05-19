package com.example.SwipeRest.controller;

import com.example.SwipeRest.dto.UserDTO;
import com.example.SwipeRest.entity.User;
import com.example.SwipeRest.entity.UserAddInfo;
import com.example.SwipeRest.enums.Role;
import com.example.SwipeRest.enums.TypeUser;
import com.example.SwipeRest.repository.UserRepo;
import com.example.SwipeRest.service.impl.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/client")
public class ClientController {
    private final ApartmentServiceImpl apartmentService;
    private final UserServiceImpl userService;
    private final NewsServiceImpl newsService;
    private final AgentServiceImpl agentService;
    private final FrameServiceImpl frameService;
    private final LCDServiceImpl lcdService;
    @GetMapping("/all")
    public ResponseEntity findAllClient(){
        return ResponseEntity.ok(userService.findAllByType(TypeUser.CLIENT));
    }
    @GetMapping("/{id}")
    public ResponseEntity findByIdClient(@PathVariable int id){
        UserDTO user = userService.findByIdDTO(id);
        if (user!=null) {
//            if (user.getRole().equals(Role.USER)) {
                if (userService.findByIdDTO(id).getTypeUser().equals(TypeUser.CLIENT)) {
                    return ResponseEntity.ok(userService.findByIdDTO(id));
                } else {
                    return ResponseEntity.badRequest().body("User by this id isn't CLIENT");
                }
//            } else {
//                return ResponseEntity.badRequest().body("User by this id is ADMIN");
//            }
        }
        else {
            return ResponseEntity.badRequest().body("User by this id not found");
        }
    }
    @PostMapping("/add")
    public ResponseEntity addClient(@RequestBody UserDTO userDTO){
        return ResponseEntity.ok(userService.addDTO(userDTO));
    }

//    @GetMapping("/all")
//    public ResponseEntity fc(){
//        return ResponseEntity.ok(userService.findAll());
//    }

}

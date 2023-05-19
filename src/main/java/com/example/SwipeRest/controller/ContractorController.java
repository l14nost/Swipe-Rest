package com.example.SwipeRest.controller;

import com.example.SwipeRest.dto.UserDTO;
import com.example.SwipeRest.entity.User;
import com.example.SwipeRest.enums.Role;
import com.example.SwipeRest.enums.TypeUser;
import com.example.SwipeRest.service.impl.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/contractor")
public class ContractorController {

    private final UserServiceImpl userService;
    @GetMapping("/all")
    public ResponseEntity findAllContractor(){
        return ResponseEntity.ok(userService.findAllByType(TypeUser.CONTRACTOR));
    }
    @GetMapping("/{id}")
    public ResponseEntity findByIdContractor(@PathVariable int id){
        UserDTO user = userService.findByIdDTO(id);
        if (user!=null) {
//            if (user.getRole().equals(Role.USER)) {
                if (userService.findById(id).getTypeUser().equals(TypeUser.CONTRACTOR)) {
                    return ResponseEntity.ok(userService.findByIdDTO(id));
                } else {
                    return ResponseEntity.badRequest().body("User by this id isn't CONTRACTOR");
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
    public ResponseEntity addContractor(@RequestBody UserDTO userDTO){
        return ResponseEntity.ok(userService.addDTO(userDTO));
    }
//    @GetMapping("/all")
//    public ResponseEntity fc(){
//        return ResponseEntity.ok(userService.findAll());
//    }

}

package com.example.SwipeRest.controller;

import com.example.SwipeRest.dto.ClientDTO;
import com.example.SwipeRest.enums.TypeUser;
import com.example.SwipeRest.service.impl.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/black/list")
@RequiredArgsConstructor
@Tag(name = "Blacklist")
public class BlackListController {
    private Logger log = LoggerFactory.getLogger(BlackListController.class);
    private final UserServiceImpl userService;
    @Operation(summary = "Get all users who are blacklisted")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "OK"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "401",description = "Unauthorized")
    })
    @GetMapping("/all")
    public ResponseEntity findAllBlackList(){
        log.info("Request blacklist");
        return ResponseEntity.ok(userService.blackListDTO());
    }
    @Operation(summary = "Add user to blacklist")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "OK"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "401",description = "Unauthorized")
    })
    @PostMapping("/add/{id}")
    public ResponseEntity addToBlackList(@PathVariable int id){
        if (id<0){
            return ResponseEntity.badRequest().body("Id cannot be negative");
        }
        ClientDTO user = userService.findByIdDTO(id);
        if (user!=null) {
            if (user.getUserType().equals(TypeUser.NOTARY)) {
                log.info("User not valid for add to blacklist");
                return ResponseEntity.badRequest().body("User by this id is Notary");
            } else {
                if (user.isBlackList()){
                    log.info("User not valid for add to blacklist");
                    return ResponseEntity.badRequest().body("User already in blacklist");
                }
                log.info("Request add to blacklist");
                userService.addToBlackList(id);
                return ResponseEntity.ok("Success add");
            }
        }
        log.info("User not found "+id);
        return ResponseEntity.badRequest().body("User not found");
    }

    @Operation(summary = "Remove user from blacklist")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "OK"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "401",description = "Unauthorized")
    })
    @PostMapping("/remove/{id}")
    public ResponseEntity removeFromBlackList(@PathVariable int id){
        if (id<0){
            return ResponseEntity.badRequest().body("Id cannot be negative");
        }
        ClientDTO user = userService.findByIdDTO(id);
        if (user!=null) {
            if (user.getUserType().equals(TypeUser.NOTARY)) {
                log.info("User not valid for add to blacklist");
                return ResponseEntity.badRequest().body("User by this id is Notary");
            } else {
                if (!user.isBlackList()){
                    log.info("User not valid for remove to blacklist");
                    return ResponseEntity.badRequest().body("User isn't in blacklist");
                }
                log.info("Request add to blacklist");
                userService.removeFromBlackList(id);
                return ResponseEntity.ok("Success remove");
            }
        }
        log.info("User not found "+id);
        return ResponseEntity.badRequest().body("User not found");
    }
}

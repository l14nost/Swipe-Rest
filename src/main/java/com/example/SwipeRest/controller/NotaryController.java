package com.example.SwipeRest.controller;

import com.example.SwipeRest.dto.ClientDTO;
import com.example.SwipeRest.enums.Role;
import com.example.SwipeRest.enums.TypeUser;
import com.example.SwipeRest.service.impl.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notary")
@Tag(name = "Notary")
public class NotaryController {
    private Logger log = LoggerFactory.getLogger(NotaryController.class);
    private final UserServiceImpl userService;
    @Operation(summary = "Get all notary")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "OK"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "401",description = "Unauthorized")
    })
    @GetMapping("/all")
    public ResponseEntity findAllNotary(){
        log.info("Request all Notary");
        return ResponseEntity.ok(userService.findAllByType(TypeUser.NOTARY));
    }
    @Operation(summary = "Get notary by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "OK"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "401",description = "Unauthorized")
    })
    @GetMapping("/{id}")
    public ResponseEntity findByIdNotary(@PathVariable @Schema(example = "6") int id){
        ClientDTO user = userService.findByIdDTO(id);
        if (user!=null) {
            if (user.getRole().equals(Role.USER)) {
                if (user.getTypeUser().equals(TypeUser.NOTARY)) {
                    log.info("Request find Notary " + id);
                    return ResponseEntity.ok(userService.findByIdDTO(id));
                } else {
                    log.info("User " + id + "not Notary");
                    return ResponseEntity.badRequest().body("User by this id isn't NOTARY");
                }
            }
            else return ResponseEntity.badRequest().body("User by this id ADMIN");
        }
        else {
            log.info("User not found "+id);
            return ResponseEntity.badRequest().body("User by this id not found");
        }
    }
    @Operation(summary = "Add notary")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "OK"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "401",description = "Unauthorized")
    })
    @PostMapping("/add")
    public ResponseEntity addNotary(@Valid @RequestBody @Schema(
            example = "{\n" +
                    "  \"mail\": \"mail@gmail.com\",\n" +
                    "  \"name\": \"Name\",\n" +
                    "  \"surname\": \"Surname\",\n" +
                    "  \"fileName\": \"../admin/dist/img/default.jpg\",\n" +
                    "  \"number\": \"123123231\",\n" +
                    "  \"agent\": null,\n" +
                    "  \"typeUser\": \"NOTARY\",\n" +
                    "  \"role\": \"USER\",\n" +
                    " \"userAddInfo\": null,\n" +
                    "  \"blackList\": false\n" +
                    "}")ClientDTO clientDTO){
        log.info("Request save Notary");
        return ResponseEntity.ok(userService.addDTO(clientDTO));
    }
    @Operation(summary = "Delete notary by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "OK"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "401",description = "Unauthorized")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteClient(@PathVariable @Schema(example = "0") int id){
        ClientDTO clientDTO = userService.findByIdDTO(id);
        if (clientDTO != null) {
            if (clientDTO.getRole().equals(Role.USER)) {
                if (clientDTO.getTypeUser().equals(TypeUser.NOTARY)) {
                    log.info("Request delete Notary " + id);
                    userService.deleteById(id);
                    return ResponseEntity.ok("Success delete:" + clientDTO);
                } else {
                    log.info("User " + id + "not Notary");
                    return ResponseEntity.badRequest().body("User by this id isn't NOTARY");
                }
            }
            else return ResponseEntity.badRequest().body("User by this id ADMIN");

        }
        else {
            log.info("User not found "+id);
            return ResponseEntity.badRequest().body("User NOT found");
        }
    }
    @Operation(summary = "Update notary by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "OK"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "401",description = "Unauthorized")
    })
    @PutMapping("/update/{id}")
    public ResponseEntity updateClient(@PathVariable @Schema(example = "6") int id, @Valid @RequestBody @Schema(
            example = "{\n" +
                    "  \"mail\": \"mail@gmail.com\",\n" +
                    "  \"name\": \"Notary\",\n" +
                    "  \"surname\": \"Surname\",\n" +
                    "  \"fileName\": \"../admin/dist/img/default.jpg\",\n" +
                    "  \"number\": \"123123231\",\n" +
                    "  \"agent\": null,\n" +
                    "  \"typeUser\": \"NOTARY\",\n" +
                    "  \"role\": \"USER\",\n" +
                    " \"userAddInfo\": null,\n" +
                    "  \"blackList\": false\n" +
                    "}") ClientDTO clientDTO){
        ClientDTO client = userService.findByIdDTO(id);
        if(client!=null) {
            if (client.getRole().equals(Role.USER)) {
                if (client.getTypeUser().equals(TypeUser.NOTARY)) {
                    log.info("Request update Notary " + id);
                    return ResponseEntity.ok(userService.updateDto(clientDTO, id));
                } else {
                    log.info("User " + id + "not Notary");
                    return ResponseEntity.badRequest().body("User by this id isn't NOTARY");
                }
            }
            else return ResponseEntity.badRequest().body("User by this id ADMIN");
        }
        else {
            log.info("User not found "+id);
            return ResponseEntity.badRequest().body("User NOT found");
        }

    }

}

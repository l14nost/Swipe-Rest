package com.example.SwipeRest.controller;

import com.example.SwipeRest.dto.ClientDTO;
import com.example.SwipeRest.enums.Role;
import com.example.SwipeRest.enums.TypeUser;
import com.example.SwipeRest.service.impl.AgentServiceImpl;
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
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/client")
@Tag(name = "Client")
public class ClientController {
    private Logger log = LoggerFactory.getLogger(ClientController.class);
    private final UserServiceImpl userService;
    private final AgentServiceImpl agentService;
    @Operation(summary = "Get all client")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "OK"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "401",description = "Unauthorized")
    })
    @GetMapping("/all")
    public ResponseEntity findAllClient(){
        log.info("Request all Client");
        return ResponseEntity.ok(userService.findAllByType(TypeUser.CLIENT));
    }
    @Operation(summary = "Get client by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "OK"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "401",description = "Unauthorized")
    })
    @GetMapping("/{id}")
    public ResponseEntity findByIdClient(@PathVariable @Schema(example = "4") int id){
        if (id<0){
            return ResponseEntity.badRequest().body("Id cannot be negative");
        }
        ClientDTO user = userService.findByIdDTO(id);
        if (user!=null) {
            if (user.getRole().equals(Role.USER)) {
                if (user.getUserType().equals(TypeUser.CLIENT)) {
                    log.info("Request find Client " + id);
                    return ResponseEntity.ok(userService.findByIdDTO(id));
                } else {
                    log.info("User " + id + "not Client");
                    return ResponseEntity.badRequest().body("User by this id isn't CLIENT");
                }
            }
            else return ResponseEntity.badRequest().body("User by this id ADMIN");
        }
        else {
            log.info("User not found "+id);
            return ResponseEntity.badRequest().body("User by this id not found");
        }
    }
    @Operation(summary = "Add client")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "OK"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "401",description = "Unauthorized")
    })
    @PostMapping("/add")
    public ResponseEntity addClient(@Valid @RequestBody  @Schema(
            example = "{\n" +
                    "  \"email\": \"mail@gmail.com\",\n" +
                    "  \"name\": \"Name\",\n" +
                    "  \"surname\": \"Surname\",\n" +
                    "  \"fileName\": \"../admin/dist/img/default.jpg\",\n" +
                    "  \"number\": \"123123231\",\n" +
                    "  \"agent\": {\n" +
                    "    \"email\": \"mail@gmail.com\",\n" +
                    "    \"name\": \"Agent\",\n" +
                    "    \"number\": \"123123123\",\n" +
                    "    \"surname\": \"Agentov\",\n" +
                    "    \"agentType\": \"SALES\"\n" +
                    "  },\n" +
                    "  \"userType\": \"CLIENT\",\n" +
                    "  \"role\": \"USER\",\n" +
                    " \"userAddInfo\": {\n" +
                    "    \"callSms\": true,\n" +
                    "    \"dateSub\": \"2023-05-05\",\n" +
                    "    \"notificationType\": \"ME\"\n" +
                    "  },\n" +
                    "  \"blackList\": false\n" +
                    "}")ClientDTO clientDTO, BindingResult result){
        log.info("Request save Client");
        if (clientDTO.getRole()!=null) {
            if (!clientDTO.getRole().equals(Role.USER)) {
                result.addError(new FieldError("clientDTO", "role", "Access denied role must be USER"));
            }
            if (clientDTO.getUserType() != TypeUser.CLIENT) {
                result.addError(new FieldError("clientDTO", "typeUser", "User must be client"));
            }
        }
        result = userService.uniqueMail(clientDTO.getEmail(),result,0,"add","clientDTO");
        if (clientDTO.getAgent()!=null) {
            result = agentService.uniqueEmail(clientDTO.getAgent().getEmail(), result, 0, "add", "agent");
        }
        if (result.hasErrors()){
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(Map.of("errors", errors));
        }
        return ResponseEntity.ok(userService.addDTO(clientDTO));
    }
    @Operation(summary = "Delete client by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "OK"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "401",description = "Unauthorized")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteClient(@PathVariable @Schema(example = "0") int id){
        if (id<0){
            return ResponseEntity.badRequest().body("Id cannot be negative");
        }
        ClientDTO clientDTO = userService.findByIdDTO(id);
        if (clientDTO != null) {
            if (clientDTO.getRole().equals(Role.USER)) {
                if (clientDTO.getUserType().equals(TypeUser.CLIENT)) {
                    log.info("Request delete Client " + id);
                    userService.deleteById(id);
                    return ResponseEntity.ok("Success delete:" + clientDTO);
                } else {
                    log.info("User " + id + "not Client");
                    return ResponseEntity.badRequest().body("User by this id isn't CLIENT");
                }
            }
            else return ResponseEntity.badRequest().body("User by this id ADMIN");
        }
        else {
            log.info("User not found "+id);
            return ResponseEntity.badRequest().body("User NOT found");
        }
    }
    @Operation(summary = "Update client by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "OK"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "401",description = "Unauthorized")
    })
    @PutMapping("/update/{id}")
    public ResponseEntity updateClient(@PathVariable @Schema(example = "9") int id , @Valid @RequestBody @Schema(
            example = "{\n" +
                    "  \"email\": \"mail@gmail.com\",\n" +
                    "  \"name\": \"Name\",\n" +
                    "  \"surname\": \"Surname\",\n" +
                    "  \"fileName\": \"../admin/dist/img/default.jpg\",\n" +
                    "  \"number\": \"123123231\",\n" +
                    "  \"agent\": {\n" +
                    "    \"email\": \"mail@gmail.com\",\n" +
                    "    \"name\": \"Agent\",\n" +
                    "    \"number\": \"123123123\",\n" +
                    "    \"surname\": \"Agentov\",\n" +
                    "    \"agentType\": \"SALES\"\n" +
                    "  },\n" +
                    "  \"userType\": \"CLIENT\",\n" +
                    "  \"role\": \"USER\",\n" +
                    " \"userAddInfo\": {\n" +
                    "    \"callSms\": true,\n" +
                    "    \"dateSub\": \"2023-05-05\",\n" +
                    "    \"notificationType\": \"ME\"\n" +
                    "  },\n" +
                    "  \"blackList\": false\n" +
                    "}") ClientDTO clientDTO,BindingResult result){
        if (id<0){
            return ResponseEntity.badRequest().body("Id cannot be negative");
        }
        if (clientDTO.getRole() == null){
            return ResponseEntity.badRequest().body("Role cannot be null");
        }
        ClientDTO client = userService.findByIdDTO(id);
        if(client!=null){
            if (client.getRole().equals(Role.USER)) {
                if (client.getUserType().equals(TypeUser.CLIENT)) {
                    log.info("Request update Client " + id);
                    result = userService.uniqueMail(clientDTO.getEmail(),result,id,"update","clientDTO");
                    if (clientDTO.getAgent()!=null) {
                        result = agentService.uniqueEmail(clientDTO.getAgent().getEmail(), result, userService.findById(id).getAgent().getIdAgent(), "update", "agent");
                    }
                    if (result.hasErrors()){
                        List<String> errors = result.getFieldErrors()
                                .stream()
                                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                                .collect(Collectors.toList());
                        return ResponseEntity.badRequest().body(Map.of("errors", errors));
                    }
                    return ResponseEntity.ok(userService.updateDto(clientDTO, id));
                } else {
                    log.info("User " + id + "not Client");
                    return ResponseEntity.badRequest().body("User by this id isn't CLIENT");
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

package com.example.SwipeRest.controller;

import com.example.SwipeRest.dto.ClientDTO;
import com.example.SwipeRest.enums.Role;
import com.example.SwipeRest.enums.TypeUser;
import com.example.SwipeRest.service.impl.UserServiceImpl;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/client")
@Tag(name = "Client")
public class ClientController {
    private Logger log = LoggerFactory.getLogger(ClientController.class);
    private final UserServiceImpl userService;
    @GetMapping("/all")
    public ResponseEntity findAllClient(){
        log.info("Request all Client");
        return ResponseEntity.ok(userService.findAllByType(TypeUser.CLIENT));
    }
    @GetMapping("/{id}")
    public ResponseEntity findByIdClient(@PathVariable int id){
        ClientDTO user = userService.findByIdDTO(id);
        if (user!=null) {
            if (user.getRole().equals(Role.USER)) {
                if (user.getTypeUser().equals(TypeUser.CLIENT)) {
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
    @PostMapping("/add")
    public ResponseEntity addClient( @Valid @RequestBody  @Schema(
            example = "{\n" +
                    "  \"mail\": \"mail@gmail.com\",\n" +
                    "  \"name\": \"Name\",\n" +
                    "  \"surname\": \"Surname\",\n" +
                    "  \"fileName\": \"../admin/dist/img/default.jpg\",\n" +
                    "  \"number\": \"123123231\",\n" +
                    "  \"agent\": {\n" +
                    "    \"mail\": \"mail@gmail.com\",\n" +
                    "    \"name\": \"Agent\",\n" +
                    "    \"number\": \"123123123\",\n" +
                    "    \"surname\": \"Agentov\",\n" +
                    "    \"typeAgent\": \"SALES\"\n" +
                    "  },\n" +
                    "  \"typeUser\": \"CLIENT\",\n" +
                    "  \"role\": \"USER\",\n" +
                    " \"userAddInfo\": {\n" +
                    "    \"callSms\": true,\n" +
                    "    \"dateSub\": \"2023-05-05\",\n" +
                    "    \"typeNotification\": \"ME\"\n" +
                    "  },\n" +
                    "  \"blackList\": false\n" +
                    "}")ClientDTO clientDTO){
        log.info("Request save Client");
        return ResponseEntity.ok(userService.addDTO(clientDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteClient(@PathVariable @Schema(example = "92") int id){
        ClientDTO clientDTO = userService.findByIdDTO(id);
        if (clientDTO != null) {
            if (clientDTO.getRole().equals(Role.USER)) {
                if (clientDTO.getTypeUser().equals(TypeUser.CLIENT)) {
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
    @PutMapping("/update/{id}")
    public ResponseEntity updateClient(@PathVariable int id, @Valid @RequestBody @Schema(
            example = "{\n" +
                    "  \"mail\": \"mail@gmail.com\",\n" +
                    "  \"name\": \"Name\",\n" +
                    "  \"surname\": \"Surname\",\n" +
                    "  \"fileName\": \"../admin/dist/img/default.jpg\",\n" +
                    "  \"number\": \"123123231\",\n" +
                    "  \"agent\": {\n" +
                    "    \"mail\": \"mail@gmail.com\",\n" +
                    "    \"name\": \"Agent\",\n" +
                    "    \"number\": \"123123123\",\n" +
                    "    \"surname\": \"Agentov\",\n" +
                    "    \"typeAgent\": \"SALES\"\n" +
                    "  },\n" +
                    "  \"typeUser\": \"CLIENT\",\n" +
                    "  \"role\": \"USER\",\n" +
                    " \"userAddInfo\": {\n" +
                    "    \"callSms\": true,\n" +
                    "    \"dateSub\": \"2023-05-05\",\n" +
                    "    \"typeNotification\": \"ME\"\n" +
                    "  },\n" +
                    "  \"blackList\": false\n" +
                    "}") ClientDTO clientDTO){
        ClientDTO client = userService.findByIdDTO(id);
        if(client!=null){
            if (client.getRole().equals(Role.USER)) {
                if (client.getTypeUser().equals(TypeUser.CLIENT)) {
                    log.info("Request update Client " + id);
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

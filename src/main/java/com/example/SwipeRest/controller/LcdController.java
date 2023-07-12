package com.example.SwipeRest.controller;

import com.example.SwipeRest.dto.LcdDTO;
import com.example.SwipeRest.service.FrameService;
import com.example.SwipeRest.service.impl.FrameServiceImpl;
import com.example.SwipeRest.service.impl.LCDServiceImpl;
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
@RequestMapping("/api/lcd")
@RequiredArgsConstructor
@Tag(name = "LCD")
public class LcdController {
    private Logger log = LoggerFactory.getLogger(LcdController.class);
    private final LCDServiceImpl lcdService;
    private final UserServiceImpl userService;
    @Operation(summary = "Get all lcd")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "OK"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "401",description = "Unauthorized")
    })
    @GetMapping("/all")
    public ResponseEntity findAllLcd(){

        log.info("Request all lcd");
        return ResponseEntity.ok(lcdService.findAllDTO());
    }
    @Operation(summary = "Get lcd by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "OK"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "401",description = "Unauthorized")
    })
    @GetMapping("/{id}")
    public ResponseEntity findByIdLcd(@PathVariable @Schema(example = "9") int id) {
        if (id<0){
            return ResponseEntity.badRequest().body("Id cannot be negative");
        }
        LcdDTO lcdDTO = lcdService.findByIdDTO(id);
        if (lcdDTO != null) {
            log.info("Request find lcd " + id);
            return ResponseEntity.ok(lcdService.findByIdDTO(id));
        } else {
            log.info("Lcd not found "+id);
            return ResponseEntity.badRequest().body("LCD not found");
        }
    }
    @Operation(summary = "Add lcd")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "OK"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "401",description = "Unauthorized")
    })
    @PostMapping("/add")
    public ResponseEntity saveLcd(@RequestBody @Valid LcdDTO lcdDTO){
        log.info("Request save lcd");
        lcdDTO.setId(0);
        lcdDTO.getPhotos().forEach(photoDTO -> photoDTO.setId(0));
        lcdDTO.getDocuments().forEach(documentDTO -> documentDTO.setId(0));
        lcdDTO.getFrames().forEach(frameDTO -> { frameDTO.setId(0);
            if (frameDTO.getApartments()!=null){
            frameDTO.getApartments().forEach(apartmentDTO -> {apartmentDTO.setId(0); apartmentDTO.setIdLcd(0);});}});
        return ResponseEntity.ok(lcdService.saveDTO(lcdDTO));
    }

    @Operation(summary = "Delete lcd by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "OK"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "401",description = "Unauthorized")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable @Schema(example = "0") int id){
        if (id<0){
            return ResponseEntity.badRequest().body("Id cannot be negative");
        }
        LcdDTO lcdDTO = lcdService.findByIdDTO(id);
        if (lcdDTO!=null) {
            log.info("Request delete lcd " + id);
            lcdService.deleteById(id);
            return ResponseEntity.ok("Success:\n"+lcdDTO);
        }
        else {
            log.info("Lcd not found "+id);
            return ResponseEntity.badRequest().body("Lcd not found");
        }
    }
    @Operation(summary = "Update lcd by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "OK"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "401",description = "Unauthorized")
    })
    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable @Schema(example = "1") int id, @RequestBody @Valid LcdDTO lcdDTO){
        if (id<0){
            return ResponseEntity.badRequest().body("Id cannot be negative");
        }
        if (userService.findById(lcdDTO.getContractor()) == null){
            return ResponseEntity.badRequest().body("idClient wrong");
        }
        LcdDTO lcd = lcdService.findByIdDTO(id);
        if (lcd!=null) {
            log.info("Request update lcd " + id);
            return ResponseEntity.ok(lcdService.updateDTO(lcdDTO,id));
        }
        else {
            log.info("Lcd not found "+id);
            return ResponseEntity.badRequest().body("Lcd not found");
        }

    }
}

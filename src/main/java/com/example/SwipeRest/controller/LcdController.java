package com.example.SwipeRest.controller;

import com.example.SwipeRest.dto.LcdDTO;
import com.example.SwipeRest.entity.LCD;
import com.example.SwipeRest.mapper.LcdMapper;
import com.example.SwipeRest.service.impl.LCDServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/lcd")
@RequiredArgsConstructor
@Tag(name = "LCD")
@Log4j2
public class LcdController {
    private final LCDServiceImpl lcdService;

    @GetMapping("/all")
    public ResponseEntity findAllLcd(){
        log.info("Request all lcd");
        return ResponseEntity.ok(lcdService.findAllDTO());
    }

    @GetMapping("/{id}")
    public ResponseEntity findByIdLcd(@PathVariable int id) {
        LcdDTO lcdDTO = lcdService.findByIdDTO(id);
        if (lcdDTO != null) {
            log.info("Request find lcd " + id);
            return ResponseEntity.ok(lcdService.findByIdDTO(id));
        } else {
            log.info("Lcd not found "+id);
            return ResponseEntity.badRequest().body("LCD not found");
        }
    }
    @PostMapping("/add")
    public ResponseEntity saveLcd(@RequestBody LcdDTO lcdDTO){
        log.info("Request save lcd");
        return ResponseEntity.ok(lcdService.saveDTO(lcdDTO));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable int id){
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
    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable int id, @RequestBody @Valid LcdDTO lcdDTO){
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

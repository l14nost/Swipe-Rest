package com.example.SwipeRest.controller;

import com.example.SwipeRest.dto.LcdDTO;
import com.example.SwipeRest.service.impl.LCDServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
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
    public ResponseEntity saveLcd(@RequestBody @Valid LcdDTO lcdDTO){
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

package com.example.SwipeRest.controller;

import com.example.SwipeRest.dto.ApartmentDTO;
import com.example.SwipeRest.service.impl.ApartmentServiceImpl;
import com.example.SwipeRest.service.impl.LCDServiceImpl;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/apartment")
@RequiredArgsConstructor
@Tag(name = "Apartment")
@Log4j2
public class ApartmentController {
    private final ApartmentServiceImpl apartmentService;

    @GetMapping("/all")
    public ResponseEntity findAllApartment(){
        log.info("Request all apartment");
        return ResponseEntity.ok(apartmentService.findAllDTO());
    }

    @GetMapping("/{id}")
    public ResponseEntity findByIdApartment(@PathVariable int id){
        ApartmentDTO apartmentDTO = apartmentService.findByIdDTO(id);
        if (apartmentDTO!=null) {
            log.info("Request find apartment "+id);
            return ResponseEntity.ok(apartmentDTO);
        }
        else {
            log.info("Apartment not found " + id);
            return ResponseEntity.badRequest().body("Apartment not found");
        }
    }
    @PostMapping("/add")
    public ResponseEntity saveApartment(@RequestBody @Valid ApartmentDTO apartmentDTO){
        log.info("Request save apartment");
        return ResponseEntity.ok("Success:\n"+apartmentService.saveDTO(apartmentDTO));
    }
    @DeleteMapping("/delete/{id}")
    public  ResponseEntity deleteApartment(@PathVariable int id){
        ApartmentDTO apartmentDTO = apartmentService.findByIdDTO(id);
        if (apartmentDTO!=null){
            log.info("Request delete apartment "+id);
            apartmentService.deleteById(id);
            return ResponseEntity.ok("Success:\n"+apartmentDTO);
        }

        else {log.info("Apartment not found "+id);
            return ResponseEntity.badRequest().body("Apartment not found");}

    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateApartment(@PathVariable int id, @RequestBody @Valid ApartmentDTO apartmentDTO){
        ApartmentDTO apartmentDTO1 = apartmentService.findByIdDTO(id);
        if (apartmentDTO1!=null){
            log.info("Request update apartment "+id);
            return ResponseEntity.ok("Success:\n"+apartmentService.updateDto(apartmentDTO,id));
        }
        else{ log.info("Apartment not found "+id);
            return ResponseEntity.badRequest().body("Apartment not found");}
    }
}

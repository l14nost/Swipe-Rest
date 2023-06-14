package com.example.SwipeRest.controller;

import com.example.SwipeRest.dto.ApartmentDTO;
import com.example.SwipeRest.service.impl.ApartmentServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/apartment")
@RequiredArgsConstructor
@Tag(name = "Apartment")
public class ApartmentController {
    private final ApartmentServiceImpl apartmentService;
    private Logger log = LoggerFactory.getLogger(ApartmentController.class);
    @Operation(summary = "Get all apartment")
    @GetMapping("/all")
    public ResponseEntity findAllApartment(){
        log.info("Request all apartment");
        return ResponseEntity.ok(apartmentService.findAllDTO());
    }
    @Operation(summary = "Find apartment by id")
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
    @Operation(summary = "Add apartment")
    @PostMapping("/add")
    public ResponseEntity saveApartment(@RequestBody @Valid ApartmentDTO apartmentDTO){
        log.info("Request save apartment");
        return ResponseEntity.ok("Success:\n"+apartmentService.saveDTO(apartmentDTO));
    }
    @Operation(summary = "Delete apartment by id")
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
    @Operation(summary = "Update apartment by id")
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

    @GetMapping("/")
    public String hello(){
        return "Hello";
    }
}

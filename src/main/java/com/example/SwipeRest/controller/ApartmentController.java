package com.example.SwipeRest.controller;

import com.example.SwipeRest.dto.ApartmentDTO;
import com.example.SwipeRest.service.impl.ApartmentServiceImpl;
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
@RequestMapping("/api/apartment")
@RequiredArgsConstructor
@Tag(name = "Apartment")
public class ApartmentController {
    private final ApartmentServiceImpl apartmentService;
    private final LCDServiceImpl lcdService;
    private final UserServiceImpl userService;
    private Logger log = LoggerFactory.getLogger(ApartmentController.class);
    @Operation(summary = "Get all apartment")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "OK"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "401",description = "Unauthorized")
    })

    @GetMapping("/all")
    public ResponseEntity findAllApartment(){
        log.info("Request all apartment");
        return ResponseEntity.ok(apartmentService.findAllDTO());
    }
    @Operation(summary = "Find apartment by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "OK"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "401",description = "Unauthorized")
    })
    @GetMapping("/{id}")
    public ResponseEntity findByIdApartment(@PathVariable @Schema(example = "2")int id){
        if (id<0){
            return ResponseEntity.badRequest().body("Id cannot be negative");
        }
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
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "OK"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "401",description = "Unauthorized")
    })
    @PostMapping("/add")
    public ResponseEntity saveApartment(@RequestBody @Valid ApartmentDTO apartmentDTO){
        log.info("Request save apartment");
        apartmentDTO.setId(0);
        apartmentDTO.getPhotos().forEach(photoDTO -> {photoDTO.setId(0);});
        if (lcdService.findById(apartmentDTO.getIdLcd()) == null){
            return ResponseEntity.badRequest().body("idLcd wrong");
        }
        if (userService.findById(apartmentDTO.getClient()) == null){
            return ResponseEntity.badRequest().body("idClient wrong");
        }
        return ResponseEntity.ok("Success:\n"+apartmentService.saveDTO(apartmentDTO));
    }
    @Operation(summary = "Delete apartment by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "OK"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "401",description = "Unauthorized")
    })
    @DeleteMapping("/delete/{id}")
    public  ResponseEntity deleteApartment(@PathVariable @Schema(example = "0") int id){
        if (id<0){
            return ResponseEntity.badRequest().body("Id cannot be negative");
        }
        ApartmentDTO apartmentDTO = apartmentService.findByIdDTO(id);
        if (apartmentDTO!=null){
            log.info("Request delete apartment "+id);
            apartmentService.deleteById(id);
            return ResponseEntity.ok("Success");
        }

        else {log.info("Apartment not found "+id);
            return ResponseEntity.badRequest().body("Apartment not found");}

    }
    @Operation(summary = "Update apartment by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "OK"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "401",description = "Unauthorized")
    })
    @PutMapping("/update/{id}")
    public ResponseEntity updateApartment(@PathVariable @Schema(example = "2") int id, @RequestBody @Valid ApartmentDTO apartmentDTO){
        if (id<0){
            return ResponseEntity.badRequest().body("Id cannot be negative");
        }
        if (lcdService.findById(apartmentDTO.getIdLcd()) == null){
            return ResponseEntity.badRequest().body("idLcd wrong");
        }
        if (userService.findById(apartmentDTO.getClient()) == null){
            return ResponseEntity.badRequest().body("idClient wrong");
        }
        ApartmentDTO apartmentDTO1 = apartmentService.findByIdDTO(id);
        if (apartmentDTO1!=null){
            log.info("Request update apartment "+id);
            return ResponseEntity.ok("Success:\n"+apartmentService.updateDto(apartmentDTO,id));
        }
        else{ log.info("Apartment not found "+id);
            return ResponseEntity.badRequest().body("Apartment not found");}
    }
}

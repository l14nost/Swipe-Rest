package com.example.SwipeRest.controller;

import com.example.SwipeRest.dto.ApartmentDTO;
import com.example.SwipeRest.enums.*;
import com.example.SwipeRest.repository.ApartmentRepo;
import com.example.SwipeRest.service.impl.ApartmentServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ApartmentControllerTest {
    @Mock
    private ApartmentServiceImpl apartmentService;
    @Mock
    private ApartmentRepo apartmentRepo;
    @InjectMocks
    private ApartmentController apartmentController;
    @Test
    void findAllApartment() {
        List<ApartmentDTO> apartmentDTOS = List.of(
                ApartmentDTO.builder().build(),
                ApartmentDTO.builder().build(),
                ApartmentDTO.builder().build()
        );
        when(apartmentService.findAllDTO()).thenReturn(apartmentDTOS);

        ResponseEntity response = apartmentController.findAllApartment();

        assertNotNull(response);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(apartmentDTOS, response.getBody());
    }

    @Test
    void findByIdApartment() {
        ApartmentDTO apartmentDTO = ApartmentDTO.builder()
                .idApartment(1)
                .number(123)
                .build();
        when(apartmentService.findByIdDTO(1)).thenReturn(apartmentDTO);
        ResponseEntity response = apartmentController.findByIdApartment(1);
        assertNotNull(response);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(apartmentDTO, response.getBody());
    }
    @Test
    void findByIdApartment_NotFound() {
        ResponseEntity response = apartmentController.findByIdApartment(1);
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
        assertEquals("Apartment not found", response.getBody());
    }
    @Test
    void findByIdLcd() {
        ApartmentDTO apartmentDTO = ApartmentDTO.builder()
                .idApartment(1)
                .number(123)
                .build();
        when(apartmentService.findByIdDTO(1)).thenReturn(apartmentDTO);
        ResponseEntity response = apartmentController.findByIdApartment(1);
        assertNotNull(response);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(apartmentDTO, response.getBody());
    }

    @Test
    void saveApartment() {
        ApartmentDTO apartmentDTO = ApartmentDTO.builder()
                .number(101)
                .description("1")
                .totalArea(100)
                .type(TypeApartment.APARTMENT)
                .layout(LayoutType.STUDIO)
                .state(State.REPAIR)
                .price(100)
                .kitchenArea(101)
                .calculation(Calculation.CAPITAL)
                .foundingDocument(FoundingDocument.TREATY)
                .countRoom(CountRoom.K1)
                .communicationType(CommunicationType.SMS)
                .commission(Commission.K10)
                .balcony(BalconyType.NO)
                .heatingType(HeatingType.INDIVIDUAL)
                .mainPhoto("123")
                .build();
        when(apartmentService.saveDTO(apartmentDTO)).thenReturn("Success:\n"+apartmentDTO);
        ResponseEntity response = apartmentController.saveApartment(apartmentDTO);
        assertNotNull(response);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals("Success:\n"+apartmentDTO, response.getBody());
    }

    @Test
    void deleteApartment() {
        ApartmentDTO apartmentDTO = ApartmentDTO.builder()
                .idApartment(1)
                .number(101)
                .description("1")
                .totalArea(100)
                .type(TypeApartment.APARTMENT)
                .layout(LayoutType.STUDIO)
                .state(State.REPAIR)
                .price(100)
                .kitchenArea(101)
                .calculation(Calculation.CAPITAL)
                .foundingDocument(FoundingDocument.TREATY)
                .countRoom(CountRoom.K1)
                .communicationType(CommunicationType.SMS)
                .commission(Commission.K10)
                .balcony(BalconyType.NO)
                .heatingType(HeatingType.INDIVIDUAL)
                .mainPhoto("123")
                .build();
        when(apartmentService.findByIdDTO(1)).thenReturn(apartmentDTO);
        ResponseEntity response = apartmentController.deleteApartment(1);
        assertNotNull(response);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals("Success:\n"+apartmentDTO, response.getBody());

    }
    @Test
    void deleteApartment_NotFound(){
        ResponseEntity response = apartmentController.deleteApartment(1);
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
        assertEquals("Apartment not found", response.getBody());

    }

    @Test
    void updateApartment() {
        ApartmentDTO apartmentDTO = ApartmentDTO.builder()
                .idApartment(1)
                .number(101)
                .description("1")
                .totalArea(100)
                .type(TypeApartment.APARTMENT)
                .layout(LayoutType.STUDIO)
                .state(State.REPAIR)
                .price(100)
                .kitchenArea(101)
                .calculation(Calculation.CAPITAL)
                .foundingDocument(FoundingDocument.TREATY)
                .countRoom(CountRoom.K1)
                .communicationType(CommunicationType.SMS)
                .commission(Commission.K10)
                .balcony(BalconyType.NO)
                .heatingType(HeatingType.INDIVIDUAL)
                .mainPhoto("123")
                .build();
        when(apartmentService.findByIdDTO(1)).thenReturn(apartmentDTO);
        ApartmentDTO apartmentDTOUpdate = ApartmentDTO.builder()
                .idApartment(1)
                .number(121)
                .description("1")
                .totalArea(100)
                .type(TypeApartment.APARTMENT)
                .layout(LayoutType.STUDIO)
                .state(State.REPAIR)
                .price(120)
                .kitchenArea(101)
                .calculation(Calculation.CAPITAL)
                .foundingDocument(FoundingDocument.TREATY)
                .countRoom(CountRoom.K1)
                .communicationType(CommunicationType.SMS)
                .commission(Commission.K10)
                .balcony(BalconyType.NO)
                .heatingType(HeatingType.INDIVIDUAL)
                .mainPhoto("123")
                .build();
        when(apartmentService.updateDto(apartmentDTOUpdate,1)).thenReturn("Success:\n"+apartmentDTOUpdate);
        ResponseEntity response = apartmentController.updateApartment(1,apartmentDTOUpdate);
        assertNotNull(response);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals("Success:\n"+apartmentDTOUpdate, response.getBody());
    }
    @Test
    void updateApartment_NotFound() {
        ResponseEntity response = apartmentController.updateApartment(1,ApartmentDTO.builder().build());
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
        assertEquals("Apartment not found", response.getBody());
    }

}
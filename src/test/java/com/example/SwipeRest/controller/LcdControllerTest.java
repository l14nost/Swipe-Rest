package com.example.SwipeRest.controller;

import com.example.SwipeRest.dto.*;
import com.example.SwipeRest.enums.ClassType;
import com.example.SwipeRest.service.impl.LCDServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LcdControllerTest {
    @Mock
    private LCDServiceImpl lcdService;
    @InjectMocks
    private LcdController lcdController;
    @Test
    void findAllLcd() {
        List<LcdDTO> lcdDTOList= List.of(
                LcdDTO.builder().build(),
                LcdDTO.builder().build(),
                LcdDTO.builder().build(),
                LcdDTO.builder().build()
                );
        when(lcdService.findAllDTO()).thenReturn(lcdDTOList);
        ResponseEntity response = lcdController.findAllLcd();
        assertNotNull(response);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(lcdDTOList,response.getBody());
    }

    @Test
    void findByIdLcd() {
        LcdDTO lcdDTO = LcdDTO.builder().idLcd(1).name("NameLcd").build();
        when(lcdService.findByIdDTO(1)).thenReturn(lcdDTO);
        ResponseEntity response = lcdController.findByIdLcd(1);
        assertNotNull(response);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(lcdDTO,response.getBody());
    }
    @Test
    void findByIdLcd_NotFound() {
        ResponseEntity response = lcdController.findByIdLcd(1);
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
        assertEquals("LCD not found",response.getBody());
    }

    @Test
    void saveLcd() {
        LcdDTO lcdDTO = LcdDTO.builder()
                .name("Name")
                .classType(ClassType.MASS)
                .contractor(12)
                .photos(Arrays.asList(
                        PhotoDTO.builder().idPhoto(1).fileName("").build()
                ))
                .documents(Arrays.asList(
                        DocumentDTO.builder().idDocuments(1).fileName("").build()
                ))
                .frames(Arrays.asList(
                        FrameDTO.builder().idFrame(1).num(1).apartments(Arrays.asList(ApartmentDTO.builder().idApartment(1).number(115).build())).build()
                ))
                .build();
        when(lcdService.saveDTO(lcdDTO)).thenReturn("Success save");
        ResponseEntity response = lcdController.saveLcd(lcdDTO);
        assertNotNull(response);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals("Success save",response.getBody());
        verify(lcdService).saveDTO(lcdDTO);
    }

    @Test
    void delete() {
        LcdDTO lcdDTO = LcdDTO.builder()
                .idLcd(1)
                .name("Name")
                .classType(ClassType.MASS)
                .contractor(12)
                .photos(Arrays.asList(
                        PhotoDTO.builder().idPhoto(1).fileName("").build()
                ))
                .documents(Arrays.asList(
                        DocumentDTO.builder().idDocuments(1).fileName("").build()
                ))
                .frames(Arrays.asList(
                        FrameDTO.builder().idFrame(1).num(1).apartments(Arrays.asList(ApartmentDTO.builder().idApartment(1).number(115).build())).build()
                ))
                .build();
        when(lcdService.findByIdDTO(1)).thenReturn(lcdDTO);
        ResponseEntity response = lcdController.delete(1);
        assertNotNull(response);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals("Success:\n"+lcdDTO,response.getBody());
        verify(lcdService).deleteById(1);
    }
    @Test
    void delete_NotFound() {
        ResponseEntity response = lcdController.delete(1);
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
        assertEquals("Lcd not found",response.getBody());
        verify(lcdService, times(0)).deleteById(1);
    }

    @Test
    void update() {
        LcdDTO lcdDTO = LcdDTO.builder()
                .idLcd(1)
                .name("Name")
                .classType(ClassType.MASS)
                .contractor(12)
                .photos(Arrays.asList(
                        PhotoDTO.builder().idPhoto(1).fileName("").build()
                ))
                .documents(Arrays.asList(
                        DocumentDTO.builder().idDocuments(1).fileName("").build()
                ))
                .frames(Arrays.asList(
                        FrameDTO.builder().idFrame(1).num(1).apartments(Arrays.asList(ApartmentDTO.builder().idApartment(1).number(115).build())).build()
                ))
                .build();
        LcdDTO lcdDTOUpdate = LcdDTO.builder()
                .name("Name1")
                .classType(ClassType.MASS)
                .contractor(12)
                .photos(Arrays.asList(
                        PhotoDTO.builder().idPhoto(1).fileName("").build()
                ))
                .documents(Arrays.asList(
                        DocumentDTO.builder().idDocuments(1).fileName("").build()
                ))
                .frames(Arrays.asList(
                        FrameDTO.builder().idFrame(1).num(1).apartments(Arrays.asList(ApartmentDTO.builder().idApartment(1).number(115).build())).build()
                ))
                .build();
        LcdDTO lcdDTOSave = LcdDTO.builder()
                .idLcd(1)
                .name("Name1")
                .classType(ClassType.MASS)
                .contractor(12)
                .photos(Arrays.asList(
                        PhotoDTO.builder().idPhoto(1).fileName("").build()
                ))
                .documents(Arrays.asList(
                        DocumentDTO.builder().idDocuments(1).fileName("").build()
                ))
                .frames(Arrays.asList(
                        FrameDTO.builder().idFrame(1).num(1).apartments(Arrays.asList(ApartmentDTO.builder().idApartment(1).number(115).build())).build()
                ))
                .build();
        when(lcdService.findByIdDTO(1)).thenReturn(lcdDTO);
        when(lcdService.updateDTO(lcdDTOUpdate,1)).thenReturn("Success:\n"+lcdDTOSave);
        ResponseEntity response = lcdController.update(1,lcdDTOUpdate);
        assertNotNull(response);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals("Success:\n"+lcdDTOSave,response.getBody());
    }
    @Test
    void update_NotFound() {
        ResponseEntity response = lcdController.update(1,LcdDTO.builder().build());
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
        assertEquals("Lcd not found",response.getBody());
        verify(lcdService, times(0)).updateDTO(LcdDTO.builder().build(),1);
    }
}
package com.example.SwipeRest.controller;

import com.example.SwipeRest.config.JWTAuthenticationFilter;
import com.example.SwipeRest.dto.*;
import com.example.SwipeRest.entity.*;
import com.example.SwipeRest.enums.*;
import com.example.SwipeRest.mapper.LcdMapper;
import com.example.SwipeRest.service.impl.ApartmentServiceImpl;
import com.example.SwipeRest.service.impl.LCDServiceImpl;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


@WebMvcTest(LcdController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class LCDControllerTestMVC {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private LCDServiceImpl lcdService;
    @MockBean
    private JWTAuthenticationFilter jwtAuthenticationFilter;
    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void saveLcd_Success() throws Exception{
        LcdDTO lcdDTO = LcdDTO.builder()
                .name("Name")
                .description("Description1")
                .sumContractor("Sum1")
                .territory(TerritoryType.CLOSE)
                .technology(TechnologyType.PANEL)
                .gasType(GasType.NO)
                .watterSupply(HeatingType.AUTONOMOUS)
                .classType(ClassType.MASS)
                .type(LCDType.FIVE)
                .address("г.Город, р.Район, вул.Вулиця,1")
                .heatingType(HeatingType.AUTONOMOUS)
                .sewerage(HeatingType.CENTRAL)
                .appointment("123123")
                .height(2)
                .status(StatusLCDType.APARTMENT)
                .advantages("123123")
                .communal(CommunalType.HALF)
                .formalization("123123")
                .distanceSea(12)
                .typePayment("123123")
                .mainPhoto("123123")
                .contractor(12)
                .frames(Arrays.asList(
                        FrameDTO.builder().idFrame(1).num(1).build()
                ))
                .documents(Arrays.asList(
                        DocumentDTO.builder().idDocuments(1).name("a123").fileName("qqweq").build()
                ))
                .photos(Arrays.asList(
                        PhotoDTO.builder().fileName("qqweq").build()
                ))
                .newsList(Arrays.asList(
                        NewsDto.builder().idNews(1).title("qqweq").build()
                ))
                .build();
        when(lcdService.saveDTO(lcdDTO)).thenReturn("Lcd save");
        String json = objectMapper.writeValueAsString(lcdDTO);
        ResultActions response = mockMvc.perform(post("/api/lcd/add")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON));
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().string("Lcd save"));

    }

    @Test
    public void saveLcd_Not_Valid() throws Exception{
        LcdDTO lcdDTO = LcdDTO.builder()
                .name("Name")
                .description("Description1")
                .sumContractor("Sum1")
                .territory(TerritoryType.CLOSE)
                .technology(TechnologyType.PANEL)
                .gasType(GasType.NO)
                .watterSupply(HeatingType.AUTONOMOUS)
                .classType(ClassType.MASS)
                .type(LCDType.FIVE)
                .address("г.Город, р.Район, вул.Вулиця,1")
                .heatingType(HeatingType.AUTONOMOUS)
                .sewerage(HeatingType.CENTRAL)
                .appointment("123123")
                .height(2)
                .status(StatusLCDType.APARTMENT)
                .advantages("12")
                .communal(CommunalType.HALF)
                .formalization("123123")
                .distanceSea(12)
                .typePayment("123123")
                .mainPhoto("123123")
                .contractor(12)
                .frames(Arrays.asList(
                        FrameDTO.builder().idFrame(1).num(1).build()
                ))
                .documents(Arrays.asList(
                        DocumentDTO.builder().idDocuments(1).name("a123").fileName("qqweq").build()
                ))
                .photos(Arrays.asList(
                        PhotoDTO.builder().fileName("qqweq").build()
                ))
                .newsList(Arrays.asList(
                        NewsDto.builder().idNews(1).title("qqweq").build()
                ))
                .build();
        when(lcdService.saveDTO(lcdDTO)).thenReturn("Lcd save");
        String json = objectMapper.writeValueAsString(lcdDTO);
        ResultActions response = mockMvc.perform(post("/api/lcd/add")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON));
        response.andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(content().string("{\"errors\":[\"advantages: size must be between 3 and 150\"]}"));

    }
    @Test
    public void updateLcd_Not_Valid() throws Exception{
        LcdDTO lcdDTO = LcdDTO.builder()
                .name("Name")
                .description("Description1")
                .sumContractor("Sum1")
                .territory(TerritoryType.CLOSE)
                .technology(TechnologyType.PANEL)
                .gasType(GasType.NO)
                .watterSupply(HeatingType.AUTONOMOUS)
                .classType(ClassType.MASS)
                .type(LCDType.FIVE)
                .address("г.Город, р.Район, вул.Вулиця,1")
                .heatingType(HeatingType.AUTONOMOUS)
                .sewerage(HeatingType.CENTRAL)
                .appointment("123123")
                .height(2)
                .status(StatusLCDType.APARTMENT)
                .advantages("12")
                .communal(CommunalType.HALF)
                .formalization("123123")
                .distanceSea(12)
                .typePayment("123123")
                .mainPhoto("123123")
                .contractor(12)
                .frames(Arrays.asList(
                        FrameDTO.builder().idFrame(1).num(1).build()
                ))
                .documents(Arrays.asList(
                        DocumentDTO.builder().idDocuments(1).name("a123").fileName("qqweq").build()
                ))
                .photos(Arrays.asList(
                        PhotoDTO.builder().fileName("qqweq").build()
                ))
                .newsList(Arrays.asList(
                        NewsDto.builder().idNews(1).title("qqweq").build()
                ))
                .build();
        when(lcdService.findByIdDTO(1)).thenReturn(lcdDTO);
        when(lcdService.updateDTO(lcdDTO,1)).thenReturn("Success:\n"+ lcdDTO);
        String json = objectMapper.writeValueAsString(lcdDTO);
        ResultActions response = mockMvc.perform(put("/api/lcd/update/1")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON));
        response.andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(content().string("{\"errors\":[\"advantages: size must be between 3 and 150\"]}"));

    }

    @Test
    public void updateLcd_Success() throws Exception{
        LcdDTO lcdDTO = LcdDTO.builder()
                .name("Name")
                .description("Description1")
                .sumContractor("Sum1")
                .territory(TerritoryType.CLOSE)
                .technology(TechnologyType.PANEL)
                .gasType(GasType.NO)
                .watterSupply(HeatingType.AUTONOMOUS)
                .classType(ClassType.MASS)
                .type(LCDType.FIVE)
                .address("г.Город, р.Район, вул.Вулиця,1")
                .heatingType(HeatingType.AUTONOMOUS)
                .sewerage(HeatingType.CENTRAL)
                .appointment("123123")
                .height(2)
                .status(StatusLCDType.APARTMENT)
                .advantages("123")
                .communal(CommunalType.HALF)
                .formalization("123123")
                .distanceSea(12)
                .typePayment("123123")
                .mainPhoto("123123")
                .contractor(12)
                .frames(Arrays.asList(
                        FrameDTO.builder().idFrame(1).num(1).build()
                ))
                .documents(Arrays.asList(
                        DocumentDTO.builder().idDocuments(1).name("a123").fileName("qqweq").build()
                ))
                .photos(Arrays.asList(
                        PhotoDTO.builder().fileName("qqweq").build()
                ))
                .newsList(Arrays.asList(
                        NewsDto.builder().idNews(1).title("qqweq").build()
                ))
                .build();
        when(lcdService.findByIdDTO(1)).thenReturn(lcdDTO);
        when(lcdService.updateDTO(lcdDTO,1)).thenReturn("Success:\n"+ lcdDTO);
        String json = objectMapper.writeValueAsString(lcdDTO);
        ResultActions response = mockMvc.perform(put("/api/lcd/update/1")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON));
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().string("Success:\n"+ lcdDTO));

    }
    @Test
    public void updateLcd_NotFound() throws Exception{
        LcdDTO lcdDTO = LcdDTO.builder()
                .name("Name")
                .description("Description1")
                .sumContractor("Sum1")
                .territory(TerritoryType.CLOSE)
                .technology(TechnologyType.PANEL)
                .gasType(GasType.NO)
                .watterSupply(HeatingType.AUTONOMOUS)
                .classType(ClassType.MASS)
                .type(LCDType.FIVE)
                .address("г.Город, р.Район, вул.Вулиця,1")
                .heatingType(HeatingType.AUTONOMOUS)
                .sewerage(HeatingType.CENTRAL)
                .appointment("123123")
                .height(2)
                .status(StatusLCDType.APARTMENT)
                .advantages("123")
                .communal(CommunalType.HALF)
                .formalization("123123")
                .distanceSea(12)
                .typePayment("123123")
                .mainPhoto("123123")
                .contractor(12)
                .frames(Arrays.asList(
                        FrameDTO.builder().idFrame(1).num(1).build()
                ))
                .documents(Arrays.asList(
                        DocumentDTO.builder().idDocuments(1).name("a123").fileName("qqweq").build()
                ))
                .photos(Arrays.asList(
                        PhotoDTO.builder().fileName("qqweq").build()
                ))
                .newsList(Arrays.asList(
                        NewsDto.builder().idNews(1).title("qqweq").build()
                ))
                .build();
        when(lcdService.findByIdDTO(1)).thenReturn(lcdDTO);
        when(lcdService.updateDTO(lcdDTO,1)).thenReturn("Success:\n"+ lcdDTO);
        String json = objectMapper.writeValueAsString(lcdDTO);
        ResultActions response = mockMvc.perform(put("/api/lcd/update/2")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON));
        response.andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(content().string("Lcd not found"));

    }




    @Test
    public void deleteLcd_Success() throws Exception{
        LcdDTO lcdDTO = LcdDTO.builder()
                .name("Name")
                .description("Description1")
                .sumContractor("Sum1")
                .territory(TerritoryType.CLOSE)
                .technology(TechnologyType.PANEL)
                .gasType(GasType.NO)
                .watterSupply(HeatingType.AUTONOMOUS)
                .classType(ClassType.MASS)
                .type(LCDType.FIVE)
                .address("г.Город, р.Район, вул.Вулиця,1")
                .heatingType(HeatingType.AUTONOMOUS)
                .sewerage(HeatingType.CENTRAL)
                .appointment("123123")
                .height(2)
                .status(StatusLCDType.APARTMENT)
                .advantages("123")
                .communal(CommunalType.HALF)
                .formalization("123123")
                .distanceSea(12)
                .typePayment("123123")
                .mainPhoto("123123")
                .contractor(12)
                .frames(Arrays.asList(
                        FrameDTO.builder().idFrame(1).num(1).build()
                ))
                .documents(Arrays.asList(
                        DocumentDTO.builder().idDocuments(1).name("a123").fileName("qqweq").build()
                ))
                .photos(Arrays.asList(
                        PhotoDTO.builder().fileName("qqweq").build()
                ))
                .newsList(Arrays.asList(
                        NewsDto.builder().idNews(1).title("qqweq").build()
                ))
                .build();
        when(lcdService.findByIdDTO(1)).thenReturn(lcdDTO);
        ResultActions response = mockMvc.perform(delete("/api/lcd/delete/1")
                .contentType(MediaType.APPLICATION_JSON));
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().string("Success:\n"+lcdDTO));

    }
    @Test
    public void deleteLcd_NotFound() throws Exception{
        when(lcdService.findByIdDTO(1)).thenReturn(null);
        ResultActions response = mockMvc.perform(delete("/api/lcd/delete/1")
                .contentType(MediaType.APPLICATION_JSON));
        response.andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(content().string("Lcd not found"));

    }

    @Test
    public void allLcd() throws Exception{
        List<LcdDTO> list = List.of(
                LcdDTO.builder().build(),
                LcdDTO.builder().build(),
                LcdDTO.builder().build(),
                LcdDTO.builder().build(),
                LcdDTO.builder().build()

        );
        when(lcdService.findAllDTO()).thenReturn(list);
        ResultActions response = mockMvc.perform(get("/api/lcd/all")
                .contentType(MediaType.APPLICATION_JSON));
        response.andExpect(MockMvcResultMatchers.status().isOk());
        List<LcdDTO> lists = objectMapper.readValue(response.andReturn().getResponse().getContentAsString(),new TypeReference<List<LcdDTO>>() {});
        assertEquals(5,lists.size());
    }
    @Test
    public void findByIdLcd_NotFound() throws Exception{
        when(lcdService.findByIdDTO(1)).thenReturn(null);
        ResultActions response = mockMvc.perform(get("/api/lcd/1")
                .contentType(MediaType.APPLICATION_JSON));
        response.andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(content().string("LCD not found"));

    }

    @Test
    public void findByIdLcd() throws Exception{
        LcdDTO lcdDTO = LcdDTO.builder().idLcd(1).build();
        when(lcdService.findByIdDTO(1)).thenReturn(lcdDTO);
        ResultActions response = mockMvc.perform(get("/api/lcd/1")
                .contentType(MediaType.APPLICATION_JSON));
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(lcdDTO)));

    }





}

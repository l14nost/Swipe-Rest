package com.example.SwipeRest.controller;

import com.example.SwipeRest.config.JWTAuthenticationFilter;
import com.example.SwipeRest.dto.ApartmentDTO;
import com.example.SwipeRest.dto.PhotoDTO;
import com.example.SwipeRest.enums.*;
import com.example.SwipeRest.service.impl.ApartmentServiceImpl;
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

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


@WebMvcTest(ApartmentController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class ApartmentControllerTestMVC {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ApartmentServiceImpl apartmentService;
    @MockBean
    private JWTAuthenticationFilter jwtAuthenticationFilter;
    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void saveApartment_Success() throws Exception{
        ApartmentDTO  apartmentDTO = ApartmentDTO.builder()
                .number(101)
                .description("1123")
                .totalArea(100)
                .type(TypeApartment.APARTMENT)
                .layout(LayoutType.STUDIO)
                .state(State.REPAIR)
                .price(200020)
                .kitchenArea(20)
                .calculation(Calculation.CAPITAL)
                .foundingDocument(FoundingDocument.TREATY)
                .countRoom(CountRoom.K1)
                .communicationType(CommunicationType.SMS)
                .commission(Commission.K10)
                .address("г.Город, р.Район, ул.Улица,1")
                .balcony(BalconyType.NO)
                .heatingType(HeatingType.INDIVIDUAL)
                .mainPhoto("123")
                .photos(List.of(PhotoDTO.builder().fileName("asdasd").build()))
                .build();
        given(apartmentService.saveDTO(ArgumentMatchers.any())).willAnswer((invocation -> invocation.getArgument(0)));
        String json = objectMapper.writeValueAsString(apartmentDTO);
        ResultActions response = mockMvc.perform(post("/api/apartment/add")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON));
        response.andExpect(MockMvcResultMatchers.status().isOk());

    }
    @Test
    public void saveApartment_Not_Valid() throws Exception{
        ApartmentDTO  apartmentDTO = ApartmentDTO.builder()
                .number(101)
                .description("1123")
                .totalArea(100)
                .type(TypeApartment.APARTMENT)
                .layout(LayoutType.STUDIO)
                .state(State.REPAIR)
                .price(2000)
                .kitchenArea(20)
                .calculation(Calculation.CAPITAL)
                .foundingDocument(FoundingDocument.TREATY)
                .countRoom(CountRoom.K1)
                .communicationType(CommunicationType.SMS)
                .commission(Commission.K10)
                .address("г.Город, р.Район, ул.Улица,1")
                .balcony(BalconyType.NO)
                .heatingType(HeatingType.INDIVIDUAL)
                .mainPhoto("123")
                .photos(List.of(PhotoDTO.builder().fileName("asdasd").build()))
                .build();
        given(apartmentService.saveDTO(ArgumentMatchers.any())).willAnswer((invocation -> invocation.getArgument(0)));
        String json = objectMapper.writeValueAsString(apartmentDTO);
        ResultActions response = mockMvc.perform(post("/api/apartment/add")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON));
        response.andExpect(MockMvcResultMatchers.status().isBadRequest());

    }

    @Test
    public void updateApartment_Not_Found() throws Exception{
        ApartmentDTO  apartmentDTO = ApartmentDTO.builder()
                .number(101)
                .description("1123")
                .totalArea(100)
                .type(TypeApartment.APARTMENT)
                .layout(LayoutType.STUDIO)
                .state(State.REPAIR)
                .price(200200)
                .kitchenArea(20)
                .calculation(Calculation.CAPITAL)
                .foundingDocument(FoundingDocument.TREATY)
                .countRoom(CountRoom.K1)
                .communicationType(CommunicationType.SMS)
                .commission(Commission.K10)
                .address("г.Город, р.Район, ул.Улица,1")
                .balcony(BalconyType.NO)
                .heatingType(HeatingType.INDIVIDUAL)
                .mainPhoto("123")
                .photos(List.of(PhotoDTO.builder().fileName("asdasd").build()))
                .build();
        given(apartmentService.saveDTO(ArgumentMatchers.any())).willAnswer((invocation -> invocation.getArgument(0)));
        String json = objectMapper.writeValueAsString(apartmentDTO);
        ResultActions response = mockMvc.perform(put("/api/apartment/update/1")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON));
        response.andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(content().string("Apartment not found"));

    }
    @Test
    public void updateApartment_Not_Valid() throws Exception{
        ApartmentDTO  apartmentDTO = ApartmentDTO.builder()
                .number(101)
                .description("12323")
                .totalArea(100)
                .type(TypeApartment.APARTMENT)
                .layout(LayoutType.STUDIO)
                .state(State.REPAIR)
                .price(2000)
                .kitchenArea(20)
                .calculation(Calculation.CAPITAL)
                .foundingDocument(FoundingDocument.TREATY)
                .countRoom(CountRoom.K1)
                .communicationType(CommunicationType.SMS)
                .commission(Commission.K10)
                .address("г.Город, р.Район, ул.Улица,1")
                .balcony(BalconyType.NO)
                .heatingType(HeatingType.INDIVIDUAL)
                .mainPhoto("123")
                .photos(List.of(PhotoDTO.builder().fileName("asdasd").build()))
                .build();
        given(apartmentService.findByIdDTO(1)).willAnswer(invocation -> invocation.getArgument(0));
        String json = objectMapper.writeValueAsString(apartmentDTO);
        ResultActions response = mockMvc.perform(put("/api/apartment/update/1")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON));
        response.andExpect(MockMvcResultMatchers.status().isBadRequest());

    }

    @Test
    public void updateApartment_Success() throws Exception{
        ApartmentDTO  apartmentDTO = ApartmentDTO.builder()
                .number(101)
                .description("12323")
                .totalArea(100)
                .type(TypeApartment.APARTMENT)
                .layout(LayoutType.STUDIO)
                .state(State.REPAIR)
                .price(200020)
                .kitchenArea(20)
                .calculation(Calculation.CAPITAL)
                .foundingDocument(FoundingDocument.TREATY)
                .countRoom(CountRoom.K1)
                .communicationType(CommunicationType.SMS)
                .commission(Commission.K10)
                .address("г.Город, р.Район, ул.Улица,1")
                .balcony(BalconyType.NO)
                .heatingType(HeatingType.INDIVIDUAL)
                .mainPhoto("123")
                .photos(List.of(PhotoDTO.builder().fileName("asdasd").build()))
                .build();
        when(apartmentService.findByIdDTO(1)).thenReturn(ApartmentDTO.builder().build());
        when(apartmentService.updateDto(apartmentDTO,1)).thenReturn(apartmentDTO);
        String json = objectMapper.writeValueAsString(apartmentDTO);
        ResultActions response = mockMvc.perform(put("/api/apartment/update/1")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON));
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().string("Success:\n"+apartmentDTO));

    }

    @Test
    public void deleteApartment_Success() throws Exception{
        ApartmentDTO  apartmentDTO = ApartmentDTO.builder()
                .number(101)
                .description("12323")
                .totalArea(100)
                .type(TypeApartment.APARTMENT)
                .layout(LayoutType.STUDIO)
                .state(State.REPAIR)
                .price(200020)
                .kitchenArea(20)
                .calculation(Calculation.CAPITAL)
                .foundingDocument(FoundingDocument.TREATY)
                .countRoom(CountRoom.K1)
                .communicationType(CommunicationType.SMS)
                .commission(Commission.K10)
                .address("г.Город, р.Район, ул.Улица,1")
                .balcony(BalconyType.NO)
                .heatingType(HeatingType.INDIVIDUAL)
                .mainPhoto("123")
                .photos(List.of(PhotoDTO.builder().fileName("asdasd").build()))
                .build();
        when(apartmentService.findByIdDTO(1)).thenReturn(apartmentDTO);
        ResultActions response = mockMvc.perform(delete("/api/apartment/delete/1")
                .contentType(MediaType.APPLICATION_JSON));
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().string("Success:\n"+apartmentDTO));

    }
    @Test
    public void deleteApartment_NotFound() throws Exception{
        when(apartmentService.findByIdDTO(1)).thenReturn(null);
        ResultActions response = mockMvc.perform(delete("/api/apartment/delete/1")
                .contentType(MediaType.APPLICATION_JSON));
        response.andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(content().string("Apartment not found"));

    }

    @Test
    public void allApartment() throws Exception{
        List<ApartmentDTO> list = List.of(
                ApartmentDTO.builder().build(),
                ApartmentDTO.builder().build(),
                ApartmentDTO.builder().build(),
                ApartmentDTO.builder().build(),
                ApartmentDTO.builder().build()
        );
        when(apartmentService.findAllDTO()).thenReturn(list);
        ResultActions response = mockMvc.perform(get("/api/apartment/all")
                .contentType(MediaType.APPLICATION_JSON));
        response.andExpect(MockMvcResultMatchers.status().isOk());
        List<ApartmentDTO> lists = objectMapper.readValue(response.andReturn().getResponse().getContentAsString(),new TypeReference<List<ApartmentDTO>>() {});
        assertEquals(5,lists.size());
    }

    @Test
    public void findByIdApartment_NotFound() throws Exception{
        when(apartmentService.findByIdDTO(1)).thenReturn(null);
        ResultActions response = mockMvc.perform(get("/api/apartment/1")
                .contentType(MediaType.APPLICATION_JSON));
        response.andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(content().string("Apartment not found"));

    }

    @Test
    public void findByIdApartment() throws Exception{
        ApartmentDTO apartmentDTO = ApartmentDTO.builder().idApartment(1).build();
        when(apartmentService.findByIdDTO(1)).thenReturn(apartmentDTO);
        ResultActions response = mockMvc.perform(get("/api/apartment/1")
                .contentType(MediaType.APPLICATION_JSON));
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(apartmentDTO)));

    }





}

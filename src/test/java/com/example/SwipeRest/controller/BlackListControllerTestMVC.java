package com.example.SwipeRest.controller;

import com.example.SwipeRest.config.JWTAuthenticationFilter;
import com.example.SwipeRest.dto.ApartmentDTO;
import com.example.SwipeRest.dto.BlackListDTO;
import com.example.SwipeRest.dto.ClientDTO;
import com.example.SwipeRest.dto.UserAddInfoDTO;
import com.example.SwipeRest.enums.TypeNotification;
import com.example.SwipeRest.enums.TypeUser;
import com.example.SwipeRest.service.impl.UserServiceImpl;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


@WebMvcTest(BlackListController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class BlackListControllerTestMVC {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserServiceImpl userService;
    @MockBean
    private JWTAuthenticationFilter jwtAuthenticationFilter;
    @Autowired
    private ObjectMapper objectMapper;



    @Test
    public void allClient() throws Exception{
        List<BlackListDTO> list = List.of(
                BlackListDTO.builder().build(),
                BlackListDTO.builder().build(),
                BlackListDTO.builder().build(),
                BlackListDTO.builder().build(),
                BlackListDTO.builder().build()

        );
        when(userService.blackListDTO()).thenReturn(list);
        ResultActions response = mockMvc.perform(get("/api/black/list/all")
                .contentType(MediaType.APPLICATION_JSON));
        response.andExpect(MockMvcResultMatchers.status().isOk());
        List<BlackListDTO> lists = objectMapper.readValue(response.andReturn().getResponse().getContentAsString(),new TypeReference<List<BlackListDTO>>() {});
        assertEquals(5,lists.size());
    }

    @Test
    public void addToBlackList_Notary() throws Exception{
        ClientDTO clientDTO = ClientDTO.builder().userType(TypeUser.NOTARY).build();
        when(userService.findByIdDTO(2)).thenReturn(clientDTO);

        ResultActions response = mockMvc.perform(post("/api/black/list/add/2")
                .contentType(MediaType.APPLICATION_JSON));
        response.andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(content().string("User by this id is Notary"));

    }

    @Test
    public void addToBlackList_Already() throws Exception{
        ClientDTO clientDTO = ClientDTO.builder().userType(TypeUser.CLIENT).blackList(true).build();
        when(userService.findByIdDTO(2)).thenReturn(clientDTO);

        ResultActions response = mockMvc.perform(post("/api/black/list/add/2")
                .contentType(MediaType.APPLICATION_JSON));
        response.andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(content().string("User already in blacklist"));

    }
    @Test
    public void addToBlackList_Success() throws Exception{
        ClientDTO clientDTO = ClientDTO.builder().userType(TypeUser.CLIENT).blackList(false).build();
        when(userService.findByIdDTO(2)).thenReturn(clientDTO);

        ResultActions response = mockMvc.perform(post("/api/black/list/add/2")
                .contentType(MediaType.APPLICATION_JSON));
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().string("Success add"));

    }

    @Test
    public void addToBlackList_NotFound() throws Exception{
        ClientDTO clientDTO = ClientDTO.builder().userType(TypeUser.CLIENT).blackList(false).build();
        when(userService.findByIdDTO(2)).thenReturn(clientDTO);

        ResultActions response = mockMvc.perform(post("/api/black/list/add/3")
                .contentType(MediaType.APPLICATION_JSON));
        response.andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(content().string("User not found"));

    }

    @Test
    public void addToBlackList_idNotFound() throws Exception{
        ClientDTO clientDTO = ClientDTO.builder().userType(TypeUser.CLIENT).blackList(false).build();
        when(userService.findByIdDTO(2)).thenReturn(clientDTO);

        ResultActions response = mockMvc.perform(post("/api/black/list/add/-10")
                .contentType(MediaType.APPLICATION_JSON));
        response.andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(content().string("Id cannot be negative"));

    }


    @Test
    public void removeFromBlackList_Already() throws Exception{
        ClientDTO clientDTO = ClientDTO.builder().userType(TypeUser.CLIENT).blackList(false).build();
        when(userService.findByIdDTO(2)).thenReturn(clientDTO);

        ResultActions response = mockMvc.perform(post("/api/black/list/remove/2")
                .contentType(MediaType.APPLICATION_JSON));
        response.andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(content().string("User isn't in blacklist"));

    }
    @Test
    public void removeFromBlackList_Success() throws Exception{
        ClientDTO clientDTO = ClientDTO.builder().userType(TypeUser.CLIENT).blackList(true).build();
        when(userService.findByIdDTO(2)).thenReturn(clientDTO);

        ResultActions response = mockMvc.perform(post("/api/black/list/remove/2")
                .contentType(MediaType.APPLICATION_JSON));
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().string("Success remove"));

    }

    @Test
    public void removeFromBlackList_NotFound() throws Exception{
        ClientDTO clientDTO = ClientDTO.builder().userType(TypeUser.CLIENT).blackList(false).build();
        when(userService.findByIdDTO(2)).thenReturn(clientDTO);

        ResultActions response = mockMvc.perform(post("/api/black/list/remove/3")
                .contentType(MediaType.APPLICATION_JSON));
        response.andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(content().string("User not found"));

    }

    @Test
    public void removeFromBlackList_IdNotFound() throws Exception{
        ClientDTO clientDTO = ClientDTO.builder().userType(TypeUser.CLIENT).blackList(false).build();
        when(userService.findByIdDTO(2)).thenReturn(clientDTO);

        ResultActions response = mockMvc.perform(post("/api/black/list/remove/-10")
                .contentType(MediaType.APPLICATION_JSON));
        response.andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(content().string("Id cannot be negative"));

    }
    @Test
    public void removeFromBlackList_Notary() throws Exception{
        ClientDTO clientDTO = ClientDTO.builder().userType(TypeUser.NOTARY).build();
        when(userService.findByIdDTO(2)).thenReturn(clientDTO);

        ResultActions response = mockMvc.perform(post("/api/black/list/remove/2")
                .contentType(MediaType.APPLICATION_JSON));
        response.andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(content().string("User by this id is Notary"));

    }



}

package com.example.SwipeRest.controller;

import com.example.SwipeRest.config.JWTAuthenticationFilter;
import com.example.SwipeRest.dto.*;
import com.example.SwipeRest.entity.Agent;
import com.example.SwipeRest.entity.User;
import com.example.SwipeRest.enums.*;
import com.example.SwipeRest.mapper.UserMapper;
import com.example.SwipeRest.service.impl.AgentServiceImpl;
import com.example.SwipeRest.service.impl.ApartmentServiceImpl;
import com.example.SwipeRest.service.impl.UserServiceImpl;
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
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willAnswer;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


@WebMvcTest(ClientController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class ClientControllerTestMVC {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserServiceImpl userService;
    @MockBean
    private AgentServiceImpl agentService;
    @MockBean
    private JWTAuthenticationFilter jwtAuthenticationFilter;
    @Autowired
    private ObjectMapper objectMapper;
    @Test
    public void saveClient_Not_Valid_Type() throws Exception{
        ClientDTO  clientDTO = ClientDTO.builder()
                .name("Name")
                .surname("Surname")
                .number("101123112")
                .typeUser(TypeUser.NOTARY)
                .role(Role.USER)
                .mail("user@gmail.com")
                .fileName("../admin/dist/default")
                .blackList(false)
                .build();
        when(userService.addDTO(clientDTO)).thenReturn("Save");
        String json = objectMapper.writeValueAsString(clientDTO);
        BindingResult result = new BeanPropertyBindingResult(clientDTO,"clientDTO");
        given(userService.uniqueMail(anyString(), any(BindingResult.class), anyInt(), anyString(), anyString() ))
                .will(invocation -> {
                    BindingResult bindingResult = invocation.getArgument(1);
                    return bindingResult;
                });
        ResultActions response = mockMvc.perform(post("/api/client/add")
                .flashAttr("result",result)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON));
        response.andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(content().string("{\"errors\":[\"typeUser: User must be client\"]}"));

    }
    @Test
    public void saveClient_Not_Valid_Role() throws Exception{
        ClientDTO  clientDTO = ClientDTO.builder()
                .name("Name")
                .surname("Surname")
                .number("101123112")
                .typeUser(TypeUser.CLIENT)
                .role(Role.ADMIN)
                .mail("user@gmail.com")
                .fileName("../admin/dist/default")
                .blackList(false)
                .agent(AgentDTO.builder()
                        .mail("newmail@gmail.com")
                        .name("AgentTest")
                        .surname("MockTest")
                        .number("123123123").build())
                .build();
        when(userService.addDTO(clientDTO)).thenReturn("Save");
        String json = objectMapper.writeValueAsString(clientDTO);
        BindingResult result = new BeanPropertyBindingResult(clientDTO,"clientDTO");
        given(userService.uniqueMail(anyString(), any(BindingResult.class), anyInt(), anyString(), anyString() ))
                .will(invocation -> {
                    BindingResult bindingResult = invocation.getArgument(1);
                    return bindingResult;
                });
        given(agentService.uniqueEmail(anyString(), any(BindingResult.class), anyInt(), anyString(), anyString() ))
                .will(invocation -> {
                    BindingResult bindingResult = invocation.getArgument(1);
                    return bindingResult;
                });
        ResultActions response = mockMvc.perform(post("/api/client/add")
                .flashAttr("result",result)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON));
        response.andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(content().string("{\"errors\":[\"role: Access denied role must be USER\"]}"));

    }

    @Test
    public void saveClient_Success() throws Exception{
        ClientDTO  clientDTO = ClientDTO.builder()
                .name("Name")
                .surname("Surname")
                .number("101123123")
                .typeUser(TypeUser.CLIENT)
                .role(Role.USER)
                .mail("user@gmail.com")
                .fileName("../admin/dist/default")
                .blackList(false)
                .userAddInfo(UserAddInfoDTO.builder().dateSub(LocalDate.now()).callSms(true).typeNotification(TypeNotification.ME).build())
                .build();
        when(userService.addDTO(clientDTO)).thenReturn("Save");
        String json = objectMapper.writeValueAsString(clientDTO);
        BindingResult result = new BeanPropertyBindingResult(clientDTO,"clientDTO");
        given(userService.uniqueMail(anyString(), any(BindingResult.class), anyInt(), anyString(), anyString() ))
                .will(invocation -> {
                    BindingResult bindingResult = invocation.getArgument(1);
                    return bindingResult;
                });
        ResultActions response = mockMvc.perform(post("/api/client/add")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON));
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().string("Save"));

    }
    @Test
    public void saveClient_Not_Valid() throws Exception{
        ClientDTO  clientDTO = ClientDTO.builder()
                .name("Name")
                .surname("Surname")
                .number("1011231")
                .typeUser(TypeUser.CLIENT)
                .role(Role.USER)
                .mail("user@gmail.com")
                .fileName("../admin/dist/default")
                .blackList(false)
                .userAddInfo(UserAddInfoDTO.builder().dateSub(LocalDate.now()).callSms(true).typeNotification(TypeNotification.ME).build())
                .build();
        when(userService.addDTO(clientDTO)).thenReturn("Save");
        String json = objectMapper.writeValueAsString(clientDTO);
        BindingResult result = new BeanPropertyBindingResult(clientDTO,"clientDTO");
        given(userService.uniqueMail(anyString(), any(BindingResult.class), anyInt(), anyString(), anyString() ))
                .will(invocation -> {
                    BindingResult bindingResult = invocation.getArgument(1);
                    return bindingResult;
                });
        ResultActions response = mockMvc.perform(post("/api/client/add")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON));
        response.andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(content().string("{\"errors\":[\"number: size must be between 9 and 9\"]}"));

    }
    @Test
    public void updateClient_Not_Valid() throws Exception{
        ClientDTO  clientDTO = ClientDTO.builder()
                .name("Name")
                .surname("Surname")
                .number("1011231")
                .typeUser(TypeUser.CLIENT)
                .role(Role.USER)
                .mail("user@gmail.com")
                .fileName("../admin/dist/default")
                .agent(AgentDTO.builder()
                        .mail("newmail@gmail.com")
                        .name("AgentTest")
                        .surname("MockTest")
                        .number("123123123").build())
                .blackList(false)
                .userAddInfo(UserAddInfoDTO.builder().dateSub(LocalDate.now()).callSms(true).typeNotification(TypeNotification.ME).build())
                .build();
        when(userService.addDTO(clientDTO)).thenReturn("Save");
        when(userService.findByIdDTO(1)).thenReturn(ClientDTO.builder().role(Role.USER).typeUser(TypeUser.CLIENT).build());
        when(userService.findById(1)).thenReturn(User.builder().role(Role.USER).typeUser(TypeUser.CLIENT).agent(Agent.builder().idAgent(1).build()).build());
        String json = objectMapper.writeValueAsString(clientDTO);
        BindingResult result = new BeanPropertyBindingResult(clientDTO,"clientDTO");
        given(userService.uniqueMail(anyString(), any(BindingResult.class), anyInt(), anyString(), anyString() ))
                .will(invocation -> {
                    BindingResult bindingResult = invocation.getArgument(1);
                    return bindingResult;
                });
        given(agentService.uniqueEmail(anyString(), any(BindingResult.class), anyInt(), anyString(), anyString() ))
                .will(invocation -> {
                    BindingResult bindingResult = invocation.getArgument(1);
                    return bindingResult;
                });
        ResultActions response = mockMvc.perform(put("/api/client/update/1")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON));
        response.andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(content().string("{\"errors\":[\"number: size must be between 9 and 9\"]}"));

    }
    @Test
    public void updateClient_NotFound() throws Exception{
        ClientDTO  clientDTO = ClientDTO.builder()
                .name("Name")
                .surname("Surname")
                .number("101123123")
                .typeUser(TypeUser.CLIENT)
                .role(Role.USER)
                .mail("user@gmail.com")
                .fileName("../admin/dist/default")
                .blackList(false)
                .userAddInfo(UserAddInfoDTO.builder().dateSub(LocalDate.now()).callSms(true).typeNotification(TypeNotification.ME).build())
                .build();
        when(userService.findByIdDTO(1)).thenReturn(null);
        String json = objectMapper.writeValueAsString(clientDTO);
        ResultActions response = mockMvc.perform(put("/api/client/update/1")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON));
        response.andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(content().string("User NOT found"));

    }
    @Test
    public void updateClient_Admin() throws Exception{
        ClientDTO  clientDTO = ClientDTO.builder()
                .name("Name")
                .surname("Surname")
                .number("101123123")
                .typeUser(TypeUser.CLIENT)
                .role(Role.ADMIN)
                .mail("user@gmail.com")
                .fileName("../admin/dist/default")
                .blackList(false)
                .userAddInfo(UserAddInfoDTO.builder().dateSub(LocalDate.now()).callSms(true).typeNotification(TypeNotification.ME).build())
                .build();
        when(userService.findByIdDTO(1)).thenReturn(clientDTO);
        String json = objectMapper.writeValueAsString(clientDTO);
        ResultActions response = mockMvc.perform(put("/api/client/update/1")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON));
        response.andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(content().string("User by this id ADMIN"));
    }
    @Test
    public void updateClient_NotClient() throws Exception{
        ClientDTO  clientDTO = ClientDTO.builder()
                .name("Name")
                .surname("Surname")
                .number("101123123")
                .typeUser(TypeUser.NOTARY)
                .role(Role.USER)
                .mail("user@gmail.com")
                .fileName("../admin/dist/default")
                .blackList(false)
                .userAddInfo(UserAddInfoDTO.builder().dateSub(LocalDate.now()).callSms(true).typeNotification(TypeNotification.ME).build())
                .build();
        when(userService.findByIdDTO(2)).thenReturn(clientDTO);
        String json = objectMapper.writeValueAsString(clientDTO);
        ResultActions response = mockMvc.perform(put("/api/client/update/2")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON));
        response.andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(content().string("User by this id isn't CLIENT"));
    }

    @Test
    public void updateClient_Success() throws Exception{
        ClientDTO  clientDTO = ClientDTO.builder()
                .idUser(2)
                .name("Name")
                .surname("Surname")
                .number("101123123")
                .typeUser(TypeUser.CLIENT)
                .role(Role.USER)
                .mail("user@gmail.com")
                .fileName("../admin/dist/default")
                .blackList(false)
                .userAddInfo(UserAddInfoDTO.builder().dateSub(LocalDate.now()).callSms(true).typeNotification(TypeNotification.ME).build())
                .build();
        when(userService.findByIdDTO(2)).thenReturn(clientDTO);
        when(userService.updateDto(clientDTO,2)).thenReturn("Success update:\n" +
                "Update user:\n"+ clientDTO);
        String json = objectMapper.writeValueAsString(clientDTO);
        BindingResult result = new BeanPropertyBindingResult(clientDTO,"clientDTO");
        given(userService.uniqueMail(anyString(), any(BindingResult.class), anyInt(), anyString(), anyString() ))
                .will(invocation -> {
                    BindingResult bindingResult = invocation.getArgument(1);
                    return bindingResult;
                });
        ResultActions response = mockMvc.perform(put("/api/client/update/2")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON));
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().string("Success update:\n" +
                        "Update user:\n"+ clientDTO));
    }

    @Test
    public void deleteApartment_Success() throws Exception{
        ClientDTO  clientDTO = ClientDTO.builder()
                .idUser(2)
                .name("Name")
                .surname("Surname")
                .number("101123123")
                .typeUser(TypeUser.CLIENT)
                .role(Role.USER)
                .mail("user@gmail.com")
                .fileName("../admin/dist/default")
                .blackList(false)
                .userAddInfo(UserAddInfoDTO.builder().dateSub(LocalDate.now()).callSms(true).typeNotification(TypeNotification.ME).build())
                .build();
        when(userService.findByIdDTO(2)).thenReturn(clientDTO);
        ResultActions response = mockMvc.perform(delete("/api/client/delete/2")
                .contentType(MediaType.APPLICATION_JSON));
        response.andExpect(MockMvcResultMatchers.status().isOk());

    }
    @Test
    public void deleteApartment_NotFound() throws Exception{
        ClientDTO  clientDTO = ClientDTO.builder()
                .idUser(2)
                .name("Name")
                .surname("Surname")
                .number("101123123")
                .typeUser(TypeUser.CLIENT)
                .role(Role.USER)
                .mail("user@gmail.com")
                .fileName("../admin/dist/default")
                .blackList(false)
                .userAddInfo(UserAddInfoDTO.builder().dateSub(LocalDate.now()).callSms(true).typeNotification(TypeNotification.ME).build())
                .build();
        when(userService.findByIdDTO(2)).thenReturn(clientDTO);
        ResultActions response = mockMvc.perform(delete("/api/client/delete/1")
                .contentType(MediaType.APPLICATION_JSON));
        response.andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(content().string("User NOT found"));
    }
    @Test
    public void deleteApartment_ADMIN() throws Exception{
        ClientDTO  clientDTO = ClientDTO.builder()
                .idUser(1)
                .name("Name")
                .surname("Surname")
                .number("101123123")
                .typeUser(TypeUser.CLIENT)
                .role(Role.ADMIN)
                .mail("user@gmail.com")
                .fileName("../admin/dist/default")
                .blackList(false)
                .userAddInfo(UserAddInfoDTO.builder().dateSub(LocalDate.now()).callSms(true).typeNotification(TypeNotification.ME).build())
                .build();
        when(userService.findByIdDTO(1)).thenReturn(clientDTO);
        ResultActions response = mockMvc.perform(delete("/api/client/delete/1")
                .contentType(MediaType.APPLICATION_JSON));
        response.andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(content().string("User by this id ADMIN"));
    }

    @Test
    public void deleteApartment_NotClient() throws Exception{
        ClientDTO  clientDTO = ClientDTO.builder()
                .idUser(2)
                .name("Name")
                .surname("Surname")
                .number("101123123")
                .typeUser(TypeUser.NOTARY)
                .role(Role.USER)
                .mail("user@gmail.com")
                .fileName("../admin/dist/default")
                .blackList(false)
                .userAddInfo(UserAddInfoDTO.builder().dateSub(LocalDate.now()).callSms(true).typeNotification(TypeNotification.ME).build())
                .build();
        when(userService.findByIdDTO(2)).thenReturn(clientDTO);
        ResultActions response = mockMvc.perform(delete("/api/client/delete/2")
                .contentType(MediaType.APPLICATION_JSON));
        response.andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(content().string("User by this id isn't CLIENT"));
    }

    @Test
    public void allClient() throws Exception{
        List<ClientDTO> list = List.of(
                ClientDTO.builder().build(),
                ClientDTO.builder().build(),
                ClientDTO.builder().build(),
                ClientDTO.builder().build(),
                ClientDTO.builder().build()
        );
        when(userService.findAllByType(TypeUser.CLIENT)).thenReturn(list);
        ResultActions response = mockMvc.perform(get("/api/client/all")
                .contentType(MediaType.APPLICATION_JSON));
        response.andExpect(MockMvcResultMatchers.status().isOk());
        List<ClientDTO> lists = objectMapper.readValue(response.andReturn().getResponse().getContentAsString(),new TypeReference<List<ClientDTO>>() {});
        assertEquals(5,lists.size());
    }
//
//    @Test
//    public void findByIdApartment_NotFound() throws Exception{
//        when(apartmentService.findByIdDTO(1)).thenReturn(null);
//        ResultActions response = mockMvc.perform(get("/api/apartment/1")
//                .contentType(MediaType.APPLICATION_JSON));
//        response.andExpect(MockMvcResultMatchers.status().isBadRequest())
//                .andExpect(content().string("Apartment not found"));
//
//    }
//
    @Test
    public void findByIdClient() throws Exception{
        ClientDTO clientDTO = ClientDTO.builder().idUser(2).typeUser(TypeUser.CLIENT).role(Role.USER).build();
        when(userService.findByIdDTO(2)).thenReturn(clientDTO);
        ResultActions response = mockMvc.perform(get("/api/client/2")
                .contentType(MediaType.APPLICATION_JSON));
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(clientDTO)));

    }
    @Test
    public void findByIdClient_NotFound() throws Exception{
        ClientDTO clientDTO = ClientDTO.builder().idUser(2).typeUser(TypeUser.CLIENT).build();
        when(userService.findByIdDTO(2)).thenReturn(clientDTO);
        ResultActions response = mockMvc.perform(get("/api/client/1")
                .contentType(MediaType.APPLICATION_JSON));
        response.andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(content().string("User by this id not found"));

    }
    @Test
    public void findByIdClient_ADMIN() throws Exception{
        ClientDTO clientDTO = ClientDTO.builder().idUser(1).typeUser(TypeUser.CLIENT).role(Role.ADMIN).build();
        when(userService.findByIdDTO(1)).thenReturn(clientDTO);
        ResultActions response = mockMvc.perform(get("/api/client/1")
                .contentType(MediaType.APPLICATION_JSON));
        response.andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(content().string("User by this id ADMIN"));

    }
    @Test
    public void findByIdClient_NotClient() throws Exception{
        ClientDTO clientDTO = ClientDTO.builder().idUser(2).typeUser(TypeUser.NOTARY).role(Role.USER).build();
        when(userService.findByIdDTO(2)).thenReturn(clientDTO);
        ResultActions response = mockMvc.perform(get("/api/client/2")
                .contentType(MediaType.APPLICATION_JSON));
        response.andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(content().string("User by this id isn't CLIENT"));

    }





}

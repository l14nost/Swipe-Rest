package com.example.SwipeRest.controller;

import com.example.SwipeRest.dto.ClientDTO;
import com.example.SwipeRest.enums.TypeUser;
import com.example.SwipeRest.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClientControllerTest {
    @Mock
    private UserServiceImpl userService;
    @InjectMocks
    private ClientController clientController;

    @Test
    void findAllClient() {
        List<ClientDTO> clients = List.of(
                ClientDTO.builder().typeUser(TypeUser.CLIENT).build(),
                ClientDTO.builder().typeUser(TypeUser.CLIENT).build(),
                ClientDTO.builder().typeUser(TypeUser.CLIENT).build(),
                ClientDTO.builder().typeUser(TypeUser.CLIENT).build()
        );
        when(userService.findAllByType(TypeUser.CLIENT)).thenReturn(clients);

        ResponseEntity response = clientController.findAllClient();

        assertNotNull(response);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(clients,response.getBody());
    }

    @Test
    void findByIdClient_Success() {
        ClientDTO clientDTO = ClientDTO.builder().idUser(2).typeUser(TypeUser.CLIENT).build();
        when(userService.findByIdDTO(2)).thenReturn(clientDTO);

        ResponseEntity response = clientController.findByIdClient(2);
        assertNotNull(response);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(clientDTO,response.getBody());
    }

    @Test
    void findByIdClient_UserNotFound() {
        ResponseEntity response = clientController.findByIdClient(1);
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
        assertEquals("User by this id not found",response.getBody());
    }

    @Test
    void findByIdClient_UserNotClient() {
        ClientDTO clientDTO = ClientDTO.builder().idUser(2).typeUser(TypeUser.CONTRACTOR).build();
        when(userService.findByIdDTO(2)).thenReturn(clientDTO);
        ResponseEntity response = clientController.findByIdClient(2);
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
        assertEquals("User by this id isn't CLIENT",response.getBody());
    }
    @Test
    void findByIdClient_Admin() {
        ClientDTO clientDTO = ClientDTO.builder().idUser(1).typeUser(TypeUser.CONTRACTOR).build();
        when(userService.findByIdDTO(1)).thenReturn(clientDTO);
        ResponseEntity response = clientController.findByIdClient(1);
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
        assertEquals("User by this id ADMIN",response.getBody());
    }

    @Test
    void addClient() {
        ClientDTO clientDTO = ClientDTO.builder().idUser(1).typeUser(TypeUser.CLIENT).name("Name").surname("Surname").number("123123123").mail("mail").build();
        when(userService.addDTO(clientDTO)).thenReturn("Success:\n"+ clientDTO);
        ResponseEntity response = clientController.addClient(clientDTO);
        assertNotNull(response);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals("Success:\n"+ clientDTO,response.getBody());

    }
    @Test
    void deleteClient_Success() {
        ClientDTO clientDTO = ClientDTO.builder().idUser(2).typeUser(TypeUser.CLIENT).build();
        when(userService.findByIdDTO(2)).thenReturn(clientDTO);
        ResponseEntity response = clientController.deleteClient(2);
        assertNotNull(response);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals("Success delete:"+ clientDTO,response.getBody());
    }
    @Test
    void deleteClient_NotClient() {
        ClientDTO clientDTO = ClientDTO.builder().idUser(2).typeUser(TypeUser.CONTRACTOR).build();
        when(userService.findByIdDTO(2)).thenReturn(clientDTO);
        ResponseEntity response = clientController.deleteClient(2);
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
        assertEquals("User by this id isn't CLIENT",response.getBody());
    }

    @Test
    void deleteClient_NotFound() {

        ResponseEntity response = clientController.deleteClient(1);
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
        assertEquals("User NOT found",response.getBody());
    }
    @Test
    void deleteClient_Admin() {
        ClientDTO clientDTO = ClientDTO.builder().idUser(1).typeUser(TypeUser.CONTRACTOR).build();
        when(userService.findByIdDTO(1)).thenReturn(clientDTO);
        ResponseEntity response = clientController.deleteClient(1);
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
        assertEquals("User by this id ADMIN",response.getBody());
    }
    @Test
    void updateClient_UserNotClient() {
        ClientDTO clientDTO = ClientDTO.builder().idUser(2).typeUser(TypeUser.CONTRACTOR).build();
        when(userService.findByIdDTO(2)).thenReturn(clientDTO);
        ResponseEntity response = clientController.updateClient(2, ClientDTO.builder().build());
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
        assertEquals("User by this id isn't CLIENT",response.getBody());
    }
    @Test
    void updateClient_NotFound() {
        ResponseEntity response = clientController.updateClient(1, ClientDTO.builder().build());
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
        assertEquals("User NOT found",response.getBody());
    }

    @Test
    void updateClient_Success() {
        ClientDTO clientDTO = ClientDTO.builder().idUser(2).typeUser(TypeUser.CLIENT).name("Name").surname("Surname").number("123123123").mail("mail").build();
        ClientDTO clientDTOUpdate = ClientDTO.builder().idUser(2).typeUser(TypeUser.CLIENT).name("Name1").surname("Surname1").number("123123123").mail("mail").build();
        when(userService.findByIdDTO(2)).thenReturn(clientDTO);
        when(userService.updateDto(clientDTOUpdate,2)).thenReturn("Success update:\n" +
                "Update user:\n"+ clientDTOUpdate);
        ResponseEntity response = clientController.updateClient(2, clientDTOUpdate);
        assertNotNull(response);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals("Success update:\n" +
                "Update user:\n"+ clientDTOUpdate,response.getBody());
    }
    @Test
    void updateClient_Admin() {
        ClientDTO clientDTO = ClientDTO.builder().idUser(1).typeUser(TypeUser.CONTRACTOR).build();
        when(userService.findByIdDTO(1)).thenReturn(clientDTO);
        ResponseEntity response = clientController.updateClient(1, ClientDTO.builder().build());
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
        assertEquals("User by this id ADMIN",response.getBody());
    }
}
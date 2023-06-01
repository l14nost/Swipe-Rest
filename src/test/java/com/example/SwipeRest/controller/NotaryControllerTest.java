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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NotaryControllerTest {
    @Mock
    private UserServiceImpl userService;
    @InjectMocks
    private NotaryController notaryController;

    @Test
    void findAllNotary() {
        List<ClientDTO> clients = List.of(
                ClientDTO.builder().typeUser(TypeUser.NOTARY).build(),
                ClientDTO.builder().typeUser(TypeUser.NOTARY).build(),
                ClientDTO.builder().typeUser(TypeUser.NOTARY).build(),
                ClientDTO.builder().typeUser(TypeUser.NOTARY).build()
        );
        when(userService.findAllByType(TypeUser.NOTARY)).thenReturn(clients);

        ResponseEntity response = notaryController.findAllNotary();

        assertNotNull(response);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(clients,response.getBody());
    }

    @Test
    void findByIdNotary_Success() {
        ClientDTO clientDTO = ClientDTO.builder().idUser(2).typeUser(TypeUser.NOTARY).build();
        when(userService.findByIdDTO(2)).thenReturn(clientDTO);

        ResponseEntity response = notaryController.findByIdNotary(2);
        assertNotNull(response);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(clientDTO,response.getBody());
    }

    @Test
    void findByIdNotary_UserNotFound() {
        ResponseEntity response = notaryController.findByIdNotary(1);
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
        assertEquals("User by this id not found",response.getBody());
    }

    @Test
    void findByIdNotary_UserNotNotary() {
        ClientDTO clientDTO = ClientDTO.builder().idUser(2).typeUser(TypeUser.CONTRACTOR).build();
        when(userService.findByIdDTO(2)).thenReturn(clientDTO);
        ResponseEntity response = notaryController.findByIdNotary(2);
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
        assertEquals("User by this id isn't NOTARY",response.getBody());
    }
    @Test
    void findByIdNotary_Admin() {
        ClientDTO clientDTO = ClientDTO.builder().idUser(1).typeUser(TypeUser.CONTRACTOR).build();
        when(userService.findByIdDTO(1)).thenReturn(clientDTO);
        ResponseEntity response = notaryController.findByIdNotary(1);
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
        assertEquals("User by this id ADMIN",response.getBody());
    }

    @Test
    void addNotary() {
        ClientDTO clientDTO = ClientDTO.builder().idUser(1).typeUser(TypeUser.NOTARY).name("Name").surname("Surname").number("123123123").mail("mail").build();
        when(userService.addDTO(clientDTO)).thenReturn("Success:\n"+ clientDTO);
        ResponseEntity response = notaryController.addNotary(clientDTO);
        assertNotNull(response);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals("Success:\n"+ clientDTO,response.getBody());

    }
    @Test
    void deleteNotary_Success() {
        ClientDTO clientDTO = ClientDTO.builder().idUser(2).typeUser(TypeUser.NOTARY).build();
        when(userService.findByIdDTO(2)).thenReturn(clientDTO);
        ResponseEntity response = notaryController.deleteClient(2);
        assertNotNull(response);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals("Success delete:"+ clientDTO,response.getBody());
    }
    @Test
    void deleteNotary_NotClient() {
        ClientDTO clientDTO = ClientDTO.builder().idUser(2).typeUser(TypeUser.CONTRACTOR).build();
        when(userService.findByIdDTO(2)).thenReturn(clientDTO);
        ResponseEntity response = notaryController.deleteClient(2);
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
        assertEquals("User by this id isn't NOTARY",response.getBody());
    }
    @Test
    void deleteNotary_Admin() {
        ClientDTO clientDTO = ClientDTO.builder().idUser(1).typeUser(TypeUser.CONTRACTOR).build();
        when(userService.findByIdDTO(1)).thenReturn(clientDTO);
        ResponseEntity response = notaryController.deleteClient(1);
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
        assertEquals("User by this id ADMIN",response.getBody());
    }

    @Test
    void deleteNotary_NotFound() {

        ResponseEntity response = notaryController.deleteClient(1);
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
        assertEquals("User NOT found",response.getBody());
    }
    @Test
    void updateNotary_UserNotNotary() {
        ClientDTO clientDTO = ClientDTO.builder().idUser(2).typeUser(TypeUser.CONTRACTOR).build();
        when(userService.findByIdDTO(2)).thenReturn(clientDTO);
        ResponseEntity response = notaryController.updateClient(2, ClientDTO.builder().build());
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
        assertEquals("User by this id isn't NOTARY",response.getBody());
    }
    @Test
    void updateNotary_Admin() {
        ClientDTO clientDTO = ClientDTO.builder().idUser(1).typeUser(TypeUser.CONTRACTOR).build();
        when(userService.findByIdDTO(1)).thenReturn(clientDTO);
        ResponseEntity response = notaryController.updateClient(1, ClientDTO.builder().build());
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
        assertEquals("User by this id ADMIN",response.getBody());
    }
    @Test
    void updateNotary_NotFound() {
        ResponseEntity response = notaryController.updateClient(1, ClientDTO.builder().build());
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
        assertEquals("User NOT found",response.getBody());
    }

    @Test
    void updateNotary_Success() {
        ClientDTO clientDTO = ClientDTO.builder().idUser(2).typeUser(TypeUser.NOTARY).name("Name").surname("Surname").number("123123123").mail("mail").build();
        ClientDTO clientDTOUpdate = ClientDTO.builder().idUser(2).typeUser(TypeUser.NOTARY).name("Name1").surname("Surname1").number("123123123").mail("mail").build();
        when(userService.findByIdDTO(2)).thenReturn(clientDTO);
        when(userService.updateDto(clientDTOUpdate,2)).thenReturn("Success update:\n" +
                "Update user:\n"+ clientDTOUpdate);
        ResponseEntity response = notaryController.updateClient(2, clientDTOUpdate);
        assertNotNull(response);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals("Success update:\n" +
                "Update user:\n"+ clientDTOUpdate,response.getBody());
    }
}
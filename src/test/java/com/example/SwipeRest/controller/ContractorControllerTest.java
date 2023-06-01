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
class ContractorControllerTest {
    @Mock
    private UserServiceImpl userService;
    @InjectMocks
    private ContractorController contractorController;

    @Test
    void findAllContractor() {
        List<ClientDTO> clients = List.of(
                ClientDTO.builder().typeUser(TypeUser.CONTRACTOR).build(),
                ClientDTO.builder().typeUser(TypeUser.CONTRACTOR).build(),
                ClientDTO.builder().typeUser(TypeUser.CONTRACTOR).build(),
                ClientDTO.builder().typeUser(TypeUser.CONTRACTOR).build()
        );
        when(userService.findAllByType(TypeUser.CONTRACTOR)).thenReturn(clients);

        ResponseEntity response = contractorController.findAllContractor();

        assertNotNull(response);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(clients,response.getBody());
    }

    @Test
    void findByIdContractor_Success() {
        ClientDTO clientDTO = ClientDTO.builder().idUser(2).typeUser(TypeUser.CONTRACTOR).build();
        when(userService.findByIdDTO(2)).thenReturn(clientDTO);

        ResponseEntity response = contractorController.findByIdContractor(2);
        assertNotNull(response);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(clientDTO,response.getBody());
    }

    @Test
    void findByIdContractor_UserNotFound() {
        ResponseEntity response = contractorController.findByIdContractor(1);
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
        assertEquals("User by this id not found",response.getBody());
    }

    @Test
    void findByIdContractor_UserNotContractor() {
        ClientDTO clientDTO = ClientDTO.builder().idUser(2).typeUser(TypeUser.CLIENT).build();
        when(userService.findByIdDTO(2)).thenReturn(clientDTO);
        ResponseEntity response = contractorController.findByIdContractor(2);
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
        assertEquals("User by this id isn't CONTRACTOR",response.getBody());
    }
    @Test
    void findByIdContractor_Admin() {
        ClientDTO clientDTO = ClientDTO.builder().idUser(1).typeUser(TypeUser.CLIENT).build();
        when(userService.findByIdDTO(1)).thenReturn(clientDTO);
        ResponseEntity response = contractorController.findByIdContractor(1);
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
        assertEquals("User by this id ADMIN",response.getBody());
    }


    @Test
    void addContractor() {
        ClientDTO clientDTO = ClientDTO.builder().idUser(1).typeUser(TypeUser.CONTRACTOR).name("Name").surname("Surname").number("123123123").mail("mail").build();
        when(userService.addDTO(clientDTO)).thenReturn("Success:\n"+ clientDTO);
        ResponseEntity response = contractorController.addContractor(clientDTO);
        assertNotNull(response);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals("Success:\n"+ clientDTO,response.getBody());

    }
    @Test
    void deleteContractor_Success() {
        ClientDTO clientDTO = ClientDTO.builder().idUser(2).typeUser(TypeUser.CONTRACTOR).build();
        when(userService.findByIdDTO(2)).thenReturn(clientDTO);
        ResponseEntity response = contractorController.deleteClient(2);
        assertNotNull(response);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals("Success delete:"+ clientDTO,response.getBody());
    }
    @Test
    void deleteContractor_NotContractor() {
        ClientDTO clientDTO = ClientDTO.builder().idUser(2).typeUser(TypeUser.CLIENT).build();
        when(userService.findByIdDTO(2)).thenReturn(clientDTO);
        ResponseEntity response = contractorController.deleteClient(2);
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
        assertEquals("User by this id isn't CONTRACTOR",response.getBody());
    }
    @Test
    void deleteContractor_Admin() {
        ClientDTO clientDTO = ClientDTO.builder().idUser(1).typeUser(TypeUser.CLIENT).build();
        when(userService.findByIdDTO(1)).thenReturn(clientDTO);
        ResponseEntity response = contractorController.deleteClient(1);
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
        assertEquals("User by this id ADMIN",response.getBody());
    }

    @Test
    void deleteContractor_NotFound() {

        ResponseEntity response = contractorController.deleteClient(1);
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
        assertEquals("User NOT found",response.getBody());
    }
    @Test
    void updateContractor_UserNotContractor() {
        ClientDTO clientDTO = ClientDTO.builder().idUser(2).typeUser(TypeUser.CLIENT).build();
        when(userService.findByIdDTO(2)).thenReturn(clientDTO);
        ResponseEntity response = contractorController.updateClient(2, ClientDTO.builder().build());
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
        assertEquals("User by this id isn't CONTRACTOR",response.getBody());
    }
    @Test
    void updateContractor_Admin() {
        ClientDTO clientDTO = ClientDTO.builder().idUser(1).typeUser(TypeUser.CLIENT).build();
        when(userService.findByIdDTO(1)).thenReturn(clientDTO);
        ResponseEntity response = contractorController.updateClient(1, ClientDTO.builder().build());
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
        assertEquals("User by this id ADMIN",response.getBody());
    }
    @Test
    void updateContractor_NotFound() {
        ResponseEntity response = contractorController.updateClient(1, ClientDTO.builder().build());
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
        assertEquals("User NOT found",response.getBody());
    }

    @Test
    void updateContractor_Success() {
        ClientDTO clientDTO = ClientDTO.builder().idUser(2).typeUser(TypeUser.CONTRACTOR).name("Name").surname("Surname").number("123123123").mail("mail").build();
        ClientDTO clientDTOUpdate = ClientDTO.builder().idUser(2).typeUser(TypeUser.CONTRACTOR).name("Name1").surname("Surname1").number("123123123").mail("mail").build();
        when(userService.findByIdDTO(2)).thenReturn(clientDTO);
        when(userService.updateDto(clientDTOUpdate,2)).thenReturn("Success update:\n" +
                "Update user:\n"+ clientDTOUpdate);
        ResponseEntity response = contractorController.updateClient(2, clientDTOUpdate);
        assertNotNull(response);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals("Success update:\n" +
                "Update user:\n"+ clientDTOUpdate,response.getBody());
    }
}
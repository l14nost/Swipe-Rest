package com.example.SwipeRest.oldController;

import com.example.SwipeRest.controller.BlackListController;
import com.example.SwipeRest.dto.BlackListDTO;
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
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BlackListControllerTest {
    @Mock
    private UserServiceImpl userService;
    @InjectMocks
    private BlackListController blackListController;
    @Test
    void findAllBlackList() {
        List<BlackListDTO> blackList = List.of(
                BlackListDTO.builder().name("User1").build(),
                BlackListDTO.builder().name("User2").build(),
                BlackListDTO.builder().name("User3").build()
        );

        when(userService.blackListDTO()).thenReturn(blackList);

        ResponseEntity response = blackListController.findAllBlackList();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(blackList,response.getBody());
    }

    @Test
    void addToBlackList_Success() {
        ClientDTO clientDTO = ClientDTO.builder()
                .idUser(1)
                .number("123123")
                .blackList(false)
                .typeUser(TypeUser.CLIENT)
                .build();
        when(userService.findByIdDTO(1)).thenReturn(clientDTO);

        ResponseEntity response = blackListController.addToBlackList(1);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Success add",response.getBody());
    }
    @Test
    void addToBlackList_Notary() {
        ClientDTO clientDTO = ClientDTO.builder()
                .idUser(1)
                .number("123123")
                .typeUser(TypeUser.NOTARY)
                .blackList(false)
                .build();
        when(userService.findByIdDTO(1)).thenReturn(clientDTO);

        ResponseEntity response = blackListController.addToBlackList(1);

        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("User by this id is Notary",response.getBody());
    }
    @Test
    void addToBlackList_NotFound() {

        ResponseEntity response = blackListController.addToBlackList(1);

        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("User not found",response.getBody());
    }
    @Test
    void addToBlackList_AlreadyInBlackList() {
        ClientDTO clientDTO = ClientDTO.builder()
                .idUser(1)
                .number("123123")
                .typeUser(TypeUser.CLIENT)
                .blackList(true)
                .build();
        when(userService.findByIdDTO(1)).thenReturn(clientDTO);

        ResponseEntity response = blackListController.addToBlackList(1);

        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("User already in blacklist",response.getBody());
    }
}
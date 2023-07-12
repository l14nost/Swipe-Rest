package com.example.SwipeRest.service.impl;

import com.example.SwipeRest.dto.AgentDTO;
import com.example.SwipeRest.dto.BlackListDTO;
import com.example.SwipeRest.dto.UserAddInfoDTO;
import com.example.SwipeRest.dto.ClientDTO;
import com.example.SwipeRest.entity.Agent;
import com.example.SwipeRest.entity.User;
import com.example.SwipeRest.entity.UserAddInfo;
import com.example.SwipeRest.enums.Role;
import com.example.SwipeRest.enums.TypeNotification;
import com.example.SwipeRest.enums.TypeUser;
import com.example.SwipeRest.repository.UserRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.never;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @Mock
    private AgentServiceImpl agentService;
    @Mock
    private UserAddInfoServiceImpl userAddInfoService;
    @Mock
    private UserRepo userRepo;
    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void findAllByType() {
        List<User> notary = Arrays.asList(
                User.builder().typeUser(TypeUser.NOTARY).build(),
                User.builder().typeUser(TypeUser.NOTARY).build(),
                User.builder().typeUser(TypeUser.NOTARY).build(),
                User.builder().typeUser(TypeUser.NOTARY).build(),
                User.builder().typeUser(TypeUser.NOTARY).build()
        );
        when(userRepo.findAllByTypeUserAndBlackListIsFalse(TypeUser.NOTARY)).thenReturn(notary);
        List<ClientDTO> users = userService.findAllByType(TypeUser.NOTARY);
        assertEquals(5,users.size());
        for (ClientDTO user : users) {
            assertEquals(TypeUser.NOTARY, user.getTypeUser());
        }

    }

    @Test
    void findAllByTypeDTO() {
        List<User> notary = Arrays.asList(
                User.builder().typeUser(TypeUser.NOTARY).build(),
                User.builder().typeUser(TypeUser.NOTARY).build(),
                User.builder().typeUser(TypeUser.NOTARY).build(),
                User.builder().typeUser(TypeUser.NOTARY).build(),
                User.builder().typeUser(TypeUser.NOTARY).build()
        );
        when(userRepo.findAllByTypeUserAndBlackListIsFalse(TypeUser.NOTARY)).thenReturn(notary);
        List<ClientDTO> users = userService.findAllByType(TypeUser.NOTARY);
        assertEquals(5,users.size());
        for (ClientDTO user : users) {
            assertEquals(TypeUser.NOTARY, user.getTypeUser());
        }
    }


    @Test
    void blackList() {
        List<User> client = Arrays.asList(
                User.builder().typeUser(TypeUser.CLIENT).build(),
                User.builder().typeUser(TypeUser.CLIENT).build(),
                User.builder().typeUser(TypeUser.CLIENT).build(),
                User.builder().typeUser(TypeUser.CLIENT).build(),
                User.builder().typeUser(TypeUser.CLIENT).build()
        );
        when(userRepo.findAllByBlackListIsTrue()).thenReturn(client);
        List<BlackListDTO> users = userService.blackListDTO();
        assertEquals(5,users.size());
    }

    @Test
    void findAll() {
        List<User> users = Arrays.asList(
                User.builder().typeUser(TypeUser.CLIENT).build(),
                User.builder().typeUser(TypeUser.NOTARY).build(),
                User.builder().typeUser(TypeUser.CLIENT).build(),
                User.builder().typeUser(TypeUser.CONTRACTOR).build(),
                User.builder().typeUser(TypeUser.CLIENT).build()
        );
        when(userRepo.findAll()).thenReturn(users);
        List<User> usersList = userService.findAll();
        assertEquals(5,usersList.size());
    }

    @Test
    void findByIdDTO() {
        User user = User.builder()
                .idUser(1)
                .number("123123123")
                .mail("123@gmail.com")
                .name("User")
                .surname("Surname")
                .blackList(false)
                .role(Role.USER)
                .build();
        when(userRepo.findById(1)).thenReturn(Optional.of(user));
        ClientDTO clientDTO = userService.findByIdDTO(1);
        assertEquals(1,clientDTO.getIdUser());
        assertEquals("123123123",clientDTO.getNumber());
        assertEquals("123@gmail.com",clientDTO.getMail());
        assertEquals("User",clientDTO.getName());
        assertEquals("Surname",clientDTO.getSurname());
        ClientDTO clientNull = userService.findByIdDTO(2);
        assertNull(clientNull);
    }

    @Test
    void findById() {
        User user = User.builder()
                .idUser(1)
                .number("123123123")
                .mail("123@gmail.com")
                .name("User")
                .surname("Surname")
                .blackList(false)
                .role(Role.USER)
                .build();
        when(userRepo.findById(1)).thenReturn(Optional.of(user));
        User clientDTO = userService.findById(1);
        assertEquals(1,clientDTO.getIdUser());
        assertEquals("123123123",clientDTO.getNumber());
        assertEquals("123@gmail.com",clientDTO.getMail());
        assertEquals("User",clientDTO.getName());
        assertEquals("Surname",clientDTO.getSurname());
        User clientNull = userService.findById(2);
        assertNull(clientNull);
    }

    @Test
    void saveEntityDTO() {
        ClientDTO user = ClientDTO.builder()
                .idUser(1)
                .number("123123123")
                .mail("123@gmail.com")
                .name("User")
                .surname("Surname")
                .role(Role.USER)
                .agent(AgentDTO.builder().idAgent(1).build())
                .build();
        userService.addDTO(user);
        User userSave = User.builder()
                .idUser(1)
                .number("123123123")
                .mail("123@gmail.com")
                .name("User")
                .surname("Surname")
                .blackList(false)
                .role(Role.USER)
                .build();
        verify(userRepo).save(userSave);
    }

    @Test
    void saveEntity() {
        User user = User.builder()
                .number("123123123")
                .mail("123@gmail.com")
                .name("User")
                .surname("Surname")
                .blackList(false)
                .role(Role.USER)
                .build();
        userService.saveEntity(user);
        User userSave = User.builder()
                .number("123123123")
                .mail("123@gmail.com")
                .name("User")
                .surname("Surname")
                .blackList(false)
                .role(Role.USER)
                .build();
        verify(userRepo).save(userSave);
    }
//804059ae-eba1-49a0-8e06-ccf3f6b545fa-stock2.jpg создать файл
    @Test
    void deleteById() {
        userService.deleteById(1);
        verify(userRepo).deleteById(1);
    }

    @Test
    void updateEntity() {
        User user = User.builder()
                .idUser(1)
                .number("123123123")
                .mail("123@gmail.com")
                .name("User")
                .surname("Surname")
                .filename("../uploads/804059ae-eba1-49a0-8e06-ccf3f6b545fa-stock2.jpg")
                .agent(Agent.builder().idAgent(1).name("Name").build())
                .build();
        when(userRepo.findById(1)).thenReturn(Optional.of(user));
        User userUpdate = User.builder()
                .number("123123123")
                .mail("124@gmail.com")
                .name("User1")
                .surname("Surname1")
                .filename("../uploads/804059ae-eba1-49a0-8e06-ccf3f6b545fa-stock3.jpg")
                .agent(Agent.builder().idAgent(1).name("Name1").build())
                .build();
        userService.updateEntity(userUpdate,1);
        User userSave = User.builder()
                .idUser(1)
                .number("123123123")
                .mail("124@gmail.com")
                .name("User1")
                .surname("Surname1")
                .filename("../uploads/804059ae-eba1-49a0-8e06-ccf3f6b545fa-stock3.jpg")
                .agent(Agent.builder().idAgent(1).name("Name1").build())
                .build();
        verify(userRepo).saveAndFlush(userSave);
    }

    @Test
    void updateDTO() {
        User user = User.builder()
                .idUser(1)
                .number("123123123")
                .mail("123@gmail.com")
                .name("User")
                .surname("Surname")
                .filename("../uploads/804059ae-eba1-49a0-8e06-ccf3f6b545fa-stock2.jpg")
                .agent(Agent.builder().idAgent(1).name("Name").build())
                .userAddInfo(UserAddInfo.builder().typeNotification(TypeNotification.MEANDAGENT).build())
                .build();
        when(userRepo.findById(1)).thenReturn(Optional.of(user));
        ClientDTO userUpdate = ClientDTO.builder()
                .number("123123123")
                .mail("124@gmail.com")
                .name("User1")
                .surname("Surname1")
                .fileName("../uploads/804059ae-eba1-49a0-8e06-ccf3f6b545fa-stock3.jpg")
                .agent(AgentDTO.builder().idAgent(1).name("Name1").build())
                .userAddInfo(UserAddInfoDTO.builder().typeNotification(TypeNotification.ME).build())
                .build();
        userService.updateDto(userUpdate,1);
        User userSave = User.builder()
                .idUser(1)
                .number("123123123")
                .mail("124@gmail.com")
                .name("User1")
                .surname("Surname1")
                .filename("../uploads/804059ae-eba1-49a0-8e06-ccf3f6b545fa-stock3.jpg")
                .agent(Agent.builder().idAgent(1).name("Name1").build())
                .userAddInfo(UserAddInfo.builder().typeNotification(TypeNotification.ME).build())
                .build();
        verify(userRepo).saveAndFlush(userSave);
        assertEquals("Something went wrong",userService.updateDto(userUpdate,2));
    }
    @Test
    void addToBlackList() {
        User user = User.builder().idUser(1).blackList(false).build();
        when(userRepo.findById(1)).thenReturn(Optional.of(user));
        userService.addToBlackList(1);
        User userBlackList = User.builder().idUser(1).blackList(true).build();
        verify(userRepo).saveAndFlush(userBlackList);
    }
    @Test
    void removeFromBlackList() {
        User user = User.builder().idUser(1).blackList(true).build();
        when(userRepo.findById(1)).thenReturn(Optional.of(user));
        userService.removeFromBlackList(1);
        User userBlackList = User.builder().idUser(1).blackList(false).build();
        verify(userRepo).saveAndFlush(userBlackList);
    }


    @Test
    void uniqueMailNull(){
        when(userRepo.findAllByMail("mail@gmail.com")).thenReturn(List.of(User.builder().build(), User.builder().build()));
        BindingResult result = mock(BindingResult.class);
        BindingResult result1 = userService.uniqueMail("mail@gmail.com",result,0,"add","clientDTO");

        assertEquals(result1,result);
        verify(result, times(1)).addError(any(FieldError.class));
    }

    @Test
    void uniqueMailUpdate(){
        when(userRepo.findAllByMail("mail@gmail.com")).thenReturn(List.of(User.builder().idUser(1).build()));
        BindingResult result = mock(BindingResult.class);
        BindingResult result1 = userService.uniqueMail("mail@gmail.com",result,1,"update","clientDTO");

        assertEquals(result1,result);
        verify(result, never()).addError(any(FieldError.class));
    }

    @Test
    void uniqueMailUpdateError(){
        when(userRepo.findAllByMail("mail@gmail.com")).thenReturn(List.of(User.builder().idUser(1).build()));
        BindingResult result = mock(BindingResult.class);
        BindingResult result1 = userService.uniqueMail("mail@gmail.com",result,2,"update","clientDTO");
        assertEquals(result1,result);
        verify(result, times(1)).addError(any(FieldError.class));
    }

    @Test
    void uniqueMailAddError(){
        when(userRepo.findAllByMail("mail@gmail.com")).thenReturn(List.of(User.builder().build()));
        BindingResult result = mock(BindingResult.class);
        BindingResult result1 = userService.uniqueMail("mail@gmail.com",result,2,"add","clientDTO");
        assertEquals(result1,result);
        verify(result, times(1)).addError(any(FieldError.class));
    }

    @Test
    void uniqueMailSuccess(){
        when(userRepo.findAllByMail("mail@gmail.com")).thenReturn(List.of());
        BindingResult result = mock(BindingResult.class);
        BindingResult result1 = userService.uniqueMail("mail@gmail.com",result,1,"add","clientDTO");
        assertEquals(result1,result);
        verify(result, never()).addError(any(FieldError.class));
    }

}
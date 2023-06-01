package com.example.SwipeRest.mapper;

import com.example.SwipeRest.dto.ClientDTO;
import com.example.SwipeRest.entity.User;
import com.example.SwipeRest.enums.Role;
import com.example.SwipeRest.enums.TypeUser;


public class UserDtoMapper {
//    public User toEntity(UserDTO userDTO){
//        return User.builder()
////                .idUser(userDTO.getIdUser())
//                .name(userDTO.getName())
//                .surname(userDTO.getSurname())
//                .typeUser(TypeUser.CLIENT)
//                .role(Role.USER)
//                .mail(userDTO.getMail())
//                .filename(userDTO.getFileName())
//                .build();
//    }


    public static User apply(ClientDTO clientDTO) {
        return User.builder()
//                .idUser(userDTO.getIdUser())
                .name(clientDTO.getName())
                .surname(clientDTO.getSurname())
                .typeUser(TypeUser.CLIENT)
                .role(Role.USER)
                .mail(clientDTO.getMail())
                .filename(clientDTO.getFileName())
                .build();

    }
}

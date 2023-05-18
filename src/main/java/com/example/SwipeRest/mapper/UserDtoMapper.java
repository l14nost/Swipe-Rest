package com.example.SwipeRest.mapper;

import com.example.SwipeRest.dto.UserDTO;
import com.example.SwipeRest.entity.User;
import com.example.SwipeRest.enums.Role;
import com.example.SwipeRest.enums.TypeUser;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class UserDtoMapper implements Function<UserDTO, User> {
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

    @Override
    public User apply(UserDTO userDTO) {
        return User.builder()
//                .idUser(userDTO.getIdUser())
                .name(userDTO.getName())
                .surname(userDTO.getSurname())
                .typeUser(TypeUser.CLIENT)
                .role(Role.USER)
                .mail(userDTO.getMail())
                .filename(userDTO.getFileName())
                .build();

    }
}

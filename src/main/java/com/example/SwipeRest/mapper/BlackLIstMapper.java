package com.example.SwipeRest.mapper;

import com.example.SwipeRest.dto.BlackListDTO;
import com.example.SwipeRest.entity.User;
import org.springframework.stereotype.Component;

import java.util.function.Function;

public class BlackLIstMapper {
    public static User toEntity(BlackListDTO userDTO){
        return User.builder()
                .idUser(userDTO.getId())
                .name(userDTO.getName())
                .surname(userDTO.getSurname())
                .typeUser(userDTO.getUserType())
                .mail(userDTO.getEmail())
                .build();
    }

    public static BlackListDTO apply(User user) {
        return BlackListDTO.builder()
                .id(user.getIdUser())
                .name(user.getName())
                .email(user.getMail())
                .surname(user.getSurname())
                .userType(user.getTypeUser())
                .build();

    }
}

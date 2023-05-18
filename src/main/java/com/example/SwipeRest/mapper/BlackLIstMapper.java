package com.example.SwipeRest.mapper;

import com.example.SwipeRest.dto.BlackListDTO;
import com.example.SwipeRest.entity.User;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class BlackLIstMapper implements Function<User, BlackListDTO> {
    public User toEntity(BlackListDTO userDTO){
        return User.builder()
                .idUser(userDTO.getIdUser())
                .name(userDTO.getName())
                .surname(userDTO.getSurname())
                .typeUser(userDTO.getTypeUser())
                .mail(userDTO.getMail())
                .build();
    }

    @Override
    public BlackListDTO apply(User user) {
        return BlackListDTO.builder()
                .idUser(user.getIdUser())
                .name(user.getName())
                .mail(user.getMail())
                .surname(user.getSurname())
                .typeUser(user.getTypeUser())
                .build();

    }
}

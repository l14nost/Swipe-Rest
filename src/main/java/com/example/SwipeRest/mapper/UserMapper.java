package com.example.SwipeRest.mapper;

import com.example.SwipeRest.dto.ClientDTO;
import com.example.SwipeRest.dto.UserDTO;
import com.example.SwipeRest.entity.User;
import com.example.SwipeRest.enums.Role;
import com.example.SwipeRest.enums.TypeUser;
import org.springframework.stereotype.Component;

import java.util.function.Function;
@Component
public class UserMapper implements Function<User, UserDTO> {
    public User toEntity(UserDTO userDTO){
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

    @Override
    public UserDTO apply(User user) {
        return UserDTO.builder()
                .name(user.getName())
                .mail(user.getMail())
                .surname(user.getSurname())
                .build();

    }
}

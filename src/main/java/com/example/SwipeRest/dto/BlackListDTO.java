package com.example.SwipeRest.dto;

import com.example.SwipeRest.enums.TypeUser;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class BlackListDTO {

    int idUser;
    String name;
    String surname;
    String mail;
    TypeUser typeUser;

}

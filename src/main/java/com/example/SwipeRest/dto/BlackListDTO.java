package com.example.SwipeRest.dto;

import com.example.SwipeRest.enums.TypeUser;
import lombok.Builder;
import lombok.Data;



@Data
@Builder
public class BlackListDTO {

    int idUser;
    String name;
    String surname;
    String mail;
    TypeUser typeUser;

}

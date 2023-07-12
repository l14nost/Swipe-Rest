package com.example.SwipeRest.dto;

import com.example.SwipeRest.enums.TypeUser;
import lombok.Builder;
import lombok.Data;



@Data
@Builder
public class BlackListDTO {

    int id;
    String name;
    String surname;
    String email;
    TypeUser userType;

}

package com.example.SwipeRest.dto;

import com.example.SwipeRest.enums.TypeUser;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {
    int idUser;
    String mail;
    String name;
    String surname;
    String fileName;
    AgentDTO agentDTO;
    TypeUser typeUser;
    UserAddInfoDTO userAddInfoDTO;
    boolean black_list;
}

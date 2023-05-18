package com.example.SwipeRest.dto;

import com.example.SwipeRest.enums.TypeAgent;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class AgentDTO {
    String mail;
    String name;
    String number;
    String surname;
    TypeAgent typeAgent;
    List<UserDTO> users;

}

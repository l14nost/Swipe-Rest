package com.example.SwipeRest.dto;

import com.example.SwipeRest.enums.Role;
import com.example.SwipeRest.enums.TypeUser;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.Valid;
import javax.validation.constraints.*;
import lombok.Builder;
import lombok.Data;



@Data
@Builder
public class ClientDTO {
    @Schema(type = "int",example = "1")
    int idUser;
    @Schema(type = "string",example = "mail@gmail.com")
    @NotBlank
    @Email( regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    @Size(min = 2, max = 255)
    String mail;
    @Schema(type = "string",example = "Name")
    @NotBlank
    @Pattern(regexp = "^[А-ЯЁA-Z][а-яёa-zA-Z]*$")
    @Size(min = 2, max = 255)
    String name;
    @Schema(type = "string",example = "Surname")
    @NotBlank
    @Pattern(regexp = "^[А-ЯЁA-Z][а-яёa-zA-Z]*$")
    @Size(min = 2, max = 255)
    String surname;
    @Schema(type = "string",example = "file.jpg")
    @Size( max = 255)
    String fileName;
    @Schema(type = "string",example = "123123231")
    @NotBlank
    @Pattern(regexp = "^[0-9]*$")
    @Size(max = 9, min = 9)
    String number;
    @Valid
    AgentDTO agent;
    @NotNull
    TypeUser typeUser;
    @NotNull
    Role role;
    @Valid
    UserAddInfoDTO userAddInfo;
    @Schema(type = "boolean",example = "false")
    boolean blackList;
}

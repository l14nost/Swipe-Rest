package com.example.SwipeRest.dto;

import com.example.SwipeRest.enums.Role;
import com.example.SwipeRest.enums.TypeUser;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
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
    String mail;
    @Schema(type = "string",example = "Name")
    @NotBlank
    @NotEmpty
    @Pattern(regexp = "^[А-ЯЁA-Z][а-яёa-zA-Z]*$")
    String name;
    @Schema(type = "string",example = "Surname")
    @NotBlank
    @NotEmpty
    @Pattern(regexp = "^[А-ЯЁA-Z][а-яёa-zA-Z]*$")
    String surname;
    @Schema(type = "string",example = "file.jpg")
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

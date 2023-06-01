package com.example.SwipeRest.dto;

import com.example.SwipeRest.enums.TypeAgent;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class AgentDTO {
    @Schema(
            type = "int",
            example = "1"
    )
    int idAgent;
    @Schema(
            type = "string",
            example = "mail@gmail.com"
    )
    @Email( regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    @NotBlank
    String mail;
    @Schema(
            type = "string",
            example = "Agent"
    )
    @NotBlank
    @NotEmpty
    @Pattern(regexp = "^[А-ЯЁA-Z][а-яёa-zA-Z]*$")
    String name;
    @Schema(
            type = "string",
            example = "123123123",
            description = "only 9 numbers"
    )
    @NotBlank
    @NotEmpty
    @Size(min = 9, max = 9)
    String number;
    @Schema(
            type = "string",
            example = "Agentov"
    )
    @NotBlank
    @NotEmpty
    @Pattern(regexp = "^[А-ЯЁA-Z][а-яёa-zA-Z]*$")
    String surname;
    @Schema(
            type = "string",
            example = "AGENT",
            description = "If it's CONTRACTOR, input value SALES"
    )
    TypeAgent typeAgent;

}

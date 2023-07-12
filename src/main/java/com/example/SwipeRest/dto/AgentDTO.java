package com.example.SwipeRest.dto;

import com.example.SwipeRest.enums.TypeAgent;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AgentDTO {
    @Schema(
            type = "int",
            example = "1"
    )
    int id;
    @Schema(
            type = "string",
            example = "mail@gmail.com"
    )
    @Email( regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    @NotBlank
    @Size(min = 5, max = 255)
    String email;
    @Schema(
            type = "string",
            example = "Agent"
    )
    @NotBlank
    @Pattern(regexp = "^[А-ЯЁA-Z][а-яёa-zA-Z]*$")
    @Size(min = 2, max = 255)
    String name;
    @Schema(
            type = "string",
            example = "123123123",
            description = "only 9 numbers"
    )
    @NotBlank
    @Size(min = 9, max = 9)
    String number;
    @Schema(
            type = "string",
            example = "Agentov"
    )
    @NotBlank
    @Pattern(regexp = "^[А-ЯЁA-Z][а-яёa-zA-Z]*$")
    @Size(min = 2, max = 255)
    String surname;
    @Schema(
            type = "string",
            example = "AGENT",
            description = "If it's CONTRACTOR, input value SALES"
    )
    TypeAgent agentType;

}

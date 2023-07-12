package com.example.SwipeRest.dto;

import com.example.SwipeRest.enums.TypeNotification;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class UserAddInfoDTO {
    @Schema(type = "boolean",example = "true")
    boolean callSms;
    @Schema(type = "date",example = "2023-5-5")
    @NotNull
    LocalDate dateSub;
    @Schema(type = "string",example = "ME")
    @NotNull
    TypeNotification notificationType;
}

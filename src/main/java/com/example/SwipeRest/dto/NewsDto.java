package com.example.SwipeRest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
@Data
@Builder
public class NewsDto {
    @Schema(example = "1")
    int id;
    @NotBlank
    @NotEmpty
    @Size(min = 3,max = 255)
    @Schema(example = "Description")
    String description;
    @NotBlank
    @NotEmpty
    @Size(min = 3,max = 50)
    @Schema(example = "Title")
    String title;
    @NotNull
    LocalDate date;
}

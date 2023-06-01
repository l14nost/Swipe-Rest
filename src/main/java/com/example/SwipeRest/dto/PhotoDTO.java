package com.example.SwipeRest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PhotoDTO {
    @Schema(example = "1")
    int idPhoto;
    @Schema(example = "../admin/dist/img/default.jpg")
    @NotBlank
    @NotEmpty
    String fileName;
}
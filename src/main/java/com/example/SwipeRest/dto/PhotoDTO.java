package com.example.SwipeRest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PhotoDTO {
    @Schema(example = "0")
    int id;
    @Schema(example = "../admin/dist/img/default.jpg")
    @NotBlank
    @Size(min = 1, max = 255)
    String fileName;
}

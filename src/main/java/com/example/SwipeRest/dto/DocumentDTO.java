package com.example.SwipeRest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DocumentDTO {
    @Schema(example = "1")
    int id;
    @NotBlank
    @Schema(example = "../admin/dist/img/document.jpg")
    @Size(min = 1, max = 255)
    String fileName;
    @NotBlank
    @Schema(example = "document.jpg")
    @Size(min = 1, max = 255)
    String name;
}

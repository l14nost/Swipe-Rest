package com.example.SwipeRest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DocumentDTO {
    @Schema(example = "1")
    int idDocuments;
    @NotBlank
    @NotEmpty
    @Schema(example = "../admin/dist/img/document.jpg")
    String fileName;
    @NotBlank
    @NotEmpty
    @Schema(example = "document.jpg")
    String name;
}

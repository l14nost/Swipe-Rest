package com.example.SwipeRest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class FrameDTO {
    @Schema(example = "1")
    int idFrame;
    @Schema(example = "1")
    int num;
    @Valid
    List<ApartmentDTO> apartments;
}

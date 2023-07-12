package com.example.SwipeRest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class FrameDTO {
    @Schema(example = "1")
    int id;
    @Schema(example = "1")
    int num;
    @Valid
    List<ApartmentDTO> apartments;
}

package com.example.SwipeRest.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class FrameDTO {
    int num;
    List<ApartmentDTO> apartments;
}

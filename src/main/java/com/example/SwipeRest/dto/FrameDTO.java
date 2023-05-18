package com.example.SwipeRest.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FrameDTO {
    int idFrame;
    int num;
    LcdDTO lcdDTO;
}

package com.example.SwipeRest.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PhotoDTO {
    String fileName;
    ApartmentDTO apartmentDTO;
    LcdDTO lcdDTO;
}

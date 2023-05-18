package com.example.SwipeRest.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
@Data
@Builder
public class NewsDto {
    String description;
    String title;
    LocalDate date;
    LcdDTO lcdDTO;
}

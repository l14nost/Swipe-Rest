package com.example.SwipeRest.dto;

import com.example.SwipeRest.enums.TypeNotification;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class UserAddInfoDTO {
    boolean callSms;
    LocalDate dateSub;
    TypeNotification typeNotification;
}

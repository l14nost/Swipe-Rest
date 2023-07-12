package com.example.SwipeRest.mapper;

import com.example.SwipeRest.dto.AgentDTO;
import com.example.SwipeRest.dto.UserAddInfoDTO;
import com.example.SwipeRest.entity.Agent;
import com.example.SwipeRest.entity.UserAddInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Function;


public class UserAddInfoMapper {

    public static UserAddInfo toEntity(UserAddInfoDTO userAddInfoDTO){
        return UserAddInfo.builder()
                .callSms(userAddInfoDTO.isCallSms())
                .dateSub(userAddInfoDTO.getDateSub())
                .typeNotification(userAddInfoDTO.getNotificationType())
                .build();
    }


    public static UserAddInfoDTO apply(UserAddInfo userAddInfo) {
        return UserAddInfoDTO.builder()
                .callSms(userAddInfo.isCallSms())
                .dateSub(userAddInfo.getDateSub())
                .notificationType(userAddInfo.getTypeNotification())
                .build();

    }
}

package com.example.SwipeRest.mapper;

import com.example.SwipeRest.dto.AgentDTO;
import com.example.SwipeRest.dto.UserAddInfoDTO;
import com.example.SwipeRest.entity.Agent;
import com.example.SwipeRest.entity.UserAddInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class UserAddInfoMapper implements Function<UserAddInfo, UserAddInfoDTO> {
    private final UserDtoMapper userMapper;
    public UserAddInfo toEntity(UserAddInfoDTO userAddInfoDTO){
        return UserAddInfo.builder()
                .callSms(userAddInfoDTO.isCallSms())
                .dateSub(userAddInfoDTO.getDateSub())
                .typeNotification(userAddInfoDTO.getTypeNotification())
                .build();
    }

    @Override
    public UserAddInfoDTO apply(UserAddInfo userAddInfo) {
        return UserAddInfoDTO.builder()
                .callSms(userAddInfo.isCallSms())
                .dateSub(userAddInfo.getDateSub())
                .typeNotification(userAddInfo.getTypeNotification())
                .build();

    }
}

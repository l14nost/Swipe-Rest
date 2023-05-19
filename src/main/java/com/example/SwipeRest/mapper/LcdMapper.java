package com.example.SwipeRest.mapper;

import com.example.SwipeRest.dto.AgentDTO;
import com.example.SwipeRest.dto.LcdDTO;
import com.example.SwipeRest.entity.Agent;
import com.example.SwipeRest.entity.LCD;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class LcdMapper implements Function<LCD, LcdDTO> {
    private final UserMapper userMapper;
    private final FrameMapper frameMapper;
//    private final UserDtoMapper userMapper;
//    public Agent toEntity(AgentDTO agentDTO){
//        return Agent.builder()
////                .idAgent(agentDTO.get)
//                .number(agentDTO.getNumber())
//                .mail(agentDTO.getMail())
//                .surname(agentDTO.getSurname())
//                .name(agentDTO.getName())
//                .type(agentDTO.getTypeAgent())
////                .users(agentDTO.getUsers().stream().map(userMapper).toList())
//                .build();
//    }

    @Override
    public LcdDTO apply(LCD lcd) {
        LcdDTO lcdDTO = LcdDTO.builder()
                .idLcd(lcd.getIdLcd())
                .advantages(lcd.getAdvantages())
                .appointment(lcd.getAppointment())
                .classType(lcd.getLcdClass())
                .communal(lcd.getCommunal())
                .description(lcd.getDescription())
                .distanceSea(lcd.getDistanceSea())
                .formalization(lcd.getFormalization())
                .frames(lcd.getFrames().stream().map(frameMapper).toList())
                .gasType(lcd.getGas())
                .heatingType(lcd.getHeating())
                .height(lcd.getHeight())
                .status(lcd.getStatus())
                .mainPhoto(lcd.getMainPhoto())
                .sewerage(lcd.getSewerage())
                .sumContractor(lcd.getSumContract())
                .technology(lcd.getTechnology())
                .territory(lcd.getTerritory())
                .typePayment(lcd.getTypePayment())
                .watterSupply(lcd.getWaterSupply())
                .name(lcd.getName())
                .build();

        if (lcd.getUser()!=null){
            lcdDTO.setUserDTO(userMapper.apply(lcd.getUser()));
        }
        return lcdDTO;

    }
}

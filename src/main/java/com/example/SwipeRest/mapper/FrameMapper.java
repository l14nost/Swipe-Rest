package com.example.SwipeRest.mapper;

import com.example.SwipeRest.dto.AgentDTO;
import com.example.SwipeRest.dto.FrameDTO;
import com.example.SwipeRest.entity.Agent;
import com.example.SwipeRest.entity.Frame;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class FrameMapper implements Function<Frame, FrameDTO> {
    private final ApartmentMapper apartmentMapper;
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
    public FrameDTO apply(Frame frame) {
        return FrameDTO.builder()
                .num(frame.getNum())
                .apartments(frame.getApartmentList().stream().map(apartmentMapper).toList())
                .build();

    }
}

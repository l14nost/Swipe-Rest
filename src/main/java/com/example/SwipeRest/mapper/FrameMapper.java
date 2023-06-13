package com.example.SwipeRest.mapper;

import com.example.SwipeRest.dto.AgentDTO;
import com.example.SwipeRest.dto.FrameDTO;
import com.example.SwipeRest.entity.Agent;
import com.example.SwipeRest.entity.Frame;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Function;
import java.util.stream.Collectors;


public class FrameMapper  {
    public static Frame toEntity(FrameDTO frameDTO){
        Frame frame = Frame.builder()
                .idFrame(frameDTO.getIdFrame())
                .num(frameDTO.getNum())
                .build();
        if (frameDTO.getApartments()!=null){
            frame.setApartmentList(frameDTO.getApartments().stream().map(ApartmentMapper::toEntity).collect(Collectors.toList()));
        }
        return frame;
    }

    public static FrameDTO apply(Frame frame) {
        return FrameDTO.builder()
                .idFrame(frame.getIdFrame())
                .num(frame.getNum())
                .apartments(frame.getApartmentList().stream().map(ApartmentMapper::apply).collect(Collectors.toList()))
                .build();

    }
}

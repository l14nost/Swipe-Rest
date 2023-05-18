package com.example.SwipeRest.mapper;

import com.example.SwipeRest.dto.AgentDTO;
import com.example.SwipeRest.dto.ClientDTO;
import com.example.SwipeRest.entity.Agent;
import com.example.SwipeRest.entity.User;
import com.example.SwipeRest.enums.Role;
import com.example.SwipeRest.enums.TypeUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class AgentMapper implements Function<Agent, AgentDTO> {
    private final UserDtoMapper userMapper;
    public Agent toEntity(AgentDTO agentDTO){
        return Agent.builder()
//                .idAgent(agentDTO.get)
                .number(agentDTO.getNumber())
                .mail(agentDTO.getMail())
                .surname(agentDTO.getSurname())
                .name(agentDTO.getName())
                .type(agentDTO.getTypeAgent())
//                .users(agentDTO.getUsers().stream().map(userMapper).toList())
                .build();
    }

    @Override
    public AgentDTO apply(Agent agent) {
        return AgentDTO.builder()
                .number(agent.getNumber())
                .typeAgent(agent.getType())
                .surname(agent.getSurname())
                .name(agent.getName())
                .build();

    }
}

package com.example.SwipeRest.mapper;

import com.example.SwipeRest.dto.AgentDTO;
import com.example.SwipeRest.entity.Agent;


public class AgentMapper{
    public static Agent toEntity(AgentDTO agentDTO){
        return Agent.builder()
                .idAgent(agentDTO.getIdAgent())
                .number(agentDTO.getNumber())
                .mail(agentDTO.getMail())
                .surname(agentDTO.getSurname())
                .name(agentDTO.getName())
                .type(agentDTO.getTypeAgent())
//                .users(agentDTO.getUsers().stream().map(userMapper).toList())
                .build();
    }

    public static AgentDTO apply(Agent agent) {
        return AgentDTO.builder()
                .idAgent(agent.getIdAgent())
                .mail(agent.getMail())
                .number(agent.getNumber())
                .typeAgent(agent.getType())
                .surname(agent.getSurname())
                .name(agent.getName())
                .build();

    }
}

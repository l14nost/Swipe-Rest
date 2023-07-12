package com.example.SwipeRest.mapper;

import com.example.SwipeRest.dto.AgentDTO;
import com.example.SwipeRest.entity.Agent;


public class AgentMapper{
    public static Agent toEntity(AgentDTO agentDTO){
        return Agent.builder()
                .idAgent(agentDTO.getId())
                .number(agentDTO.getNumber())
                .mail(agentDTO.getEmail())
                .surname(agentDTO.getSurname())
                .name(agentDTO.getName())
                .type(agentDTO.getAgentType())
//                .users(agentDTO.getUsers().stream().map(userMapper).toList())
                .build();
    }

    public static AgentDTO apply(Agent agent) {
        return AgentDTO.builder()
                .id(agent.getIdAgent())
                .email(agent.getMail())
                .number(agent.getNumber())
                .agentType(agent.getType())
                .surname(agent.getSurname())
                .name(agent.getName())
                .build();

    }
}

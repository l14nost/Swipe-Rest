package com.example.SwipeRest.service.impl;

import com.example.SwipeRest.entity.Agent;
import com.example.SwipeRest.enums.TypeAgent;
import com.example.SwipeRest.repository.AgentRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class AgentServiceImplTest {
    @Mock
    private AgentRepo agentRepo;
    @InjectMocks
    private AgentServiceImpl agentService;

    @Test
    public void saveEntityTest(){
        Agent agent = Agent.builder()
                .type(TypeAgent.AGENT)
                .mail("newmail@gmail.com")
                .name("AgentTest")
                .surname("MockTest")
                .number("123123123")
                .build();
        Agent agent1 = Agent.builder()
                .type(TypeAgent.AGENT)
                .mail("newmai@gmail.com")
                .name("AgentTest")
                .surname("MockTest")
                .number("123123123")
                .build();
        agentService.saveEntity(agent);
        verify(agentRepo).save(agent);
    }
    @Test
    void findByIdAgentTest(){
        Agent agent = Agent.builder()
                .idAgent(8)
                .name("11")
                .surname("11")
                .mail("11")
                .number("11")
                .type(TypeAgent.AGENT).build();
        when(agentRepo.findById(8)).thenReturn(Optional.of(agent));

        Agent res = agentService.findById(8);

        assertEquals("11",res.getName());
        verify(agentRepo, times(1)).findById(8);
    }
    @Test
    public void findByIdAgentTestFailed(){
        when(agentRepo.findById(1)).thenReturn(Optional.empty());
        assertNull(agentService.findById(1));
    }
    @Test
    public void findAllTest(){
        List<Agent> agents = new ArrayList<>();
        for (int i = 0; i<28;i++){
            agents.add(new Agent());
        }
        when(agentRepo.findAll()).thenReturn(agents);

        List<Agent> agentList = agentService.findAll();
        assertEquals(28,agentList.size());
    }

    @Test
    public void deleteById(){
        agentService.deleteById(1);
        verify(agentRepo).deleteById(1);
    }

    @Test
    public void updateEntity(){
        Agent agent = Agent.builder()
                .idAgent(1)
                .name("11")
                .surname("11")
                .mail("11")
                .number("11")
                .type(TypeAgent.AGENT).build();
        when(agentRepo.findById(1)).thenReturn(Optional.of(agent));
        Agent agentUpdate = Agent.builder()
                .idAgent(1)
                .name("Agent")
                .surname("Surname")
                .mail("agent@gmail.com")
                .number("123123123")
                .type(TypeAgent.AGENT).build();
        agentService.updateEntity(agentUpdate,1);
        Agent agentSave = Agent.builder()
                .name("Agent")
                .surname("Surname")
                .mail("agent@gmail.com")
                .number("123123123")
                .type(TypeAgent.AGENT).build();
        verify(agentRepo).saveAndFlush(agentSave);
    }
}
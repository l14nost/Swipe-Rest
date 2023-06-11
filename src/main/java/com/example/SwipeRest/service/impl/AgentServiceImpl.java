package com.example.SwipeRest.service.impl;

import com.example.SwipeRest.controller.ApartmentController;
import com.example.SwipeRest.entity.Agent;
import com.example.SwipeRest.repository.AgentRepo;
import com.example.SwipeRest.service.AgentService;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgentServiceImpl implements AgentService {
    private Logger log = LoggerFactory.getLogger(AgentServiceImpl.class);
    private final AgentRepo agentRepo;


    public AgentServiceImpl(AgentRepo agentRepo) {
        this.agentRepo = agentRepo;
    }

    @Override
    public List<Agent> findAll() {
        log.info("All agent");
        return agentRepo.findAll();
    }

    @Override
    public Agent findById(int id) {
        Optional<Agent> agent = agentRepo.findById(id);
        if(agent.isPresent()){
            log.info("Find agent:"+id);
            return agent.get();
        }
        else {
            log.info("Not find agent:"+id);
            return null;
        }
    }

    @Override
    public void saveEntity(Agent agent) {
        agentRepo.save(agent);
        log.info("Agent save");
    }

    @Override
    public void deleteById(int id) {
        agentRepo.deleteById(id);
        log.info("Agent delete");
    }

    @Override
    public void updateEntity(Agent agent, int id) {
        Optional<Agent> agentOptional = agentRepo.findById(id);
        if(agentOptional.isPresent()){
            Agent agentUpdate = agentOptional.get();
            if(agent.getName()!=null){
                agentUpdate.setName(agent.getName());
            }
            if(agent.getMail()!=null){
                agentUpdate.setMail(agent.getMail());
            }
            if(agent.getNumber()!=null){
                agentUpdate.setNumber(agent.getNumber());
            }
            if(agent.getSurname()!=null){
                agentUpdate.setSurname(agent.getSurname());
            }
            agentRepo.saveAndFlush(agentUpdate);
            log.info("Agent:"+id+" update");
        }
    }
}

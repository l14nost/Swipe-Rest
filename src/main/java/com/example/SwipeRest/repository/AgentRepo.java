package com.example.SwipeRest.repository;

import com.example.SwipeRest.entity.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgentRepo extends JpaRepository<Agent, Integer>{
    List<Agent> findAllByMail(String mail);
}

package com.example.SwipeRest.repository;

import com.example.SwipeRest.entity.Frame;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FrameRepo extends JpaRepository<Frame,Integer>{
}

package com.example.SwipeRest.repository;


import com.example.SwipeRest.entity.LCD;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface LCDRepo extends JpaRepository<LCD, Integer>, JpaSpecificationExecutor<LCD> {
    Page<LCD> findAll(Pageable pageable);
}

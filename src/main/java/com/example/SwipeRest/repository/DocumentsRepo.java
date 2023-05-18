package com.example.SwipeRest.repository;

import com.example.SwipeRest.entity.Documents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentsRepo extends JpaRepository<Documents, Integer> {
}

package com.example.SwipeRest.repository;

import com.example.SwipeRest.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepo extends JpaRepository<News, Integer> {
}

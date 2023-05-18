package com.example.SwipeRest.repository;

import com.example.SwipeRest.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotosRepo extends JpaRepository<Photo, Integer> {
}

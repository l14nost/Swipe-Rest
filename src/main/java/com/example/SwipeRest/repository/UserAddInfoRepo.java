package com.example.SwipeRest.repository;

import com.example.SwipeRest.entity.UserAddInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAddInfoRepo extends JpaRepository<UserAddInfo, Integer> {
}

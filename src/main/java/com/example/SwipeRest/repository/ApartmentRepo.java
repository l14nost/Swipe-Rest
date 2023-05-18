package com.example.SwipeRest.repository;

import com.example.SwipeRest.entity.Apartment;
import com.example.SwipeRest.entity.Frame;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApartmentRepo extends JpaRepository<Apartment, Integer>, JpaSpecificationExecutor<Apartment> {
    List<Apartment> findAllByFrameIsNull();
    Page<Apartment> findAllByFrameIsNull(Pageable pageable);

    List<Apartment> findAllByFrame(Frame frame);
    Page<Apartment> findAllByFrame(Frame frame,Pageable pageable);
}

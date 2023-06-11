package com.example.SwipeRest.service.impl;

import com.example.SwipeRest.controller.ApartmentController;
import com.example.SwipeRest.entity.Apartment;
import com.example.SwipeRest.entity.Frame;
import com.example.SwipeRest.repository.FrameRepo;
import com.example.SwipeRest.service.FrameService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class FrameServiceImpl implements FrameService {
    private Logger log = LoggerFactory.getLogger(FrameServiceImpl.class);
    private final FrameRepo frameRepo;
    private final ApartmentServiceImpl apartmentService;


    @Override
    public List<Frame> findAll() {
        log.info("All frame");
        return frameRepo.findAll();

    }

    @Override
    public Frame findById(int id) {
        Optional<Frame> frame = frameRepo.findById(id);
        if(frame.isPresent()){
            log.info("Frame find "+id);
            return frame.get();
        }
        else {
            log.info("Frame not find "+id);
            return null;
        }
    }

    @Override
    public void saveEntity(Frame frame) {
        frameRepo.save(frame);
        log.info("Frame save");
    }

    @Override
    public void deleteById(int id) {
        frameRepo.deleteById(id);
        log.info("Frame delete "+id);
    }

    @Override
    public void updateEntity(Frame frame, int id) {
        Optional<Frame> frameOptional = frameRepo.findById(id);
        if(frameOptional.isPresent()){
            Frame frameUpdate = frameOptional.get();
            if(frame.getApartmentList()!=null){
                List<Apartment> apartments = frame.getApartmentList();
                apartments.stream().forEach(apartment -> {
                    apartmentService.updateEntity(apartment, apartment.getIdApartment());
                    log.info("Apartment update "+apartment.getIdApartment());
                });
            }
            if(frame.getNum()!=0){
                frameUpdate.setNum(frame.getNum());
            }
            frameRepo.saveAndFlush(frameUpdate);
            log.info("Frame update "+id);
        }
    }
}

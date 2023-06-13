package com.example.SwipeRest.service.impl;

import com.example.SwipeRest.controller.ApartmentController;
import com.example.SwipeRest.dto.LcdDTO;
import com.example.SwipeRest.entity.*;
import com.example.SwipeRest.mapper.LcdMapper;
import com.example.SwipeRest.repository.LCDRepo;
import com.example.SwipeRest.service.LCDService;
import com.example.SwipeRest.specification.LcdSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LCDServiceImpl implements LCDService {
    private Logger log = LoggerFactory.getLogger(LCDServiceImpl.class);
    private final LCDRepo lcdRepo;
    private final PhotosServiceImpl photosService;
    private final FrameServiceImpl frameService;
    private final DocumentsServiceImpl documentsService;
    private final NewsServiceImpl newsService;

    public LcdDTO findByIdDTO(int id) {
        Optional<LCD> lcd = lcdRepo.findById(id);
        if(lcd.isPresent()){
            log.info("Lcd find "+id );
            return LcdMapper.apply(lcd.get());
        }
        else {
            log.info("Lcd not find "+id );

            return null;
        }
    }

    public List<LcdDTO> findAllDTO() {
        log.info("All lcdDto");
        return lcdRepo.findAll().stream().map(LcdMapper::apply).collect(Collectors.toList());
    }
    @Override
    public List<LCD> findAll() {
        log.info("All lcd");
        return lcdRepo.findAll();
    }

    @Override
    public LCD findById(int id) {
        Optional<LCD> lcd = lcdRepo.findById(id);
        if(lcd.isPresent()){
            log.info("Lcd find "+id);
            return lcd.get();
        }
        else {
            log.info("Lcd not find "+id);
            return null;
        }
    }
    public String saveDTO(LcdDTO lcdDTO) {
        LCD lcd = LcdMapper.toEntity(lcdDTO);
        lcd.setUser(User.builder().idUser(lcdDTO.getContractor()).build());
        if (lcd.getPhotoList()!=null) {
            List<Photo> photos = lcd.getPhotoList();
            photos.stream().forEach(photo -> {
                photo.setIdPhotos(0);
                photo.setLcd(lcd);
                log.info("Photo save");

            });
        }
        if (lcd.getFrames()!=null) {
            List<Frame> frames = lcd.getFrames();
            frames.stream().forEach(frame -> {
                frame.setLcd(lcd);
                log.info("Frame save");
                if (frame.getApartmentList()!=null) {
                    frame.getApartmentList().stream().forEach(apartment -> {
                        apartment.setIdApartment(0);
                        apartment.setLcd(lcd);
                        apartment.setFrame(frame);
                        apartment.setUser(User.builder().idUser(lcdDTO.getContractor()).build());
                        log.info("Apartment save");
                        if (apartment.getPhotoList()!=null) {
                            List<Photo> photos = apartment.getPhotoList();
                            photos.stream().forEach(photo -> {
                                photo.setIdPhotos(0);
                                photo.setApartment(apartment);
                            });
                        }

                    });
                }
            });
        }
        if (lcd.getDocuments()!=null) {
            List<Documents> documents = lcd.getDocuments();
            documents.stream().forEach(documents1 -> {
                        documents1.setIdDocuments(0);
                        documents1.setLcd(lcd);
                        log.info("Document save");

                    }
            );
        }
        if (lcd.getNewsList()!=null) {
            List<News> news = lcd.getNewsList();
            news.stream().forEach(news1 -> {
                        news1.setIdNews(0);
                        news1.setLcd(lcd);
                        log.info("News save");
                    }
            );
        }
        lcdRepo.saveAndFlush(lcd);
        log.info("Lcd save");
        return "Success save";
    }
    @Override
    public void saveEntity(LCD lcd) {
        lcdRepo.save(lcd);
        log.info("Lcd save");
    }

    @Override
    public void deleteById(int id) {
        lcdRepo.deleteById(id);
        log.info("Lcd delete");
    }

    @Override
    public void updateEntity(LCD lcd, int id) {
        Optional<LCD> lcdOptional = lcdRepo.findById(id);
        if(lcdOptional.isPresent()){
            LCD lcdUpdate = lcdOptional.get();
            if(lcd.getMainPhoto()!=null){
                lcdUpdate.setMainPhoto(lcd.getMainPhoto());
            }
            if(lcd.getAdvantages()!=null){
                lcdUpdate.setAdvantages(lcd.getAdvantages());
            }
            if(lcd.getAppointment() !=null){
                lcdUpdate.setAppointment(lcd.getAppointment());
            }
            if(lcd.getCommunal()!=null){
                lcdUpdate.setCommunal(lcd.getCommunal());
            }
            if(lcd.getGas()!=null){
                lcdUpdate.setGas(lcd.getGas());
            }
            if(lcd.getHeating()!=null){
                lcdUpdate.setHeating(lcd.getHeating());
            }
            if (lcd.getAddress()!=null){
                lcdUpdate.setAddress(lcd.getAddress());
            }
            if(lcd.getName()!=null){
                lcdUpdate.setName(lcd.getName());
            }
            if(lcd.getSewerage()!=null){
                lcdUpdate.setSewerage(lcd.getSewerage());
            }
            if(lcd.getStatus()!=null){
                lcdUpdate.setStatus(lcd.getStatus());
            }
            if(lcd.getTechnology()!=null){
                lcdUpdate.setTechnology(lcd.getTechnology());
            }
            if(lcd.getMainPhoto()!=null){
                lcdUpdate.setMainPhoto(lcd.getMainPhoto());
            }
            if(lcd.getDistanceSea()!=0){
                lcdUpdate.setDistanceSea(lcd.getDistanceSea());
            }
            if(lcd.getHeight()!=0){
                lcdUpdate.setHeight(lcd.getHeight());
            }
            if(lcd.getSumContract()!=null){
                lcdUpdate.setSumContract(lcd.getSumContract());
            }
            if(lcd.getTerritory()!=null){
                lcdUpdate.setTerritory(lcd.getTerritory());
            }
            if(lcd.getType()!=null){
                lcdUpdate.setType(lcd.getType());
            }
            if(lcd.getTypePayment()!=null){
                lcdUpdate.setTypePayment(lcd.getTypePayment());
            }
            if(lcd.getWaterSupply()!=null){
                lcdUpdate.setWaterSupply(lcd.getWaterSupply());
            }
            if(lcd.getDescription()!=null){
                lcdUpdate.setDescription(lcd.getDescription());
            }
            if(lcd.getLcdClass()!=null){
                lcdUpdate.setLcdClass(lcd.getLcdClass());
            }
            if(lcd.getUser()!=null){
                lcdUpdate.setUser(lcd.getUser());
            }
            if(lcd.getFormalization()!=null){
                lcdUpdate.setFormalization(lcd.getFormalization());
            }
            if (lcd.getNewsList()!=null){
                lcdUpdate.setNewsList(lcd.getNewsList());
            }
            lcdRepo.saveAndFlush(lcdUpdate);
            log.info("Lcd update"+id);
        }

    }

    public String updateDTO(LcdDTO lcdDTO,int id) {
        LCD lcd = LcdMapper.toEntity(lcdDTO);
        Optional<LCD> lcdOptional = lcdRepo.findById(id);
        if(lcdOptional.isPresent()){
            LCD lcdUpdate = lcdOptional.get();
            if(lcd.getMainPhoto()!=null){
                lcdUpdate.setMainPhoto(lcd.getMainPhoto());
            }
            if(lcd.getAdvantages()!=null){
                lcdUpdate.setAdvantages(lcd.getAdvantages());
            }
            if(lcd.getAppointment() !=null){
                lcdUpdate.setAppointment(lcd.getAppointment());
            }
            if(lcd.getCommunal()!=null){
                lcdUpdate.setCommunal(lcd.getCommunal());
            }
            if(lcd.getGas()!=null){
                lcdUpdate.setGas(lcd.getGas());
            }
            if(lcd.getHeating()!=null){
                lcdUpdate.setHeating(lcd.getHeating());
            }
            if(lcd.getName()!=null){
                lcdUpdate.setName(lcd.getName());
            }
            if(lcd.getSewerage()!=null){
                lcdUpdate.setSewerage(lcd.getSewerage());
            }
            if(lcd.getStatus()!=null){
                lcdUpdate.setStatus(lcd.getStatus());
            }
            if(lcd.getTechnology()!=null){
                lcdUpdate.setTechnology(lcd.getTechnology());
            }
            if(lcd.getMainPhoto()!=null){
                lcdUpdate.setMainPhoto(lcd.getMainPhoto());
            }
            if(lcd.getDistanceSea()!=0){
                lcdUpdate.setDistanceSea(lcd.getDistanceSea());
            }
            if(lcd.getHeight()!=0){
                lcdUpdate.setHeight(lcd.getHeight());
            }
            if(lcd.getSumContract()!=null){
                lcdUpdate.setSumContract(lcd.getSumContract());
            }
            if(lcd.getTerritory()!=null){
                lcdUpdate.setTerritory(lcd.getTerritory());
            }
            if(lcd.getType()!=null){
                lcdUpdate.setType(lcd.getType());
            }
            if(lcd.getTypePayment()!=null){
                lcdUpdate.setTypePayment(lcd.getTypePayment());
            }
            if(lcd.getWaterSupply()!=null){
                lcdUpdate.setWaterSupply(lcd.getWaterSupply());
            }
            if(lcd.getDescription()!=null){
                lcdUpdate.setDescription(lcd.getDescription());
            }
            if(lcd.getLcdClass()!=null){
                lcdUpdate.setLcdClass(lcd.getLcdClass());
            }
            if (lcd.getAddress()!=null){
                lcdUpdate.setAddress(lcd.getAddress());
            }
            if(lcd.getFormalization()!=null){
                lcdUpdate.setFormalization(lcd.getFormalization());
            }
            if (lcd.getFrames()!=null){
                List<Frame> frames = lcd.getFrames();
                frames.stream().forEach(frame -> {
                    frameService.updateEntity(frame, frame.getIdFrame());
                    log.info("Frame update"+frame.getIdFrame());

                });

            }
            if (lcd.getPhotoList()!=null){
                List<Photo> photos= lcd.getPhotoList();
                photos.stream().forEach(photo -> {
                    photosService.updateEntity(photo, photo.getIdPhotos());
                    log.info("Photo update"+photo.getIdPhotos());
                });
            }
            if (lcd.getDocuments()!=null){
                List<Documents> documents = lcd.getDocuments();
                documents.stream().forEach(documents1 -> {
                    documentsService.updateEntity(documents1, documents1.getIdDocuments());
                    log.info("Document update"+documents1.getIdDocuments());
                });
            }
            if (lcd.getNewsList()!=null){
                List<News> news = lcd.getNewsList();
                news.stream().forEach(news1 -> {
                    newsService.updateEntity(news1, news1.getIdNews());
                    log.info("News update"+news1.getIdNews());
                });
            }
            if (lcdDTO.getContractor()!=0){
                lcdUpdate.setUser(User.builder().idUser(lcdDTO.getContractor()).build());
                log.info("User update"+lcdDTO.getContractor());
            }
            lcdRepo.saveAndFlush(lcdUpdate);
            log.info("Lcd update"+id);
            return "Success:\n"+LcdMapper.apply(lcdUpdate);
        }
        else return "Something went wrong";
    }
}

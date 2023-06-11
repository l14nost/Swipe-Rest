package com.example.SwipeRest.service.impl;

import com.example.SwipeRest.controller.ApartmentController;
import com.example.SwipeRest.entity.Photo;
import com.example.SwipeRest.repository.PhotosRepo;
import com.example.SwipeRest.service.PhotosService;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhotosServiceImpl implements PhotosService {
    private Logger log = LoggerFactory.getLogger(PhotosServiceImpl.class);
    private final PhotosRepo photosRepo;

    public PhotosServiceImpl(PhotosRepo photosRepo) {
        this.photosRepo = photosRepo;
    }

    @Override
    public List<Photo> findAll() {
        log.info("All photo");
        return photosRepo.findAll();
    }

    @Override
    public Photo findById(int id) {
        Optional<Photo> photos = photosRepo.findById(id);
        if(photos.isPresent()){
            log.info("Photo find "+id);
            return photos.get();
        }
        else {
            log.info("Photo not find "+id);

            return null;
        }
    }

    @Override
    public void saveEntity(Photo photo) {
        photosRepo.save(photo);
        log.info("Photo save");

    }

    @Override
    public void deleteById(int id) {
        photosRepo.deleteById(id);
        log.info("Photo delete "+id);

    }

    @Override
    public void updateEntity(Photo photo, int id) {
        Optional<Photo> photosOptional = photosRepo.findById(id);
        if(photosOptional.isPresent()){
            Photo photoUpdate = photosOptional.get();
            if(photo.getFileName()!=null){
                photoUpdate.setFileName(photo.getFileName());
            }
            photosRepo.saveAndFlush(photoUpdate);
            log.info("Photo update "+id);

        }
    }
}

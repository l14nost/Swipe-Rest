package com.example.SwipeRest.service.impl;

import com.example.SwipeRest.controller.ApartmentController;
import com.example.SwipeRest.dto.ApartmentDTO;
import com.example.SwipeRest.entity.Apartment;
import com.example.SwipeRest.entity.LCD;
import com.example.SwipeRest.entity.Photo;
import com.example.SwipeRest.entity.User;
import com.example.SwipeRest.mapper.ApartmentMapper;
import com.example.SwipeRest.repository.ApartmentRepo;
import com.example.SwipeRest.service.ApartmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ApartmentServiceImpl implements ApartmentService {
    private Logger log = LoggerFactory.getLogger(ApartmentServiceImpl.class);
    private final ApartmentRepo apartmentRepo;
    private final UserServiceImpl userService;
    private final PhotosServiceImpl photosService;
//    private static final Logger log = LogManager.getLogger(AgentServiceImpl.class);

    @Override
    public List<Apartment> findAll() {
        log.info("All apartment");
        return apartmentRepo.findAll();

    }

    public void lcdIdToNull(Apartment apartment){
        apartment.setLcd(null);
        apartmentRepo.saveAndFlush(apartment);
    }
    public List<ApartmentDTO> findAllDTO(){
        log.info("All apartmentDTO");
        return apartmentRepo.findAllByFrameIsNull().stream().map(ApartmentMapper::apply).collect(Collectors.toList());
    }
    public ApartmentDTO findByIdDTO(int id) {
        Optional<Apartment> apartment = apartmentRepo.findById(id);
        if(apartment.isPresent()){
            log.info("Apartment found by id:"+id);
            return ApartmentMapper.apply(apartment.get());
        }
        else {
            log.info("Apartment not found by id:"+id);
            return null;
        }
    }
    @Override
    public Apartment findById(int id) {
        Optional<Apartment> apartment = apartmentRepo.findById(id);
        if(apartment.isPresent()){
            log.info("Apartment found by id:"+id);
            return apartment.get();
        }
        else {
            log.info("Apartment not found by id:"+id);
            return null;
        }
    }
    public ApartmentDTO saveDTO(ApartmentDTO apartmentDTO) {
            Apartment apartment = ApartmentMapper.toEntity(apartmentDTO);
            apartment.setUser(User.builder().idUser(apartmentDTO.getClient()).build());
            if (apartment.getPhotoList() != null) {
                List<Photo> photos = apartment.getPhotoList();
                apartment.setPhotoList(photos);
            }
            apartmentRepo.save(apartment);
            log.info("Apartment save");
            return apartmentDTO;
    }
    @Override
    public void saveEntity(Apartment apartment) {
        log.info("Apartment save");
        apartmentRepo.save(apartment);

    }

    @Override
    public void deleteById(int id) {
        log.info("Apartment save");
        apartmentRepo.deleteById(id);
    }

    @Override
    public void updateEntity(Apartment apartment, int id) {
        Optional<Apartment> apartmentOptional = apartmentRepo.findById(id);
        if(apartmentOptional.isPresent()){
            Apartment apartmentUpdate = apartmentOptional.get();
            if(apartment.getMainPhoto()!=null){
                apartmentUpdate.setMainPhoto(apartment.getMainPhoto());
            }
            if(apartment.getCommission()!=null){
                apartmentUpdate.setCommission(apartment.getCommission());
            }
            if(apartment.getCalculation()!=null){
                apartmentUpdate.setCalculation(apartment.getCalculation());
            }
            if(apartment.getBalconyType()!=null){
                apartmentUpdate.setBalconyType(apartment.getBalconyType());
            }
            if (apartment.getAddress()!=null){
                apartmentUpdate.setAddress(apartment.getAddress());
            }
            if(apartment.getLcd()!=null){
                apartmentUpdate.setLcd(apartment.getLcd());
            }
            if(apartment.getDescription()!=null){
                apartmentUpdate.setDescription(apartment.getDescription());
            }
            if(apartment.getFoundingDocument()!=null){
                apartmentUpdate.setFoundingDocument(apartment.getFoundingDocument());
            }
            if(apartment.getCommunicationType()!=null){
                apartmentUpdate.setCommunicationType(apartment.getCommunicationType());
            }
            if(apartment.getCountRoom()!=null){
                apartmentUpdate.setCountRoom(apartment.getCountRoom());
            }
            if(apartment.getMainPhoto()!=null){
                apartmentUpdate.setMainPhoto(apartment.getMainPhoto());
            }
            if(apartment.getTotalArea()!=0){
                apartmentUpdate.setTotalArea(apartment.getTotalArea());
            }
            if(apartment.getKitchenArea()!=0){
                apartmentUpdate.setKitchenArea(apartment.getKitchenArea());
            }
            if(apartment.getNumber()!=0){
                apartmentUpdate.setNumber(apartment.getNumber());
            }
            if(apartment.getLayout()!=null){
                apartmentUpdate.setLayout(apartment.getLayout());
            }
            if(apartment.getPrice()!=0){
                apartmentUpdate.setPrice(apartment.getPrice());
            }
            if(apartment.getMainPhoto()!=null){
                apartmentUpdate.setMainPhoto(apartment.getMainPhoto());
            }
            if(apartment.getState()!=null){
                apartmentUpdate.setState(apartment.getState());
            }
            if(apartment.getType()!=null){
                apartmentUpdate.setType(apartment.getType());
            }
            if(apartment.getUser()!=null){
                userService.updateEntity(apartmentUpdate.getUser(), apartmentUpdate.getUser().getIdUser());
                log.info("User update:"+apartmentUpdate.getUser().getIdUser());
            }
            apartmentRepo.saveAndFlush(apartmentUpdate);
            log.info("Apartment update:"+id);
        }
    }
    public ApartmentDTO updateDto(ApartmentDTO apartmentDTO, int id) {
        Apartment apartment = ApartmentMapper.toEntity(apartmentDTO);
        System.out.println(apartment);
        Optional<Apartment> apartmentOptional = apartmentRepo.findById(id);
        if(apartmentOptional.isPresent()){
            Apartment apartmentUpdate = apartmentOptional.get();
            if(apartment.getMainPhoto()!=null){
                apartmentUpdate.setMainPhoto(apartment.getMainPhoto());
            }
            if(apartment.getCommission()!=null){
                apartmentUpdate.setCommission(apartment.getCommission());
            }
            if(apartment.getCalculation()!=null){
                apartmentUpdate.setCalculation(apartment.getCalculation());
            }
            if (apartment.getAddress()!=null){
                apartmentUpdate.setAddress(apartment.getAddress());
            }
            if(apartment.getBalconyType()!=null){
                apartmentUpdate.setBalconyType(apartment.getBalconyType());
            }
            if(apartment.getDescription()!=null){
                apartmentUpdate.setDescription(apartment.getDescription());
            }
            if(apartment.getFoundingDocument()!=null){
                apartmentUpdate.setFoundingDocument(apartment.getFoundingDocument());
            }
            if(apartment.getCommunicationType()!=null){
                apartmentUpdate.setCommunicationType(apartment.getCommunicationType());
            }
            if(apartment.getCountRoom()!=null){
                apartmentUpdate.setCountRoom(apartment.getCountRoom());
            }
            if(apartment.getTotalArea()!=0){
                apartmentUpdate.setTotalArea(apartment.getTotalArea());
            }
            if(apartment.getKitchenArea()!=0){
                apartmentUpdate.setKitchenArea(apartment.getKitchenArea());
            }
            if(apartment.getNumber()!=0){
                apartmentUpdate.setNumber(apartment.getNumber());
            }
            if(apartment.getLayout()!=null){
                apartmentUpdate.setLayout(apartment.getLayout());
            }
            if(apartment.getPrice()!=0){
                apartmentUpdate.setPrice(apartment.getPrice());
            }
            if(apartment.getState()!=null){
                apartmentUpdate.setState(apartment.getState());
            }
            if(apartment.getType()!=null){
                apartmentUpdate.setType(apartment.getType());
            }
            if(apartment.getBalconyType()!=null){
                apartmentUpdate.setBalconyType(apartment.getBalconyType());
            }
            if(apartment.getHeatingType()!=null){
                apartmentUpdate.setHeatingType(apartment.getHeatingType());
            }
            if(apartmentUpdate.getUser()!=null){
                apartmentUpdate.setUser(User.builder().idUser(apartmentDTO.getClient()).build());
                log.info("User update:"+apartmentUpdate.getUser().getIdUser());
            }
            if (apartmentDTO.getIdLcd()!=0){
                apartmentUpdate.setLcd(LCD.builder().idLcd(apartmentDTO.getIdLcd()).build());
            }
            if (apartment.getPhotoList()!=null){
                List<Photo> photos = apartment.getPhotoList();
                photos.stream().forEach(photo -> {
                    photosService.updateEntity(photo, photo.getIdPhotos());
                    log.info("Photo update:"+photo.getIdPhotos());
                });
            }
            System.out.println(apartmentUpdate);
            apartmentRepo.saveAndFlush(apartmentUpdate);
            log.info("Apartment update:"+id);
            return ApartmentMapper.apply(apartmentUpdate);
        }
        return ApartmentDTO.builder().build();
    }
}

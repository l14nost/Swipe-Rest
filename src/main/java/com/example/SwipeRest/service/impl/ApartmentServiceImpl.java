package com.example.SwipeRest.service.impl;

import com.example.SwipeRest.entity.Apartment;
import com.example.SwipeRest.entity.Frame;
import com.example.SwipeRest.repository.ApartmentRepo;
import com.example.SwipeRest.service.ApartmentService;
import com.example.SwipeRest.specification.ApartmentForFrameSpecification;
import com.example.SwipeRest.specification.ApartmentForLcdSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApartmentServiceImpl implements ApartmentService {
    private final ApartmentRepo apartmentRepo;

    public ApartmentServiceImpl(ApartmentRepo apartmentRepo) {
        this.apartmentRepo = apartmentRepo;
    }

    @Override
    public List<Apartment> findAll() {
        return apartmentRepo.findAll();
    }

    public List<Apartment> findAllByFrame(){
        return apartmentRepo.findAllByFrameIsNull();
    }

    public Page<Apartment> findAllByFramePagination(Pageable pageable,int keyWord){
        if(keyWord != 0){
            ApartmentForLcdSpecification apartmentForLcdSpecification = ApartmentForLcdSpecification.builder().keyWord(keyWord).build();
            return apartmentRepo.findAll(apartmentForLcdSpecification,pageable);
        }
        return apartmentRepo.findAllByFrameIsNull(pageable);
    }

    public Page<Apartment> findAllForFramePagination(Frame frame, Pageable pageable, int keyWord){
        if (keyWord!=0){
            ApartmentForFrameSpecification apartmentForFrameSpecification = ApartmentForFrameSpecification.builder().keyWord(keyWord).frame(frame).build();
            return apartmentRepo.findAll(apartmentForFrameSpecification,pageable);
        }
        return apartmentRepo.findAllByFrame(frame,pageable);
    }

    @Override
    public Apartment findById(int id) {
        Optional<Apartment> apartment = apartmentRepo.findById(id);
        if(apartment.isPresent()){
            return apartment.get();
        }
        else {
            return Apartment.builder().build();
        }
    }

    @Override
    public void saveEntity(Apartment apartment) {
        apartmentRepo.save(apartment);
    }

    @Override
    public void deleteById(int id) {
        apartmentRepo.deleteById(id);
    }

    @Override
    public void updateEntity(Apartment apartment, int id) {
        Optional<Apartment> apartmentOptional = apartmentRepo.findById(id);
        if(apartmentOptional.isPresent()){
            Apartment apartmentUpdate = apartmentOptional.get();
            if(apartment.getPhotoList()!=null){
                apartmentUpdate.setPhotoList(apartment.getPhotoList());
            }
            if(apartment.getMainPhoto()!=null){
                apartmentUpdate.setMainPhoto(apartment.getMainPhoto());
            }
            if(apartment.getAddress()!=null){
                apartmentUpdate.setAddress(apartment.getAddress());
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
                apartmentUpdate.setUser(apartment.getUser());
            }

            apartmentRepo.saveAndFlush(apartmentUpdate);
        }
    }
}

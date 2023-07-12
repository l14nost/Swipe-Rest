package com.example.SwipeRest.mapper;

import com.example.SwipeRest.dto.ApartmentDTO;
import com.example.SwipeRest.entity.Apartment;
import com.example.SwipeRest.entity.LCD;

import java.util.stream.Collectors;


public class ApartmentMapper{
    public static Apartment toEntity(ApartmentDTO apartmentDTO){
        Apartment apartment = Apartment.builder()
                .idApartment(apartmentDTO.getId())
                .communicationType(apartmentDTO.getCommunicationType())
                .countRoom(apartmentDTO.getCountRoom())
                .foundingDocument(apartmentDTO.getFoundingDocument())
                .price(apartmentDTO.getPrice())
                .state(apartmentDTO.getState())
                .commission(apartmentDTO.getCommission())
                .type(apartmentDTO.getType())
                .heatingType(apartmentDTO.getHeatingType())
                .layout(apartmentDTO.getLayout())
                .balconyType(apartmentDTO.getBalcony())
                .calculation(apartmentDTO.getCalculation())
                .kitchenArea(apartmentDTO.getKitchenArea())
                .totalArea(apartmentDTO.getTotalArea())
                .mainPhoto(apartmentDTO.getMainPhoto())
                .number(apartmentDTO.getNumber())
                .address(apartmentDTO.getAddress())
                .description(apartmentDTO.getDescription())
                .build();
        if (apartmentDTO.getPhotos()!=null){
            apartment.setPhotoList(apartmentDTO.getPhotos().stream().map(PhotoMapper::toEntity).collect(Collectors.toList()));        }
        if (apartmentDTO.getIdLcd()!=0){
            apartment.setLcd(LCD.builder().idLcd(apartmentDTO.getIdLcd()).build());
        }
        return apartment;
    }

    public static ApartmentDTO apply(Apartment apartment) {
        ApartmentDTO apartmentDTO = ApartmentDTO.builder()
                .id(apartment.getIdApartment())
                .calculation(apartment.getCalculation())
                .commission(apartment.getCommission())
                .communicationType(apartment.getCommunicationType())
                .countRoom(apartment.getCountRoom())
                .description(apartment.getDescription())
                .address(apartment.getAddress())
                .foundingDocument(apartment.getFoundingDocument())
                .heatingType(apartment.getHeatingType())
                .price(apartment.getPrice())
                .layout(apartment.getLayout())
                .state(apartment.getState())
                .kitchenArea(apartment.getKitchenArea())
                .type(apartment.getType())
                .mainPhoto(apartment.getMainPhoto())
                .totalArea(apartment.getTotalArea())
                .number(apartment.getNumber())
                .balcony(apartment.getBalconyType())
                .build();
        if (apartment.getUser()!=null){
            apartmentDTO.setClient(apartment.getUser().getIdUser());
        }
        if (apartment.getPhotoList()!=null){
            apartmentDTO.setPhotos(apartment.getPhotoList().stream().map(PhotoMapper::apply).collect(Collectors.toList()));
        }
        return apartmentDTO;


    }
}

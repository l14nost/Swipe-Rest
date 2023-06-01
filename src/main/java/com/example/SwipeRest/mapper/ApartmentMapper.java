package com.example.SwipeRest.mapper;

import com.example.SwipeRest.dto.ApartmentDTO;
import com.example.SwipeRest.entity.Apartment;


public class ApartmentMapper{
    public static Apartment toEntity(ApartmentDTO apartmentDTO){
        Apartment apartment = Apartment.builder()
                .idApartment(apartmentDTO.getIdApartment())
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
            apartment.setPhotoList(apartmentDTO.getPhotos().stream().map(PhotoMapper::toEntity).toList());
        }
        return apartment;
    }

    public static ApartmentDTO apply(Apartment apartment) {
        ApartmentDTO apartmentDTO = ApartmentDTO.builder()
                .idApartment(apartment.getIdApartment())
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
            apartmentDTO.setPhotos(apartment.getPhotoList().stream().map(PhotoMapper::apply).toList());
        }
        return apartmentDTO;


    }
}

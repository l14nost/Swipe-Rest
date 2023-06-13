package com.example.SwipeRest.mapper;

import com.example.SwipeRest.dto.LcdDTO;
import com.example.SwipeRest.entity.LCD;

import java.util.stream.Collectors;


public class LcdMapper  {

    public static LCD toEntity(LcdDTO lcdDTO){
        LCD lcd = LCD.builder()
                .idLcd(lcdDTO.getIdLcd())
                .name(lcdDTO.getName())
                .description(lcdDTO.getDescription())
                .distanceSea(lcdDTO.getDistanceSea())
                .formalization(lcdDTO.getFormalization())
                .communal(lcdDTO.getCommunal())
                .status(lcdDTO.getStatus())
                .height(lcdDTO.getHeight())
                .advantages(lcdDTO.getAdvantages())
                .appointment(lcdDTO.getAppointment())
                .sewerage(lcdDTO.getSewerage())
                .gas(lcdDTO.getGasType())
                .waterSupply(lcdDTO.getWatterSupply())
                .heating(lcdDTO.getHeatingType())
                .lcdClass(lcdDTO.getClassType())
                .mainPhoto(lcdDTO.getMainPhoto())
                .address(lcdDTO.getAddress())
                .technology(lcdDTO.getTechnology())
                .territory(lcdDTO.getTerritory())
                .type(lcdDTO.getType())
                .sumContract(lcdDTO.getSumContractor())
                .status(lcdDTO.getStatus())
                .typePayment(lcdDTO.getTypePayment())
                .build();
        if (lcdDTO.getDocuments()!=null){
            lcd.setDocuments(lcdDTO.getDocuments().stream().map(DocumentMapper::toEntity).collect(Collectors.toList()));
        }
        if (lcdDTO.getFrames()!=null){
            lcd.setFrames(lcdDTO.getFrames().stream().map(FrameMapper::toEntity).collect(Collectors.toList()));
        }
        if (lcdDTO.getPhotos()!=null){
            lcd.setPhotoList(lcdDTO.getPhotos().stream().map(PhotoMapper::toEntity).collect(Collectors.toList()));
        }
        if (lcdDTO.getNewsList()!=null){
            lcd.setNewsList(lcdDTO.getNewsList().stream().map(NewsMapper::toEntity).collect(Collectors.toList()));
        }
        return lcd;
    }

    public static LcdDTO apply(LCD lcd) {
        LcdDTO lcdDTO = LcdDTO.builder()
                .idLcd(lcd.getIdLcd())
                .advantages(lcd.getAdvantages())
                .appointment(lcd.getAppointment())
                .classType(lcd.getLcdClass())
                .communal(lcd.getCommunal())
                .description(lcd.getDescription())
                .distanceSea(lcd.getDistanceSea())
                .formalization(lcd.getFormalization())
                .gasType(lcd.getGas())
                .heatingType(lcd.getHeating())
                .height(lcd.getHeight())
                .status(lcd.getStatus())
                .mainPhoto(lcd.getMainPhoto())
                .sewerage(lcd.getSewerage())
                .address(lcd.getAddress())
                .sumContractor(lcd.getSumContract())
                .technology(lcd.getTechnology())
                .territory(lcd.getTerritory())
                .typePayment(lcd.getTypePayment())
                .watterSupply(lcd.getWaterSupply())
                .name(lcd.getName())
                .type(lcd.getType())
                .build();

        if (lcd.getUser()!=null){
            lcdDTO.setContractor(lcd.getUser().getIdUser());
        }
        if (lcd.getFrames()!=null){
            lcdDTO.setFrames(lcd.getFrames().stream().map(FrameMapper::apply).collect(Collectors.toList()));
        }
        if (lcd.getPhotoList()!=null){
            lcdDTO.setPhotos(lcd.getPhotoList().stream().map(PhotoMapper::apply).collect(Collectors.toList()));
        }
        if (lcd.getDocuments()!=null){
            lcdDTO.setDocuments(lcd.getDocuments().stream().map(DocumentMapper::apply).collect(Collectors.toList()));
        }
        return lcdDTO;

    }
}

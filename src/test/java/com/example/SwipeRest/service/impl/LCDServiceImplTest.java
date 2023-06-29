package com.example.SwipeRest.service.impl;

import com.example.SwipeRest.dto.*;
import com.example.SwipeRest.entity.*;
import com.example.SwipeRest.enums.*;
import com.example.SwipeRest.repository.LCDRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LCDServiceImplTest {
    @Mock
    private FrameServiceImpl frameService;
    @Mock
    private PhotosServiceImpl photosService;
    @Mock
    private  DocumentsServiceImpl documentsService;
    @Mock
    private NewsServiceImpl newsService;
    @Mock
    private LCDRepo lcdRepo;
    @InjectMocks
    private LCDServiceImpl lcdService;


    @Test
    void findAll() {
        List<LCD> lcds = Arrays.asList(
                LCD.builder().build(),
                LCD.builder().build(),
                LCD.builder().build()
        );
        when(lcdRepo.findAll()).thenReturn(lcds);
        List<LCD> lcdList = lcdService.findAll();
        assertEquals(3,lcdList.size());

    }
    @Test
    void findAllDto() {
        List<LCD> lcds = Arrays.asList(
                LCD.builder().build(),
                LCD.builder().build(),
                LCD.builder().build()
        );
        when(lcdRepo.findAll()).thenReturn(lcds);
        List<LcdDTO> lcdList = lcdService.findAllDTO();
        assertEquals(3,lcdList.size());

    }

    @Test
    void findByIdDTO() {
        LCD lcd = LCD.builder()
                .idLcd(1)
                .name("Name")
                .type(LCDType.FIVE)
                .heating(HeatingType.AUTONOMOUS)
                .build();
        when(lcdRepo.findById(1)).thenReturn(Optional.of(lcd));
        LcdDTO lcdDTO = lcdService.findByIdDTO(1);
        assertEquals("Name",lcdDTO.getName());
        assertEquals(LCDType.FIVE,lcdDTO.getType());
        assertEquals(HeatingType.AUTONOMOUS,lcdDTO.getHeatingType());
        assertNull(lcdService.findByIdDTO(2));
    }

    @Test
    void findById() {
        LCD lcd = LCD.builder()
                .idLcd(1)
                .name("Name")
                .type(LCDType.FIVE)
                .heating(HeatingType.AUTONOMOUS)
                .build();
        when(lcdRepo.findById(1)).thenReturn(Optional.of(lcd));
        LCD lcdDTO = lcdService.findById(1);
        assertEquals("Name",lcdDTO.getName());
        assertEquals(LCDType.FIVE,lcdDTO.getType());
        assertEquals(HeatingType.AUTONOMOUS,lcdDTO.getHeating());
        assertNull(lcdService.findById(2));
    }

    @Test
    void saveDTO() {
        User user = User.builder()
                .idUser(1)
                .number("123123123")
                .mail("123@gmail.com")
                .name("User")
                .surname("Surname")
                .blackList(false)
                .role(Role.USER)
                .build();
        LcdDTO lcdDTO = LcdDTO.builder()
                .name("Name")
                .classType(ClassType.MASS)
                .contractor(12)
                .photos(Arrays.asList(
                        PhotoDTO.builder().idPhoto(1).fileName("").build()
                ))
                .documents(Arrays.asList(
                        DocumentDTO.builder().idDocuments(1).fileName("").build()
                ))
                .frames(Arrays.asList(
                        FrameDTO.builder().idFrame(1).num(1).apartments(Arrays.asList(ApartmentDTO.builder().idApartment(1).number(115).photos(List.of(PhotoDTO.builder().idPhoto(2).fileName("").build())).build())).build()
                ))
                .newsList(Arrays.asList(
                        NewsDto.builder().idNews(1).title("").build()
                ))
                .build();
        lcdService.saveDTO(lcdDTO);
        LCD lcd = LCD.builder()
                .name("Name")
                .user(user)
                .lcdClass(ClassType.MASS)
                .photoList(Arrays.asList(
                        Photo.builder().idPhotos(1).fileName("").build()
                ))
                .documents(Arrays.asList(
                        Documents.builder().idDocuments(1).fileName("").build()
                ))
                .frames(Arrays.asList(
                        Frame.builder().num(1).idFrame(1).build()
                ))
                .newsList(Arrays.asList(
                        News.builder().idNews(1).title("").build()
                ))
                .build();
        verify(lcdRepo).saveAndFlush(lcd);
    }

    @Test
    void saveEntity() {
        LCD lcd = LCD.builder()
                .name("Name")
                .lcdClass(ClassType.MASS)
                .mainPhoto("../admin/dist/img/default.jpg")
                .build();
        lcdService.saveEntity(lcd);
        verify(lcdRepo).save(lcd);
    }

    @Test
    void deleteById() {
        when(lcdRepo.findById(1)).thenReturn(Optional.of(LCD.builder().build()));
        lcdService.deleteById(1);
        verify(lcdRepo).deleteById(1);
    }

    @Test
    void updateEntity() {
        LCD lcd =LCD.builder()
                .idLcd(1)
                .name("Name")
                .description("Description")
                .sumContract("Sum")
                .build();
        User user = User.builder()
                .idUser(1)
                .number("123123123")
                .mail("123@gmail.com")
                .name("User")
                .surname("Surname")
                .blackList(false)
                .role(Role.USER)
                .build();
        when(lcdRepo.findById(1)).thenReturn(Optional.of(lcd));
        LCD lcdUpdate = LCD.builder()
                .name("Name")
                .description("Description1")
                .sumContract("Sum1")
                .territory(TerritoryType.CLOSE)
                .technology(TechnologyType.PANEL)
                .gas(GasType.NO)
                .waterSupply(HeatingType.AUTONOMOUS)
                .lcdClass(ClassType.MASS)
                .type(LCDType.FIVE)
                .heating(HeatingType.AUTONOMOUS)
                .address("г.Город, р.Район, вул.Вулиця,1")
                .sewerage(HeatingType.CENTRAL)

                .appointment("")
                .height(12)
                .status(StatusLCDType.APARTMENT)
                .advantages("")
                .communal(CommunalType.HALF)
                .formalization("")
                .distanceSea(12)
                .typePayment("")
                .mainPhoto("")
                .user(user)
                .frames(Arrays.asList(
                        Frame.builder().idFrame(1).num(1).apartmentList(Arrays.asList(
                                Apartment.builder().idApartment(1).number(115).build()
                        )).build()
                ))
                .documents(Arrays.asList(
                        Documents.builder().idDocuments(1).fileName("").build()
                ))
                .photoList(Arrays.asList(
                        Photo.builder().idPhotos(1).fileName("").build()
                ))
                .newsList(Arrays.asList(
                        News.builder().idNews(1).title("").build()
                ))
                .build();
        lcdService.updateEntity(lcdUpdate,1);
        LCD lcdSave = LCD.builder()
                .idLcd(1)
                .name("Name")
                .description("Description1")
                .sumContract("Sum1")
                .territory(TerritoryType.CLOSE)
                .technology(TechnologyType.PANEL)
                .gas(GasType.NO)
                .waterSupply(HeatingType.AUTONOMOUS)
                .lcdClass(ClassType.MASS)
                .type(LCDType.FIVE)
                .heating(HeatingType.AUTONOMOUS)
                .sewerage(HeatingType.CENTRAL)
                .appointment("")
                .height(12)
                .address("г.Город, р.Район, вул.Вулиця,1")
                .status(StatusLCDType.APARTMENT)
                .advantages("")
                .communal(CommunalType.HALF)
                .formalization("")
                .distanceSea(12)
                .typePayment("")
                .mainPhoto("")
                .user(user)
                .frames(Arrays.asList(
                        Frame.builder().idFrame(1).num(1).apartmentList(Arrays.asList(
                                Apartment.builder().idApartment(1).number(115).build()
                        )).build()
                ))
                .documents(Arrays.asList(
                        Documents.builder().idDocuments(1).fileName("").build()
                ))
                .photoList(Arrays.asList(
                        Photo.builder().idPhotos(1).fileName("").build()
                ))
                .newsList(Arrays.asList(
                        News.builder().idNews(1).title("").build()
                ))
                .build();
        verify(lcdRepo).saveAndFlush(lcdSave);
    }

    @Test
    void updateDTO() {
        LCD lcd =LCD.builder()
                .idLcd(1)
                .name("Name")
                .description("Description")
                .sumContract("Sum")
                .build();
        when(lcdRepo.findById(1)).thenReturn(Optional.of(lcd));
        LcdDTO lcdUpdate = LcdDTO.builder()
                .name("Name")
                .description("Description1")
                .sumContractor("Sum1")
                .territory(TerritoryType.CLOSE)
                .technology(TechnologyType.PANEL)
                .gasType(GasType.NO)
                .watterSupply(HeatingType.AUTONOMOUS)
                .classType(ClassType.MASS)
                .type(LCDType.FIVE)
                .address("г.Город, р.Район, вул.Вулиця,1")
                .heatingType(HeatingType.AUTONOMOUS)
                .sewerage(HeatingType.CENTRAL)
                .appointment("")
                .height(12)
                .status(StatusLCDType.APARTMENT)
                .advantages("")
                .communal(CommunalType.HALF)
                .formalization("")
                .distanceSea(12)
                .typePayment("")
                .mainPhoto("")
                .contractor(12)
                .frames(Arrays.asList(
                        FrameDTO.builder().idFrame(1).num(1).apartments(Arrays.asList(
                                ApartmentDTO.builder().idApartment(1).number(115).build()
                        )).build()
                ))
                .documents(Arrays.asList(
                        DocumentDTO.builder().idDocuments(1).fileName("").build()
                ))
                .photos(Arrays.asList(
                        PhotoDTO.builder().fileName("").build()
                ))
                .newsList(Arrays.asList(
                        NewsDto.builder().idNews(1).title("").build()
                ))
                .build();
        lcdService.updateDTO(lcdUpdate,1);
        LCD lcdSave = LCD.builder()
                .idLcd(1)
                .name("Name")
                .description("Description1")
                .sumContract("Sum1")
                .territory(TerritoryType.CLOSE)
                .technology(TechnologyType.PANEL)
                .gas(GasType.NO)
                .waterSupply(HeatingType.AUTONOMOUS)
                .lcdClass(ClassType.MASS)
                .type(LCDType.FIVE)
                .address("г.Город, р.Район, вул.Вулиця,1")
                .heating(HeatingType.AUTONOMOUS)
                .sewerage(HeatingType.CENTRAL)
                .appointment("")
                .height(12)
                .status(StatusLCDType.APARTMENT)
                .advantages("")
                .communal(CommunalType.HALF)
                .formalization("")
                .distanceSea(12)
                .typePayment("")
                .mainPhoto("")
                .user(User.builder().idUser(12).build())
                .frames(Arrays.asList(
                        Frame.builder().idFrame(1).num(1).apartmentList(Arrays.asList(
                                Apartment.builder().idApartment(1).number(115).build()
                        )).build()
                ))
                .documents(Arrays.asList(
                        Documents.builder().idDocuments(1).fileName("").build()
                ))
                .photoList(Arrays.asList(
                        Photo.builder().idPhotos(1).fileName("").build()
                ))
                .newsList(Arrays.asList(
                        News.builder().idNews(1).title("").build()
                ))
                .build();
        verify(lcdRepo).saveAndFlush(lcdSave);
        assertEquals("Something went wrong",lcdService.updateDTO(lcdUpdate,2));

    }
}
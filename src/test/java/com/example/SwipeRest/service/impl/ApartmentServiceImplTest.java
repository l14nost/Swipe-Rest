package com.example.SwipeRest.service.impl;

import com.example.SwipeRest.dto.ApartmentDTO;
import com.example.SwipeRest.dto.PhotoDTO;
import com.example.SwipeRest.dto.ClientDTO;
import com.example.SwipeRest.entity.*;
import com.example.SwipeRest.enums.*;
import com.example.SwipeRest.repository.ApartmentRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

class ApartmentServiceImplTest {

    @Mock
    private ApartmentRepo apartmentRepo;
    @Mock
    private UserServiceImpl userService;
    @Mock
    private PhotosServiceImpl photosService;
    @InjectMocks
    private ApartmentServiceImpl apartmentService;


    @Test
    void lcdIdToNull(){
        apartmentService.lcdIdToNull(Apartment.builder().lcd(LCD.builder().idLcd(1).build()).build());
        verify(apartmentRepo).saveAndFlush(Apartment.builder().lcd(null).build());
    }
    @Test
    void findAllDTO() {
        List<Apartment> apartments = new ArrayList<>();
        for (int i = 0;i<18;i++){
            apartments.add(Apartment.builder().build());
        }
        when(apartmentRepo.findAllByFrameIsNull()).thenReturn(apartments);
        List<ApartmentDTO> apartmentsList = apartmentService.findAllDTO();
        assertEquals(18,apartmentsList.size());
    }

    @Test
    void findAll() {
        List<Apartment> apartments = new ArrayList<>();
        for (int i = 0;i<18;i++){
            apartments.add(Apartment.builder().build());
        }
        when(apartmentRepo.findAll()).thenReturn(apartments);
        List<Apartment> apartmentsList = apartmentService.findAll();
        assertEquals(18,apartmentsList.size());
    }


    @Test
    void findByIdDTO() {
        ApartmentDTO apartmentDTO = ApartmentDTO.builder()
                .id(6)
                .number(122)
                .build();
        Apartment apartment = Apartment.builder()
                .idApartment(6)
                .number(122)
                .build();
        when(apartmentRepo.findById(6)).thenReturn(Optional.of(apartment));
        ApartmentDTO apartmentDTOTest = apartmentService.findByIdDTO(6);
        assertEquals(apartmentDTO.getNumber(),apartmentDTOTest.getNumber());

    }

    @Test
    void findById() {
        Apartment apartment = Apartment.builder()
                .idApartment(6)
                .number(122)
                .build();
        when(apartmentRepo.findById(6)).thenReturn(Optional.of(apartment));
        Apartment apartmentDTOTest = apartmentService.findById(6);
        assertEquals(122,apartmentDTOTest.getNumber());
        assertNull(apartmentService.findById(2));

    }
    @Test
    void findByIdDTONotFound() {
        when(apartmentRepo.findById(1)).thenReturn(Optional.empty());
        ApartmentDTO apartmentDTO = apartmentService.findByIdDTO(1);
        assertNull(apartmentDTO);

    }

    @Test
    void saveDTO() {
        ApartmentDTO apartmentDTO = ApartmentDTO.builder()
                .number(500)
                .photos(Arrays.asList(
                        PhotoDTO.builder().id(1).fileName("").build()
                ))
                .build();
        Apartment apartment = Apartment.builder()
                .number(500)
                .photoList(Arrays.asList(
                        Photo.builder().idPhotos(1).fileName("").build()
                ))
                .build();
        apartmentService.saveDTO(apartmentDTO);
        verify(apartmentRepo).save(apartment);

    }

    @Test
    void saveEntity() {
        Apartment apartment = Apartment.builder()
                .idApartment(1)
                .number(500)
                .totalArea(100)
                .description("")
                .build();
        apartmentService.saveEntity(apartment);

        verify(apartmentRepo).save(apartment);
    }

    @Test
    void deleteById() {
        apartmentService.deleteById(1);
        verify(apartmentRepo).deleteById(1);
    }

    @Test
    void updateEntity() {
        Apartment apartment = Apartment.builder()
                .idApartment(1)
                .number(100)
                .description("")
                .totalArea(100)
                .user(User.builder().idUser(1).build())
                .photoList(new ArrayList<>())
                .type(TypeApartment.APARTMENT)
                .layout(LayoutType.STUDIO)
                .state(State.REPAIR)
                .price(100000)
                .kitchenArea(100)
                .calculation(Calculation.CAPITAL)
                .foundingDocument(FoundingDocument.TREATY)
                .frame(Frame.builder().idFrame(1).build())
                .countRoom(CountRoom.K1)
                .communicationType(CommunicationType.SMS)
                .commission(Commission.K10)
                .balconyType(BalconyType.NO)
                .heatingType(HeatingType.INDIVIDUAL)
                .mainPhoto("")
                .lcd(LCD.builder().idLcd(1).build())
                .build();
        when(apartmentRepo.findById(1)).thenReturn(Optional.of(apartment));
        Apartment apartmentUpdate = Apartment.builder()
                .number(101)
                .description("")
                .totalArea(100)
                .user(User.builder().idUser(1).name("Name").build())
                .photoList(new ArrayList<>())
                .type(TypeApartment.APARTMENT)
                .layout(LayoutType.STUDIO)
                .state(State.REPAIR)
                .price(100001)
                .address("г.Город, р.Район, вул.Вулиця,1")
                .kitchenArea(101)
                .calculation(Calculation.CAPITAL)
                .foundingDocument(FoundingDocument.TREATY)
                .frame(Frame.builder().idFrame(1).build())
                .countRoom(CountRoom.K1)
                .communicationType(CommunicationType.SMS)
                .commission(Commission.K10)
                .balconyType(BalconyType.NO)
                .heatingType(HeatingType.INDIVIDUAL)
                .mainPhoto("123")
                .lcd(LCD.builder().idLcd(1).build())
                .build();
        apartmentService.updateEntity(apartmentUpdate,1);
        Apartment apartmentSave = Apartment.builder()
                .idApartment(1)
                .number(101)
                .description("")
                .totalArea(100)
                .user(User.builder().idUser(1).name("Name").build())
                .photoList(new ArrayList<>())
                .type(TypeApartment.APARTMENT)
                .layout(LayoutType.STUDIO)
                .state(State.REPAIR)
                .price(100001)
                .kitchenArea(101)
                .address("г.Город, р.Район, вул.Вулиця,1")
                .calculation(Calculation.CAPITAL)
                .foundingDocument(FoundingDocument.TREATY)
                .frame(Frame.builder().idFrame(1).build())
                .countRoom(CountRoom.K1)
                .communicationType(CommunicationType.SMS)
                .commission(Commission.K10)
                .balconyType(BalconyType.NO)
                .heatingType(HeatingType.INDIVIDUAL)
                .mainPhoto("123")
                .lcd(LCD.builder().idLcd(1).build())
                .build();
        verify(apartmentRepo).saveAndFlush(apartmentSave);
    }

    @Test
    void updateDTO() {
        Apartment apartment = Apartment.builder()
                .idApartment(1)
                .number(100)
                .description("")
                .totalArea(100)
                .user(User.builder().idUser(1).build())
                .photoList(new ArrayList<>())
                .type(TypeApartment.APARTMENT)
                .layout(LayoutType.STUDIO)
                .state(State.REPAIR)
                .price(100000)
                .kitchenArea(100)
                .calculation(Calculation.CAPITAL)
                .foundingDocument(FoundingDocument.TREATY)
                .frame(Frame.builder().idFrame(1).build())
                .countRoom(CountRoom.K1)
                .communicationType(CommunicationType.SMS)
                .commission(Commission.K10)
                .balconyType(BalconyType.NO)
                .heatingType(HeatingType.INDIVIDUAL)
                .mainPhoto("")
                .build();
        when(apartmentRepo.findById(1)).thenReturn(Optional.of(apartment));
        ApartmentDTO apartmentUpdate = ApartmentDTO.builder()
                .number(101)
                .description("")
                .totalArea(100)
                .client(7)
                .photos(Arrays.asList(PhotoDTO.builder().id(1).fileName("").build()))
                .type(TypeApartment.APARTMENT)
                .layout(LayoutType.STUDIO)
                .state(State.REPAIR)
                .price(100001)
                .idLcd(1)
                .address("г.Город, р.Район, вул.Вулиця,1")
                .kitchenArea(101)
                .calculation(Calculation.CAPITAL)
                .foundingDocument(FoundingDocument.TREATY)
                .countRoom(CountRoom.K1)
                .communicationType(CommunicationType.SMS)
                .commission(Commission.K10)
                .balcony(BalconyType.NO)
                .heatingType(HeatingType.INDIVIDUAL)
                .mainPhoto("123")
                .build();
        apartmentService.updateDto(apartmentUpdate,1);
        Apartment apartmentSave = Apartment.builder()
                .idApartment(1)
                .number(101)
                .description("")
                .totalArea(100)
                .user(User.builder().idUser(7).build())
                .photoList(Arrays.asList(Photo.builder().idPhotos(1).fileName("").build()))
                .type(TypeApartment.APARTMENT)
                .layout(LayoutType.STUDIO)
                .state(State.REPAIR)
                .price(100001)
                .address("г.Город, р.Район, вул.Вулиця,1")
                .kitchenArea(101)
                .lcd(LCD.builder().idLcd(1).build())
                .calculation(Calculation.CAPITAL)
                .foundingDocument(FoundingDocument.TREATY)
                .frame(Frame.builder().idFrame(1).build())
                .countRoom(CountRoom.K1)
                .communicationType(CommunicationType.SMS)
                .commission(Commission.K10)
                .balconyType(BalconyType.NO)
                .heatingType(HeatingType.INDIVIDUAL)
                .mainPhoto("123")
                .build();
        verify(apartmentRepo).saveAndFlush(apartmentSave);
        assertEquals(ApartmentDTO.builder().build(),apartmentService.updateDto(apartmentUpdate,2));
    }
}
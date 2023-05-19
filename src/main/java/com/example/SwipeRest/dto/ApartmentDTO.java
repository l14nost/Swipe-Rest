package com.example.SwipeRest.dto;

import com.example.SwipeRest.entity.Frame;
import com.example.SwipeRest.enums.*;
import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@Builder
public class ApartmentDTO {
    int  idApartment;
    Calculation calculation;
    Commission commission;
    CommunicationType communicationType;
    CountRoom countRoom;
    String description;
    HeatingType heatingType;
    int kitchenArea;
    LayoutType layout;
    int price;
    State state;
    int totalArea;
    TypeApartment type;
    int idLcd;
    int number;
    String mainPhoto;
    FoundingDocument foundingDocument;
    UserDTO userDTO;

}

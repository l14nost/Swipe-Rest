package com.example.SwipeRest.dto;

import com.example.SwipeRest.enums.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LcdDTO {
    int idLcd;
    String advantages;
    String appointment;
    CommunalType communal;
    int distanceSea;
    GasType gasType;
    HeatingType heatingType;
    int height;
    String name;
    HeatingType sewerage;
    StatusLCDType status;
    String sumContractor;
    TechnologyType technology;
    TerritoryType territory;
    String typePayment;
    HeatingType watterSupply;
    UserDTO userDTO;
    String mainPhoto;
    String description;
    ClassType classType;
    String formalization;

}

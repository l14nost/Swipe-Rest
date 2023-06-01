package com.example.SwipeRest.dto;

import com.example.SwipeRest.enums.*;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class LcdDTO {
    @Schema(example = "1")
    int idLcd;
    @Schema(example = "Advantages")
    @NotBlank
    @NotEmpty
    String advantages;
    @Schema(example = "Appointment")
    @NotBlank
    @NotEmpty
    String appointment;
    @Schema(example = "PAYMENTS")
    @NotNull
    CommunalType communal;
    @Schema(example = "25")
    int distanceSea;
    @NotNull
    @Schema(example = "YES")
    GasType gasType;
    @NotNull
    @Schema(example = "AUTONOMOUS")
    HeatingType heatingType;
    @Schema(example = "2")
    int height;
    @Schema(example = "LcdName")
    @NotBlank
    @NotEmpty
    String name;
    @NotNull
    @Schema(example = "AUTONOMOUS")
    HeatingType sewerage;
    @Schema(example = "APARTMENT")
    @NotNull
    StatusLCDType status;
    @Schema(example = "Sum Contract")
    @NotBlank
    @NotEmpty
    String sumContractor;
    @Schema(example = "MONOLITH")
    @NotNull
    TechnologyType technology;
    @Schema(example = "CLOSE")
    @NotNull
    TerritoryType territory;
    @Schema(example = "HALF")
    @NotBlank
    @NotEmpty
    String typePayment;
    @Schema(example = "AUTONOMOUS")
    @NotNull
    HeatingType watterSupply;
    @Schema(example = "12")
    int contractor;
    @Schema(example = "../admin/dist/img/default.jpg")
    @NotBlank
    @NotEmpty
    String mainPhoto;
    @Schema(example = "Description")
    @NotBlank
    @NotEmpty
    String description;
    @Schema(example = "ELITE")
    @NotNull
    ClassType classType;
    @Schema(example = "г.Город, р.Район, вул.Вулиця,1")
    @NotBlank
    @NotEmpty
    @Pattern(message = "г.Город, р.Район, вул.Вулиця,1",regexp = "г\\.[A-Za-zА-Яа-я]+, р\\.[A-Za-zА-Яа-я]+, вул\\.[A-Za-zА-Яа-я]+,\\d+")
    String address;
    @Schema(example = "MANY")
    @NotNull
    LCDType type;
    @Schema(example = "Formalization")
    @NotBlank
    @NotEmpty
    String formalization;
    @Valid
    List<FrameDTO> frames;
    @Valid
    List<PhotoDTO> photos;
    @Valid
    List<DocumentDTO> documents;
    List<NewsDto> newsList;

}

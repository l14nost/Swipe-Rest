package com.example.SwipeRest.dto;

import com.example.SwipeRest.enums.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.Valid;
import javax.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class LcdDTO {
    @Schema(example = "0")
    int idLcd;
    @Schema(example = "Advantages")
    @NotBlank
    @NotEmpty
    @Size(min = 3,max = 150)
    String advantages;
    @Schema(example = "Appointment")
    @NotBlank
    @NotEmpty
    @Size(min = 3,max = 150)
    String appointment;
    @Schema(example = "PAYMENTS")
    @NotNull
    CommunalType communal;
    @Schema(example = "25")
            @Min(10)
            @Max(200)
    int distanceSea;
    @NotNull
    @Schema(example = "YES")
    GasType gasType;
    @NotNull
    @Schema(example = "AUTONOMOUS")
    HeatingType heatingType;
    @Schema(example = "2")
            @Min(2)
            @Max(5)
    int height;
    @Schema(example = "LcdName")
    @NotBlank
    @NotEmpty
    @Pattern(regexp = "^[А-ЯЁA-Z][а-яёa-zA-Z]*$")
    String name;
    @NotNull
    @Schema(example = "AUTONOMOUS")
    HeatingType sewerage;
    @Schema(example = "APARTMENT")
    @NotNull
    StatusLCDType status;
    @Schema(example = "Sum")
    @NotBlank
    @NotEmpty
    @Size(min = 3,max = 150)
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
    @Size(min = 3,max = 150)
    String typePayment;
    @Schema(example = "AUTONOMOUS")
    @NotNull
    HeatingType watterSupply;
    @Schema(example = "4")
    int contractor;
    @Schema(example = "../admin/dist/img/default.jpg")
    @NotBlank
    @NotEmpty
    String mainPhoto;
    @Schema(example = "Description")
    @NotBlank
    @NotEmpty
    @Size(min = 3,max = 250)
    String description;
    @Schema(example = "ELITE")
    @NotNull
    ClassType classType;
    @Schema(example = "г.Город, р.Район, ул.Улица,1")
    @NotBlank
    @NotEmpty
    @Pattern(message = "г.Город, р.Район, ул.Улица,1",regexp = "г\\.[A-Za-zА-Яа-я]+, р\\.[A-Za-zА-Яа-я]+, вул\\.[A-Za-zА-Яа-я]+,\\d+")
    String address;
    @Schema(example = "MANY")
    @NotNull
    LCDType type;
    @Schema(example = "Formalization")
    @NotBlank
    @NotEmpty
    @Size(min = 3,max = 150)
    String formalization;
    @Valid
    List<FrameDTO> frames;
    @Valid
    List<PhotoDTO> photos;
    @Valid
    List<DocumentDTO> documents;
    List<NewsDto> newsList;

}

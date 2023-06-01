package com.example.SwipeRest.dto;

import com.example.SwipeRest.enums.*;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@Builder
public class ApartmentDTO {
    @Schema(example = "1")
    int idApartment;
    @Schema(example = "RECEIPT")
    @NotNull
    Calculation calculation;
    @Schema(example = "K10")
    @NotNull
    Commission commission;
    @Schema(example = "CALLSMS")
    @NotNull
    CommunicationType communicationType;
    @Schema(example = "K1")
    @NotNull
    CountRoom countRoom;
    @Schema(example = "Description")
    @NotNull
            @Size(min = 3,max = 1000)
    String description;
    @Schema(example = "AUTONOMOUS")
    @NotNull
    HeatingType heatingType;
    @Schema(example = "20")
    int kitchenArea;
    @Schema(example = "CLASSICAL")
    @NotNull
    LayoutType layout;
    @Schema(example = "10000")
    int price;
    @Schema(example = "REPAIR")
    @NotNull
    State state;
    @Schema(example = "100")
    int totalArea;
    @Schema(example = "COMMUNAL")
    TypeApartment type;
    @Schema(example = "4")
    int idLcd;
    @Schema(example = "100")
    int number;
    @Schema(example = "../admin/dist/img/default.jpg")
    @NotBlank
    @NotEmpty
    String mainPhoto;
    @Schema(example = "г.Город, р.Район, вул.Вулиця,1")
    @NotBlank
    @NotEmpty
    @Pattern(message = "г.Город, р.Район, вул.Вулиця,1",regexp = "г\\.[A-Za-zА-Яа-я]+, р\\.[A-Za-zА-Яа-я]+, вул\\.[A-Za-zА-Яа-я]+,\\d+")
    String address;
    @Schema(example = "TREATY")
    @NotNull
    FoundingDocument foundingDocument;
    @Schema(example = "YES")
    @NotNull
    BalconyType balcony;
    @Valid
    List<PhotoDTO> photos;
    @Schema(example = "7")
    int client;

}

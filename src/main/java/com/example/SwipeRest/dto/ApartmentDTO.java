package com.example.SwipeRest.dto;

import com.example.SwipeRest.enums.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.Valid;
import javax.validation.constraints.*;
import lombok.*;

import java.util.List;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApartmentDTO {
    @Schema(example = "0")
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
    @Min(5)
    @Max(25)
    int kitchenArea;
    @Schema(example = "CLASSICAL")
    @NotNull
    LayoutType layout;
    @Schema(example = "200001")
    @Min(200000)
    @Max(2000000)
    int price;
    @Schema(example = "REPAIR")
    @NotNull
    State state;
    @Schema(example = "100")
    @Min(20)
    @Max(200)
    int totalArea;
    @Schema(example = "COMMUNAL")
    TypeApartment type;
    @Schema(example = "0")
    int idLcd;
    @Schema(example = "100")
    @Min(1)
    @Max(500)
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
    @Schema(example = "9")
    int client;

}

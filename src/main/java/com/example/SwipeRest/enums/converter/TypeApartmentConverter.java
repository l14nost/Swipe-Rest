package com.example.SwipeRest.enums.converter;

import com.example.SwipeRest.enums.TypeApartment;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class TypeApartmentConverter implements AttributeConverter<TypeApartment, String> {

    @Override
    public String convertToDatabaseColumn(TypeApartment attribute) {
        if(attribute == null){
            return null;
        }
        else {
            return attribute.getValue();
        }
    }


    @Override
    public TypeApartment convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }

        return Stream.of(TypeApartment.values())
                .filter(c -> c.getValue().equals(dbData))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);

    }
}

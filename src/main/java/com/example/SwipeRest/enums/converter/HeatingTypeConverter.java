package com.example.SwipeRest.enums.converter;

import com.example.SwipeRest.enums.HeatingType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class HeatingTypeConverter implements AttributeConverter<HeatingType, String> {

    @Override
    public String convertToDatabaseColumn(HeatingType attribute) {
        if(attribute == null){
            return null;
        }
        else {
            return attribute.getValue();
        }
    }

    @Override
    public HeatingType convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }

        return Stream.of(HeatingType.values())
                .filter(c -> c.getValue().equals(dbData))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);

    }
}

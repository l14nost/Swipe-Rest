package com.example.SwipeRest.enums.converter;

import com.example.SwipeRest.enums.BalconyType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class BalconyTypeConverter implements AttributeConverter<BalconyType, String> {

    @Override
    public String convertToDatabaseColumn(BalconyType attribute) {
        if(attribute == null){
            return null;
        }
        else {
            return attribute.getValue();
        }
    }

    @Override
    public BalconyType convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }

        return Stream.of(BalconyType.values())
                .filter(c -> c.getValue().equals(dbData))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);

    }
}

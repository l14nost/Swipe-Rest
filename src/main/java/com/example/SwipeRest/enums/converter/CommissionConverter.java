package com.example.SwipeRest.enums.converter;

import com.example.SwipeRest.enums.Commission;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class CommissionConverter implements AttributeConverter<Commission, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Commission attribute) {
        if(attribute == null){
            return null;
        }
        else {
            return attribute.getValue();
        }
    }

    @Override
    public Commission convertToEntityAttribute(Integer dbData) {
        if (dbData == null) {
            return null;
        }

        return Stream.of(Commission.values())
                .filter(c -> c.getValue() == dbData)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);

    }
}

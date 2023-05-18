package com.example.SwipeRest.enums.converter;

import com.example.SwipeRest.enums.Calculation;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class CalculationConverter implements AttributeConverter<Calculation, String> {

    @Override
    public String convertToDatabaseColumn(Calculation attribute) {
        if(attribute == null){
            return null;
        }
        else {
            return attribute.getValue();
        }
    }

    @Override
    public Calculation convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }

        return Stream.of(Calculation.values())
                .filter(c -> c.getValue().equals(dbData))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);

    }
}

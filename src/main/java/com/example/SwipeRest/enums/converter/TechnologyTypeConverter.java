package com.example.SwipeRest.enums.converter;

import com.example.SwipeRest.enums.TechnologyType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class TechnologyTypeConverter implements AttributeConverter<TechnologyType, String> {

    @Override
    public String convertToDatabaseColumn(TechnologyType attribute) {
        if(attribute == null){
            return null;
        }
        else {
            return attribute.getValue();
        }
    }

    @Override
    public TechnologyType convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }

        return Stream.of(TechnologyType.values())
                .filter(c -> c.getValue().equals(dbData))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);

    }
}

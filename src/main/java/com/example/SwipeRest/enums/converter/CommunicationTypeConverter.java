package com.example.SwipeRest.enums.converter;

import com.example.SwipeRest.enums.CommunicationType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class CommunicationTypeConverter implements AttributeConverter<CommunicationType, String> {

    @Override
    public String convertToDatabaseColumn(CommunicationType attribute) {
        if(attribute == null){
            return null;
        }
        else {
            return attribute.getValue();
        }
    }

    @Override
    public CommunicationType convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }

        return Stream.of(CommunicationType.values())
                .filter(c -> c.getValue().equals(dbData))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);

    }
}

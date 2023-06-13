package com.example.SwipeRest.enums.converter;

import com.example.SwipeRest.enums.CommunalType;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class CommunalTypeConverter implements AttributeConverter<CommunalType, String> {

    @Override
    public String convertToDatabaseColumn(CommunalType attribute) {
        if(attribute == null){
            return null;
        }
        else {
            return attribute.getValue();
        }
    }

    @Override
    public CommunalType convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }

        return Stream.of(CommunalType.values())
                .filter(c -> c.getValue().equals(dbData))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);

    }
}

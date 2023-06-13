package com.example.SwipeRest.enums.converter;

import com.example.SwipeRest.enums.TerritoryType;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class TerritoryConverter implements AttributeConverter<TerritoryType, String> {

    @Override
    public String convertToDatabaseColumn(TerritoryType attribute) {
        if(attribute == null){
            return null;
        }
        else {
            return attribute.getValue();
        }
    }

    @Override
    public TerritoryType convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }

        return Stream.of(TerritoryType.values())
                .filter(c -> c.getValue().equals(dbData))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);

    }
}

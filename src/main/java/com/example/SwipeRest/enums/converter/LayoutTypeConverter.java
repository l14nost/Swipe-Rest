package com.example.SwipeRest.enums.converter;

import com.example.SwipeRest.enums.LayoutType;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class LayoutTypeConverter implements AttributeConverter<LayoutType, String> {

    @Override
    public String convertToDatabaseColumn(LayoutType attribute) {
        if(attribute == null){
            return null;
        }
        else {
            return attribute.getValue();
        }
    }

    @Override
    public LayoutType convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }

        return Stream.of(LayoutType.values())
                .filter(c -> c.getValue().equals(dbData))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);

    }
}

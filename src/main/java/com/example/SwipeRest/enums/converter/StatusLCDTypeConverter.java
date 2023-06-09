package com.example.SwipeRest.enums.converter;

import com.example.SwipeRest.enums.StatusLCDType;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class StatusLCDTypeConverter implements AttributeConverter<StatusLCDType, String> {

    @Override
    public String convertToDatabaseColumn(StatusLCDType attribute) {
        if(attribute == null){
            return null;
        }
        else {
            return attribute.getValue();
        }
    }

    @Override
    public StatusLCDType convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }

        return Stream.of(StatusLCDType.values())
                .filter(c -> c.getValue().equals(dbData))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);

    }
}

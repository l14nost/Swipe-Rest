package com.example.SwipeRest.enums.converter;

import com.example.SwipeRest.enums.Advantage;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class AdvantageConverter implements AttributeConverter<Advantage, String> {
    @Override
    public String convertToDatabaseColumn(Advantage attribute) {
        return attribute.toString();
    }

    @Override
    public Advantage convertToEntityAttribute(String dbData) {
        return Advantage.valueOf(dbData);
    }
}

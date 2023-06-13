package com.example.SwipeRest.enums.converter;

import com.example.SwipeRest.enums.CountRoom;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class CountRoomConverter implements AttributeConverter<CountRoom, Integer> {

    @Override
    public Integer convertToDatabaseColumn(CountRoom attribute) {
        if(attribute == null){
            return null;
        }
        else {
            return attribute.getValue();
        }
    }

    @Override
    public CountRoom convertToEntityAttribute(Integer dbData) {
        if (dbData == null) {
            return null;
        }

        return Stream.of(CountRoom.values())
                .filter(c -> c.getValue() == dbData)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);

    }
}

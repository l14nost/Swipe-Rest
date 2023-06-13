package com.example.SwipeRest.enums.converter;

import com.example.SwipeRest.enums.State;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class StateConverter implements AttributeConverter<State, String> {

    @Override
    public String convertToDatabaseColumn(State attribute) {
        if(attribute == null){
            return null;
        }
        else {
            return attribute.getValue();
        }
    }

    @Override
    public State convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }

        return Stream.of(State.values())
                .filter(c -> c.getValue().equals(dbData))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);

    }
}

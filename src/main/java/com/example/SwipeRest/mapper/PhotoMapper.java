package com.example.SwipeRest.mapper;

import com.example.SwipeRest.dto.AgentDTO;
import com.example.SwipeRest.dto.PhotoDTO;
import com.example.SwipeRest.entity.Agent;
import com.example.SwipeRest.entity.Photo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Function;

public class PhotoMapper  {
    public static Photo toEntity(PhotoDTO photoDTO){
        return Photo.builder()
                .idPhotos(photoDTO.getId())
                .fileName(photoDTO.getFileName())
                .build();
    }

    public static PhotoDTO apply(Photo photo) {
        return PhotoDTO.builder()
                .id(photo.getIdPhotos())
                .fileName(photo.getFileName())
                .build();

    }
}

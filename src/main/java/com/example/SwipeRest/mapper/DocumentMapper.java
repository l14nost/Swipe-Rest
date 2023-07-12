package com.example.SwipeRest.mapper;

import com.example.SwipeRest.dto.DocumentDTO;
import com.example.SwipeRest.dto.PhotoDTO;
import com.example.SwipeRest.entity.Documents;
import com.example.SwipeRest.entity.Photo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Function;


public class DocumentMapper{
    public static Documents toEntity(DocumentDTO documentDTO){
        return Documents.builder()
                .idDocuments(documentDTO.getId())
                .name(documentDTO.getName())
                .fileName(documentDTO.getFileName())
                .build();
    }

    public static DocumentDTO apply(Documents documents) {
        return DocumentDTO.builder()
                .id(documents.getIdDocuments())
                .fileName(documents.getFileName())
                .name(documents.getName())
                .build();

    }
}

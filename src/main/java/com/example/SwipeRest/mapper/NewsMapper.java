package com.example.SwipeRest.mapper;

import com.example.SwipeRest.dto.DocumentDTO;
import com.example.SwipeRest.dto.NewsDto;
import com.example.SwipeRest.entity.Documents;
import com.example.SwipeRest.entity.News;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Function;


public class NewsMapper{
    public static News toEntity(NewsDto newsDto){
        return News.builder()
                .idNews(newsDto.getIdNews())
                .date(newsDto.getDate())
                .description(newsDto.getDescription())
                .title(newsDto.getTitle())
                .build();
    }

    public static NewsDto apply(News news) {
        return NewsDto.builder()
                .idNews(news.getIdNews())
                .description(news.getDescription())
                .date(news.getDate())
                .title(news.getTitle())
                .build();

    }
}

package com.example.SwipeRest.service.impl;

import com.example.SwipeRest.dto.NewsDto;
import com.example.SwipeRest.entity.LCD;
import com.example.SwipeRest.entity.News;
import com.example.SwipeRest.repository.NewsRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class NewsServiceImplTest {
    @Mock
    private NewsRepo newsRepo;
    @Mock
    private LCDServiceImpl lcdService;
    @InjectMocks
    private NewsServiceImpl newsService;

    @Test
    void findAll() {
        List<News> newsList = Arrays.asList(
                News.builder().date(LocalDate.of(2023,5,28)).build(),
                News.builder().date(LocalDate.of(2023,5,20)).build(),
                News.builder().date(LocalDate.of(2023,10,13)).build(),
                News.builder().date(LocalDate.of(2023,5,28)).build(),
                News.builder().date(LocalDate.of(2023,1,19)).build(),
                News.builder().date(LocalDate.of(2023,1,19)).build()

        );
        when(newsRepo.findAll()).thenReturn(newsList);
        List<News> newsList1 = newsService.findAll();
        assertEquals(6,newsList1.size());
    }

    @Test
    void findById() {
        News news = News.builder()
                .idNews(5)
                .title("Title")
                .description("Description")
                .date(LocalDate.of(2023,5,28))
                .lcd(LCD.builder().idLcd(5).build())
                .build();
        when(newsRepo.findById(5)).thenReturn(Optional.of(news));
        News news1 = newsService.findById(5);
        assertEquals("Title", news1.getTitle());
        assertEquals("Description", news1.getDescription());
        assertEquals(LocalDate.of(2023,5,28), news1.getDate());
    }
    @Test
    void findByIdNull() {
        News news1 = newsService.findById(5);
        assertNull(news1);
    }

    @Test
    void findByIdDTO() {
        News news = News.builder()
                .idNews(5)
                .title("Title")
                .description("Description")
                .date(LocalDate.of(2023,5,28))
                .lcd(LCD.builder().idLcd(5).build())
                .build();
        when(newsRepo.findById(5)).thenReturn(Optional.of(news));
        NewsDto news1 = newsService.findByIdDTO(5);
        assertEquals("Title", news1.getTitle());
        assertEquals("Description", news1.getDescription());
        assertEquals(LocalDate.of(2023,5,28), news1.getDate());
    }
    @Test
    void findByIdDtoNull() {
        NewsDto news1 = newsService.findByIdDTO(5);
        assertNull(news1);
    }


    @Test
    void saveEntity() {
        News news = News.builder()
                .title("Title")
                .description("Description")
                .date(LocalDate.of(2023,5,28))
                .build();
        News newsFake = News.builder()
                .title("Title1")
                .description("Description")
                .date(LocalDate.of(2023,5,28))
                .build();
        newsService.saveEntity(news);
        verify(newsRepo).save(news);
    }

    @Test
    void deleteById() {
        newsService.deleteById(1);
        verify(newsRepo).deleteById(1);
    }

    @Test
    void updateEntity() {
        News news = News.builder()
                .idNews(1)
                .title("Title")
                .description("Description")
                .date(LocalDate.of(2023,5,28))
                .lcd(LCD.builder().idLcd(1).build())
                .build();
        when(newsRepo.findById(1)).thenReturn(Optional.of(news));
        News newsUpdate = News.builder()
                .title("Title1")
                .description("D")
                .date(LocalDate.of(2023,6,28))
                .lcd(LCD.builder().idLcd(1).build())
                .build();
        newsService.updateEntity(newsUpdate,1);
        News newsSave = News.builder()
                .idNews(1)
                .title("Title1")
                .description("D")
                .date(LocalDate.of(2023,6,28))
                .lcd(LCD.builder().idLcd(1).build())
                .build();
        verify(newsRepo).saveAndFlush(newsSave);
    }

//    @Test
//    void updateDTO() {
//        News news = News.builder()
//                .idNews(1)
//                .title("Title")
//                .description("Description")
//                .date(LocalDate.of(2023,5,28))
//                .lcd(LCD.builder().idLcd(1).build())
//                .build();
//        when(newsRepo.findById(1)).thenReturn(Optional.of(news));
//        NewsDTO newsUpdate = NewsDTO.builder()
//                .title("Title1")
//                .description("D")
//                .idLcd(1)
//                .date(LocalDate.of(2023,6,28))
//                .build();
//        newsService.updateDTO(newsUpdate,1);
//        News newsSave = News.builder()
//                .idNews(1)
//                .title("Title1")
//                .description("D")
//                .date(LocalDate.of(2023,6,28))
//                .lcd(LCD.builder().idLcd(1).build())
//                .build();
//        verify(newsRepo).saveAndFlush(newsSave);
//    }
}
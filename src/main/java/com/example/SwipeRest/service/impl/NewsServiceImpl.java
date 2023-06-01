package com.example.SwipeRest.service.impl;

import com.example.SwipeRest.dto.NewsDto;
import com.example.SwipeRest.entity.News;
import com.example.SwipeRest.mapper.NewsMapper;
import com.example.SwipeRest.repository.NewsRepo;
import com.example.SwipeRest.service.NewsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Log4j2
@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {
    private final NewsRepo newsRepo;



    @Override
    public List<News> findAll() {
        log.info("All news");
        return newsRepo.findAll();
    }
    public NewsDto findByIdDTO(int id) {
        Optional<News> news = newsRepo.findById(id);
        if(news.isPresent()){
            log.info("News find "+id);
            return NewsMapper.apply(news.get());
        }
        else {
            log.info("News not find "+id);
            return null;
        }
    }

    @Override
    public News findById(int id) {
        Optional<News> news = newsRepo.findById(id);
        if(news.isPresent()){
            log.info("News find "+id);
            return  news.get();
        }
        else {
            log.info("News not find "+id);
            return null;
        }
    }

    @Override
    public void saveEntity(News news) {
        newsRepo.save(news);
        log.info("News save");
    }

    @Override
    public void deleteById(int id) {
        newsRepo.deleteById(id);
        log.info("News delete "+id);
    }

    @Override
    public void updateEntity(News news, int id) {
        Optional<News> newsOptional = newsRepo.findById(id);
        if(newsOptional.isPresent()){
            News updateNews = newsOptional.get();
            if(news.getDate()!=null){
                updateNews.setDate(news.getDate());
            }
            if(news.getDescription()!=null){
                updateNews.setDescription(news.getDescription());
            }
            if(news.getTitle()!=null){
                updateNews.setTitle(news.getTitle());
            }
            newsRepo.saveAndFlush(updateNews);
            log.info("News update  "+id);

        }

    }
}

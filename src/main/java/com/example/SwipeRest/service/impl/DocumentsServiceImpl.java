package com.example.SwipeRest.service.impl;

import com.example.SwipeRest.controller.ApartmentController;
import com.example.SwipeRest.entity.Documents;
import com.example.SwipeRest.repository.DocumentsRepo;
import com.example.SwipeRest.service.DocumentsService;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocumentsServiceImpl implements DocumentsService {
    private Logger log = LoggerFactory.getLogger(DocumentsServiceImpl.class);
    private final DocumentsRepo documentsRepo;

    public DocumentsServiceImpl(DocumentsRepo documentsRepo) {
        this.documentsRepo = documentsRepo;
    }

    @Override
    public List<Documents> findAll() {
        log.info("All Documents");
        return documentsRepo.findAll();
    }

    @Override
    public Documents findById(int id) {
        Optional<Documents> documents = documentsRepo.findById(id);
        if(documents.isPresent()){
            log.info("Document find:"+id);
            return documents.get();
        }
        else {
            log.info("Document not find:"+id);
            return null;
        }
    }

    @Override
    public void saveEntity(Documents documents) {
        documentsRepo.save(documents);
        log.info("Document save");
    }

    @Override
    public void deleteById(int id) {
        documentsRepo.deleteById(id);
        log.info("Document delete "+id);
    }

    @Override
    public void updateEntity(Documents documents, int id) {
        Optional<Documents> documentsOptional = documentsRepo.findById(id);
        if(documentsOptional.isPresent()){
            Documents documentsUpdate = documentsOptional.get();
            if(documents.getName()!=null){
                documentsUpdate.setName(documents.getName());
            }
            if(documents.getFileName()!=null){
                documentsUpdate.setFileName(documents.getFileName());
            }
            documentsRepo.saveAndFlush(documentsUpdate);
            log.info("Document update "+id);
        }
    }
}

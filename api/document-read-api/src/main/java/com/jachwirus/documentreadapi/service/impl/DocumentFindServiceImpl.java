package com.jachwirus.documentreadapi.service.impl;

import com.jachwirus.documentreadapi.exception.SortTargetNotSupportedException;
import com.jachwirus.documentreadapi.exception.DocumentNotFoundException;
import com.jachwirus.documentreadapi.model.Document;
import com.jachwirus.documentreadapi.repository.DocumentRepository;

import com.jachwirus.documentreadapi.service.DocumentFindService;
import com.jachwirus.documentreadapi.util.DefaultValue;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DocumentFindServiceImpl implements DocumentFindService {
    private DocumentRepository documentRepository;

    public DocumentFindServiceImpl(DocumentRepository documentRepository){
        this.documentRepository = documentRepository;
    }

    @Override
    public List<Document> findDocumentsList(String category, String sortTarget, String page) {
        boolean isCategoryEntire = category.equals(DefaultValue.category);
        int pageNumber = Integer.parseInt(page);

        if(isCategoryEntire){
            return findAllDocumentBy(sortTarget, pageNumber);
        }else{
            return findByCategory(category, pageNumber);
        }
    }

    private List<Document> findAllDocumentBy(String sortTarget, int page) {
        Pageable pageable = PageRequest.of(page-1, DefaultValue.pageSize);
        List<Document> documentList = findAllDocumentBy(sortTarget, pageable);

        return documentList;
    }

    private List<Document> findAllDocumentBy(String sortTarget, Pageable pageable) {
        if(sortTarget.equals(DefaultValue.sortTarget)) {
            return documentRepository.findAllOrderByLatest(pageable);
        }else if(sortTarget.equals("popularity")) {
            return documentRepository.findAllOrderByPopularity(pageable);
        }else {
            throw new SortTargetNotSupportedException(sortTarget);
        }
    }

    private List<Document> findByCategory(String category, int page){
        Pageable pageable = PageRequest.of(page-1, DefaultValue.pageSize);
        List<Document> documentList = documentRepository.findByCategory(category, pageable);

        return documentList;
    }

    @Override
    public Document findDocumentById(Long id) {
        Document document = documentRepository.findById(id)
                .orElseThrow(() -> new DocumentNotFoundException(id));

        return document;
    }
}

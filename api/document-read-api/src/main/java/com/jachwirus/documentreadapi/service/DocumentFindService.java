package com.jachwirus.documentreadapi.service;

import com.jachwirus.documentreadapi.model.Document;

import java.util.List;

public interface DocumentFindService {
    List<Document> findDocumentsList(String category, String sortTarget, String page);
    Document findDocumentById(Long id);
}

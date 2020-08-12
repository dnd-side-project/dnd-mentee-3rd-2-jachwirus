package com.jachwirus.documentreadapi.service;

import com.jachwirus.documentreadapi.dto.Document;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

public interface DocumentService {
    CollectionModel<EntityModel<Document>> findAllDocuments();
    EntityModel<Document> findDocumentById(Long id);
}

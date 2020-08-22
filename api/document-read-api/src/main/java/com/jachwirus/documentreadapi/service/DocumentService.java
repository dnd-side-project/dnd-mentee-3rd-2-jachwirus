package com.jachwirus.documentreadapi.service;

import com.jachwirus.documentreadapi.dto.model.DocumentDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

public interface DocumentService {
    CollectionModel<EntityModel<DocumentDto>> findDocumentsList(String category);
    EntityModel<DocumentDto> findDocumentById(Long id);
    CollectionModel<EntityModel<DocumentDto>> getHotChartDocumentList();
}

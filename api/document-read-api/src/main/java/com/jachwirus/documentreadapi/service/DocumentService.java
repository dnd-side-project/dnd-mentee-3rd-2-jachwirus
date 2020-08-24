package com.jachwirus.documentreadapi.service;

import com.jachwirus.documentreadapi.dto.model.DocumentDetailDto;
import com.jachwirus.documentreadapi.dto.model.DocumentInfoDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

public interface DocumentService {
    CollectionModel<EntityModel<DocumentInfoDto>> findDocumentsList(String category);
    EntityModel<DocumentDetailDto> findDocumentById(Long id);
    CollectionModel<EntityModel<DocumentInfoDto>> getHotChartDocumentList();
}

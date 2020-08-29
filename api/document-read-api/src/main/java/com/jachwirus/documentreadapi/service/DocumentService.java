package com.jachwirus.documentreadapi.service;

import com.jachwirus.documentreadapi.dto.model.DocumentDetailDto;
import com.jachwirus.documentreadapi.dto.model.DocumentInfoDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

import java.util.Optional;

public interface DocumentService {
    CollectionModel<EntityModel<DocumentInfoDto>> findDocumentsList(Optional<String> category, Optional<String> sortTarget);
    EntityModel<DocumentDetailDto> findDocumentById(Long id);
}

package com.jachwirus.documentreadapi.service;

import com.jachwirus.documentreadapi.dto.model.DocumentDetailDto;
import com.jachwirus.documentreadapi.dto.model.DocumentInfoDto;
import com.jachwirus.documentreadapi.model.Document;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

import java.util.List;

public interface EntityModelService {
    CollectionModel<EntityModel<DocumentInfoDto>> toCollectionModel(List<Document> list, String category, String sortTarget, String page);
    EntityModel<DocumentDetailDto> toEntityModel(Document document, String contents);
}

package com.jachwirus.documentreadapi.service.impl;

import com.jachwirus.documentreadapi.controller.DocumentController;
import com.jachwirus.documentreadapi.dto.assembler.DocumentDetailAssembler;
import com.jachwirus.documentreadapi.dto.assembler.DocumentInfoAssembler;
import com.jachwirus.documentreadapi.dto.mapper.DocumentDetailDtoMapper;
import com.jachwirus.documentreadapi.dto.mapper.DocumentInfoDtoMapper;
import com.jachwirus.documentreadapi.dto.model.DocumentDetailDto;
import com.jachwirus.documentreadapi.dto.model.DocumentInfoDto;
import com.jachwirus.documentreadapi.model.Document;
import com.jachwirus.documentreadapi.service.EntityModelService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class EntityModelServiceImpl implements EntityModelService {
    @Override
    public CollectionModel<EntityModel<DocumentInfoDto>> toCollectionModel(
            List<Document> list,
            String category,
            String sortTarget,
            String page
    ) {
        List<EntityModel<DocumentInfoDto>> documents = toEntityModelList(list);
        Link selfLink = linkTo(methodOn(DocumentController.class).findList(category, sortTarget, page)).withSelfRel();
        CollectionModel<EntityModel<DocumentInfoDto>> collectionModel = CollectionModel.of(documents, selfLink);

        return collectionModel;
    }

    private List<EntityModel<DocumentInfoDto>> toEntityModelList(List<Document> documentList) {
        List<EntityModel<DocumentInfoDto>> entityModelList = documentList.stream()
                .map(DocumentInfoDtoMapper::toDocumentInfoDto)
                .map(dto -> new DocumentInfoAssembler().toModel(dto))
                .collect(Collectors.toList());

        return entityModelList;
    }

    @Override
    public EntityModel<DocumentDetailDto> toEntityModel(Document document, String contents) {
        DocumentDetailDto dto = DocumentDetailDtoMapper.toDocumentDetailDto(document, contents);
        EntityModel<DocumentDetailDto> model = new DocumentDetailAssembler().toModel(dto);

        return model;
    }
}

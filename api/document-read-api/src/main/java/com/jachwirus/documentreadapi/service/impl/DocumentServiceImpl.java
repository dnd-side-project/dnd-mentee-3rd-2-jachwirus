package com.jachwirus.documentreadapi.service.impl;

import com.jachwirus.documentreadapi.controller.DocumentController;
import com.jachwirus.documentreadapi.dto.assembler.*;
import com.jachwirus.documentreadapi.dto.mapper.*;
import com.jachwirus.documentreadapi.dto.model.*;
import com.jachwirus.documentreadapi.exception.SortTargetNotSupportedException;
import com.jachwirus.documentreadapi.exception.DocumentNotFoundException;
import com.jachwirus.documentreadapi.model.Comment;
import com.jachwirus.documentreadapi.model.Document;
import com.jachwirus.documentreadapi.repository.DocumentRepository;

import com.jachwirus.documentreadapi.service.DocumentService;
import com.jachwirus.documentreadapi.util.DefaultValue;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class DocumentServiceImpl implements DocumentService {
    private DocumentRepository documentRepository;

    public DocumentServiceImpl(DocumentRepository documentRepository){
        this.documentRepository = documentRepository;
    }

    @Override
    public CollectionModel<EntityModel<DocumentInfoDto>> findDocumentsList(
            String category,
            String sortTarget,
            String page
    ) {
        boolean isCategoryEntire = category.equals(DefaultValue.category);
        int pageNumber = Integer.parseInt(page);

        if(isCategoryEntire){
            return findAllDocumentSortBy(sortTarget, pageNumber);
        }else{
            return findByCategory(category, pageNumber);
        }
    }

    private CollectionModel<EntityModel<DocumentInfoDto>> findAllDocumentSortBy(
            String sortTarget,
            int page
    ) {
        Pageable pageable = PageRequest.of(page-1, DefaultValue.pageSize);
        List<Document> documentList = findDocumentListOrderBy(sortTarget, pageable);
        CollectionModel<EntityModel<DocumentInfoDto>> collectionModel = toCollectionModel(documentList, DefaultValue.category);

        return collectionModel;
    }

    private List<Document> findDocumentListOrderBy(String sortTarget, Pageable pageable) {
        if(sortTarget.equals(DefaultValue.sortTarget)) {
            return documentRepository.findAllOrderByLatest(pageable);
        }else if(sortTarget.equals("popularity")) {
            return documentRepository.findAllOrderByPopularity(pageable);
        }else {
            throw new SortTargetNotSupportedException(sortTarget);
        }
    }

    private CollectionModel<EntityModel<DocumentInfoDto>> findByCategory(
            String category,
            int page
    ){
        Pageable pageable = PageRequest.of(page-1, DefaultValue.pageSize);
        List<Document> documentList = documentRepository.findByCategory(category, pageable);
        CollectionModel<EntityModel<DocumentInfoDto>> collectionModel = toCollectionModel(documentList, category);

        return collectionModel;
    }

    private  CollectionModel<EntityModel<DocumentInfoDto>> toCollectionModel(
            List<Document> documentList,
            String selfLinkPath
    ) {
        String sortInfo = DefaultValue.sortTarget;
        String page = DefaultValue.page;

        List<EntityModel<DocumentInfoDto>> documents = toEntityModelList(documentList);
        Link selfLink = linkTo(methodOn(DocumentController.class).findList(selfLinkPath, sortInfo, page)).withSelfRel();

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
    public Document findDocumentById(Long id) {
        Document document = documentRepository.findById(id).orElseThrow(() -> new DocumentNotFoundException(id));

        return document;
    }

    @Override
    public EntityModel<DocumentDetailDto> getDocumentDetailModel(Document document, String contents) {
        DocumentDetailDto dto = DocumentDetailDtoMapper.toDocumentDetailDto(document, contents);
        EntityModel<DocumentDetailDto> model = new DocumentDetailAssembler().toModel(dto);

        return model;
    }
}

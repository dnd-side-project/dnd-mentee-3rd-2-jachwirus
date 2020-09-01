package com.jachwirus.documentreadapi.service.impl;

import com.jachwirus.documentreadapi.controller.DocumentController;
import com.jachwirus.documentreadapi.dto.assembler.DocumentDetailAssembler;
import com.jachwirus.documentreadapi.dto.mapper.DocumentDetailDtoMapper;
import com.jachwirus.documentreadapi.dto.mapper.DocumentInfoDtoMapper;
import com.jachwirus.documentreadapi.dto.model.DocumentDetailDto;
import com.jachwirus.documentreadapi.dto.model.DocumentInfoDto;
import com.jachwirus.documentreadapi.exception.SortTargetNotSupportedException;
import com.jachwirus.documentreadapi.model.Document;
import com.jachwirus.documentreadapi.exception.DocumentNotFoundException;
import com.jachwirus.documentreadapi.dto.assembler.DocumentInfoAssembler;
import com.jachwirus.documentreadapi.repository.DocumentHashTagRepository;
import com.jachwirus.documentreadapi.repository.DocumentRepository;

import com.jachwirus.documentreadapi.repository.HotChartRepository;
import com.jachwirus.documentreadapi.service.DocumentService;
import com.jachwirus.documentreadapi.util.DefaultValue;
import org.springframework.data.domain.Sort;
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
    private DocumentHashTagRepository documentHashTagRepository;
    private HotChartRepository hotChartRepository;
    private DocumentInfoAssembler documentInfoAssembler;
    private DocumentDetailAssembler documentDetailAssembler;


    public DocumentServiceImpl(
            DocumentRepository documentRepository,
            DocumentHashTagRepository documentHashTagRepository,
            HotChartRepository hotChartRepository,
            DocumentInfoAssembler documentInfoAssembler,
            DocumentDetailAssembler documentDetailAssembler
    ){
        this.documentRepository = documentRepository;
        this.documentHashTagRepository = documentHashTagRepository;
        this.hotChartRepository = hotChartRepository;
        this.documentInfoAssembler = documentInfoAssembler;
        this.documentDetailAssembler = documentDetailAssembler;
    }

    @Override
    public CollectionModel<EntityModel<DocumentInfoDto>> findDocumentsList(
            Optional<String> inputCategory,
            Optional<String> inputSortTarget
    ) {
        String category = getString(inputCategory, DefaultValue.category);
        String sortTarget = getString(inputSortTarget, DefaultValue.sortTarget);

        boolean isCategoryEntire = category.equals(DefaultValue.category);

        if(isCategoryEntire){
            return findAllDocumentSortBy(sortTarget);
        }else{
            return findByCategory(category);
        }
    }

    private String getString(Optional<String> str, String defaultValue) {
        final String empty = "";
        String value =  Optional.ofNullable(str).get().orElse(empty);
        if(value.equals(empty)) {
            return defaultValue;
        }else{
            return value;
        }
    }

    private CollectionModel<EntityModel<DocumentInfoDto>> findAllDocumentSortBy(String sortTarget) {
        Sort order = getSortTargetWithCheckValidation(sortTarget);

        List<Document> documentList = getDocumentListOrderBy(order);
        CollectionModel<EntityModel<DocumentInfoDto>> collectionModel = toCollectionModel(documentList, DefaultValue.category);

        return collectionModel;
    }

    private List<Document> getDocumentListOrderBy(Sort order) {
        return documentRepository.findAllWithLatestVersionAndAndComments(order);
    }

    private Sort getSortTargetWithCheckValidation(String sortTarget) {
        final String latest = DefaultValue.sortTarget;
        final String popularity = "popularity";

        boolean isSortTargetLatest = sortTarget.equals(latest);
        boolean isSortTargetPopularity = sortTarget.equals(popularity);
        boolean isSortTypeSupportable = isSortTargetLatest || isSortTargetPopularity;

        if( isSortTypeSupportable ) {
            Sort order = null;
            if( isSortTargetLatest ) {
                sortTarget = "id";
                order = Sort.by(Sort.Direction.DESC, sortTarget);
            } else if ( isSortTargetPopularity ) {
                sortTarget = "viewCount";
                order = Sort.by(Sort.Direction.DESC, sortTarget)
                        .and(Sort.by(Sort.Direction.DESC, "id"));
            }
            return order;
        }

        throw new SortTargetNotSupportedException(sortTarget);
    }

    private CollectionModel<EntityModel<DocumentInfoDto>> findByCategory(String category){
        List<Document> documentList = documentRepository.findByCategory(category);
        CollectionModel<EntityModel<DocumentInfoDto>> collectionModel = toCollectionModel(documentList, category);
        return collectionModel;
    }

    private  CollectionModel<EntityModel<DocumentInfoDto>> toCollectionModel(
            List<Document> documentList,
            String selfLinkInfo
    ) {
        List<EntityModel<DocumentInfoDto>> documents = toEntityModelList(documentList);
        Link selfLink = linkTo(methodOn(DocumentController.class).findList(Optional.of(selfLinkInfo), Optional.of(DefaultValue.sortTarget))).withSelfRel();
        CollectionModel<EntityModel<DocumentInfoDto>> collectionModel = CollectionModel.of(documents, selfLink);

        return collectionModel;
    }

    private List<EntityModel<DocumentInfoDto>> toEntityModelList(List<Document> documentList) {
        List<DocumentInfoDto> documentListElementDtoInfo = documentList.stream()
                .map(DocumentInfoDtoMapper::toDocumentInfoDto).collect(Collectors.toList());
        List<EntityModel<DocumentInfoDto>> result = documentListElementDtoInfo.stream()
                .map(documentInfoAssembler::toModel).collect(Collectors.toList());

        return result;
    }

    @Override
    public Document findDocumentById(Long id) {
        Document document = documentRepository.findById(id)
                .orElseThrow(() -> new DocumentNotFoundException(id));

        return document;
    }

    @Override
    public EntityModel<DocumentDetailDto> getDocumentDetailModel(Document document, String contents) {
        DocumentDetailDto dto = DocumentDetailDtoMapper.toDocumentDetailDto(document, contents);
        EntityModel<DocumentDetailDto> model = new DocumentDetailAssembler().toModel(dto);

        return model;
    }
}

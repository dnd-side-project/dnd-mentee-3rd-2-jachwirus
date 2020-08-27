package com.jachwirus.documentreadapi.service.impl;

import static com.jachwirus.documentreadapi.controller.DocumentController.ENTIRE_LIST;

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
import com.jachwirus.documentreadapi.model.DocumentHashTag;
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

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
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
    public CollectionModel<EntityModel<DocumentInfoDto>> findDocumentsList(String category, String sortTarget) {
        boolean isCategoryEmpty = category == null || category.equals("");
        boolean isCategoryEntire = isCategoryEmpty || category.equals(ENTIRE_LIST);

        if(isCategoryEntire){
            return findAllDocumentSortBy(sortTarget);
        }else{
            return findByCategory(category);
        }
    }

    private CollectionModel<EntityModel<DocumentInfoDto>> findAllDocumentSortBy(String sortTarget) {
        String parsedSortTarget = getSortTargetWithCheckValidation(sortTarget);
//        List<Document> documentList= documentRepository.findAllWithLatestVersion(Sort.by(sortTarget));
//        CollectionModel<EntityModel<DocumentInfoDto>> collectionModel = toCollectionModel(documentList, ENTIRE_LIST);

//        return collectionModel;
        if(parsedSortTarget.equals("id")) {
            return getRecentChartDocumentList();
        }else if(parsedSortTarget.equals("view_count")){
            return getHotChartDocumentList();
        }
        return null;
    }

    private String getSortTargetWithCheckValidation(String sortTarget) {
        final String latest = "latest";
        final String popularity = "popularity";

        boolean isSortTargetEmpty = sortTarget == null || sortTarget.equals("");
        boolean isSortTargetLatest = isSortTargetEmpty || sortTarget.equals(latest);
        boolean isSortTargetPopularity = sortTarget != null && sortTarget.equals(popularity);
        boolean isSortTypeSupportable = isSortTargetLatest || isSortTargetPopularity;

        if( isSortTypeSupportable ) {
            if( isSortTargetLatest ) {
                sortTarget = "id";
            } else if ( isSortTargetPopularity ) {
                sortTarget = "view_count";
            }
            return sortTarget;
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
        Link selfLink = linkTo(methodOn(DocumentController.class).findList(selfLinkInfo, DefaultValue.sortTarget)).withSelfRel();
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
    public EntityModel<DocumentDetailDto> findDocumentById(Long id) {
        Document document = documentRepository.findById(id)
                .orElseThrow(() -> new DocumentNotFoundException(id));
        DocumentDetailDto dto = DocumentDetailDtoMapper.toDocumentDetailDto(document);
        EntityModel<DocumentDetailDto> result = documentDetailAssembler.toModel(dto);

        return result;
    }

    private CollectionModel<EntityModel<DocumentInfoDto>> getHotChartDocumentList() {
        List<Document> list = hotChartRepository.findAll().stream()
                .map(e -> e.getDocument())
                .collect(Collectors.toList());
        Collections.sort(list, Comparator.comparingInt(Document::getViewCount)); // need to modify sort in query
        CollectionModel<EntityModel<DocumentInfoDto>> collectionModel = toCollectionModel(list, DefaultValue.category);
        return collectionModel;
    }


    private CollectionModel<EntityModel<DocumentInfoDto>> getRecentChartDocumentList() {
        List<Document> list = documentRepository.findAll();
        Collections.sort(list, Comparator.comparingLong(Document::getId).reversed());//need to do at query
        CollectionModel<EntityModel<DocumentInfoDto>> collectionModel = toCollectionModel(list, DefaultValue.category);
        return collectionModel;
    }
}

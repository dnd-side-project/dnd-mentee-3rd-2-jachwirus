package com.jachwirus.documentreadapi.service.impl;

import static com.jachwirus.documentreadapi.controller.DocumentController.ENTIRE_LIST;

import com.jachwirus.documentreadapi.controller.DocumentController;
import com.jachwirus.documentreadapi.dto.assembler.DocumentDetailAssembler;
import com.jachwirus.documentreadapi.dto.mapper.DocumentDetailDtoMapper;
import com.jachwirus.documentreadapi.dto.mapper.DocumentInfoDtoMapper;
import com.jachwirus.documentreadapi.dto.model.DocumentDetailDto;
import com.jachwirus.documentreadapi.dto.model.DocumentInfoDto;
import com.jachwirus.documentreadapi.model.Document;
import com.jachwirus.documentreadapi.exception.DocumentNotFoundException;
import com.jachwirus.documentreadapi.dto.assembler.DocumentInfoAssembler;
import com.jachwirus.documentreadapi.repository.DocumentRepository;

import com.jachwirus.documentreadapi.repository.HotChartRepository;
import com.jachwirus.documentreadapi.service.DocumentService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class DocumentServiceImpl implements DocumentService {
    private DocumentRepository documentRepository;
    private DocumentInfoAssembler documentInfoAssembler;
    private DocumentDetailAssembler documentDetailAssembler;
    private HotChartRepository hotChartRepository;

    public DocumentServiceImpl(
            DocumentRepository documentRepository,
            DocumentInfoAssembler documentInfoAssembler,
            DocumentDetailAssembler documentDetailAssembler,
            HotChartRepository hotChartRepository
    ){
        this.documentRepository = documentRepository;
        this.documentInfoAssembler = documentInfoAssembler;
        this.documentDetailAssembler = documentDetailAssembler;
        this.hotChartRepository = hotChartRepository;
    }

    @Override
    public CollectionModel<EntityModel<DocumentInfoDto>> findDocumentsList(String category) {
        if(category == null || category.equals("") || category.equals(ENTIRE_LIST)){
            return findAllDocument();
        }else{
            return findByCategory(category);
        }
    }

    private CollectionModel<EntityModel<DocumentInfoDto>> findAllDocument(){
        List<Document> documentList= documentRepository.findAll();
        CollectionModel<EntityModel<DocumentInfoDto>> collectionModel = toCollectionModel(documentList, ENTIRE_LIST);

        return collectionModel;
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
        Link selfLink = linkTo(methodOn(DocumentController.class).findList(selfLinkInfo)).withSelfRel();
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

    @Override
    public CollectionModel<EntityModel<DocumentInfoDto>> getHotChartDocumentList() {
        List<Document> list = hotChartRepository.findAll().stream()
                .map(e -> e.getDocument())
                .collect(Collectors.toList());
        Collections.sort(list, Comparator.comparingInt(Document::getViewCount)); // need to modify sort in query
        CollectionModel<EntityModel<DocumentInfoDto>> collectionModel = toCollectionModel(list, "/hot-chart");
        return collectionModel;
    }
}

package com.jachwirus.documentreadapi.service.impl;

import static com.jachwirus.documentreadapi.controller.DocumentController.ENTIRE_LIST;

import com.jachwirus.documentreadapi.controller.DocumentController;
import com.jachwirus.documentreadapi.dto.mapper.DocumentDtoMapper;
import com.jachwirus.documentreadapi.dto.model.DocumentDto;
import com.jachwirus.documentreadapi.model.Document;
import com.jachwirus.documentreadapi.exception.DocumentNotFoundException;
import com.jachwirus.documentreadapi.dto.assembler.DocumentModelAssembler;
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
    private DocumentModelAssembler model;
    private HotChartRepository hotChartRepository;

    public DocumentServiceImpl(
            DocumentRepository documentRepository,
            DocumentModelAssembler model,
            HotChartRepository hotChartRepository
    ){
        this.documentRepository = documentRepository;
        this.model = model;
        this.hotChartRepository = hotChartRepository;
    }

    @Override
    public CollectionModel<EntityModel<DocumentDto>> findDocumentsList(String category) {
        if(category == null || category.equals("") || category.equals(ENTIRE_LIST)){
            return findAllDocument();
        }else{
            return findByCategory(category);
        }
    }

    private CollectionModel<EntityModel<DocumentDto>> findAllDocument(){
        List<Document> documentList= documentRepository.findAll();
        CollectionModel<EntityModel<DocumentDto>> collectionModel = toCollectionModel(documentList, ENTIRE_LIST);

        return collectionModel;
    }

    private CollectionModel<EntityModel<DocumentDto>> findByCategory(String category){
        List<Document> documentList = documentRepository.findByCategory(category);
        CollectionModel<EntityModel<DocumentDto>> collectionModel = toCollectionModel(documentList, category);
        return collectionModel;
    }

    private  CollectionModel<EntityModel<DocumentDto>> toCollectionModel(
            List<Document> documentList,
            String selfLinkInfo
    ) {
        List<EntityModel<DocumentDto>> documents = toEntityModelList(documentList);
        Link selfLink = linkTo(methodOn(DocumentController.class).findList(selfLinkInfo)).withSelfRel();
        CollectionModel<EntityModel<DocumentDto>> collectionModel = CollectionModel.of(documents, selfLink);

        return collectionModel;
    }

    private List<EntityModel<DocumentDto>> toEntityModelList(List<Document> documentList) {
        List<DocumentDto> documentDtoList = documentList.stream()
                .map(DocumentDtoMapper::toDocumentDto).collect(Collectors.toList());
        List<EntityModel<DocumentDto>> result = documentDtoList.stream()
                .map(model::toModel).collect(Collectors.toList());

        return result;
    }

    @Override
    public EntityModel<DocumentDto> findDocumentById(Long id) {
        Document document = documentRepository.findById(id)
                .orElseThrow(() -> new DocumentNotFoundException(id));
        DocumentDto dto = DocumentDtoMapper.toDocumentDto(document);
        EntityModel<DocumentDto> result = model.toModel(dto);

        return result;
    }

    @Override
    public CollectionModel<EntityModel<DocumentDto>> getHotChartDocumentList() {
        List<Document> list = hotChartRepository.findAll().stream()
                .map(e -> e.getDocument())
                .collect(Collectors.toList());
        Collections.sort(list, Comparator.comparingInt(Document::getViewCount)); // need to modify sort in query
        CollectionModel<EntityModel<DocumentDto>> collectionModel = toCollectionModel(list, "/hot-chart");
        return collectionModel;
    }
}

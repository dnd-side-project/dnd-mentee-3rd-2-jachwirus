package com.jachwirus.documentreadapi.service;

import static com.jachwirus.documentreadapi.controller.DocumentController.ENTIRE_LIST;

import com.jachwirus.documentreadapi.controller.DocumentController;
import com.jachwirus.documentreadapi.dto.Document;
import com.jachwirus.documentreadapi.exception.DocumentNotFoundException;
import com.jachwirus.documentreadapi.model.DocumentModelAssembler;
import com.jachwirus.documentreadapi.repository.DocumentRepository;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class DocumentServiceImpl implements DocumentService {
    private DocumentRepository repository;
    private DocumentModelAssembler model;

    public DocumentServiceImpl(DocumentRepository repository, DocumentModelAssembler model){
        this.repository = repository;
        this.model = model;
    }

    @Override
    public CollectionModel<EntityModel<Document>> findDocumentsList(String category) {
        if(category == null || category.equals("") || category.equals(ENTIRE_LIST)){
            return findAllDocument();
        }else{
            return findByCategory(category);
        }
    }

    private CollectionModel<EntityModel<Document>> findAllDocument(){
        List<EntityModel<Document>> documents = repository.findAll().stream()
                .map(model::toModel).collect(Collectors.toList());

        Link selfLink = linkTo(methodOn(DocumentController.class).findList(ENTIRE_LIST)).withSelfRel();
        CollectionModel<EntityModel<Document>> collectionModel = CollectionModel.of(documents, selfLink);

        return collectionModel;
    }

    private CollectionModel<EntityModel<Document>> findByCategory(String category){
        List<EntityModel<Document>> documents = repository.findByCategory(category).stream()
                .map(model::toModel).collect(Collectors.toList());

        Link selfLink = linkTo(methodOn(DocumentController.class).findList(category)).withSelfRel();
        CollectionModel<EntityModel<Document>> collectionModel = CollectionModel.of(documents, selfLink);
        return collectionModel;
    }

    @Override
    public EntityModel<Document> findDocumentById(Long id) {
        Document document = repository.findById(id).orElseThrow(() -> new DocumentNotFoundException(id));
        EntityModel<Document> result = model.toModel(document);

        return result;
    }
}

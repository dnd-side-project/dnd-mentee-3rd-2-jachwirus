package com.jachwirus.documentreadapi.controller;

import com.jachwirus.documentreadapi.dto.Document;
import com.jachwirus.documentreadapi.service.DocumentService;
import com.jachwirus.documentreadapi.service.DocumentServiceImpl;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/documents")
public class DocumentController {
    private DocumentService documentService;
    public DocumentController(DocumentServiceImpl documentServiceImpl){
        this.documentService = documentServiceImpl;
    }

    @GetMapping(path={"", "/"})
    public CollectionModel<EntityModel<Document>> all(){
        CollectionModel<EntityModel<Document>> collection = documentService.findAllDocuments();
        return collection;
    }

    @GetMapping("/{id}")
    public EntityModel<Document> one(@PathVariable Long id){
        EntityModel<Document> model = documentService.findDocumentById(id);
        return model;
    }
}

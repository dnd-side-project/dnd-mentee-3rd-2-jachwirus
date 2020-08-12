package com.jachwirus.documentreadapi.controller;

import com.jachwirus.documentreadapi.dto.Document;
import com.jachwirus.documentreadapi.service.DocumentService;
import com.jachwirus.documentreadapi.service.DocumentServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/documents")
public class DocumentController {
    public static final String ENTIRE_LIST = "ENTIRE";

    private DocumentService documentService;
    public DocumentController(DocumentServiceImpl documentServiceImpl){
        this.documentService = documentServiceImpl;
    }

    @GetMapping("")
    @ApiOperation(value = "위키 데이터 리스트 조회")
    public CollectionModel<EntityModel<Document>> findList(
            @RequestParam(defaultValue = ENTIRE_LIST, value = "category", name="category", required = false)
            String category
    ){
        CollectionModel<EntityModel<Document>> collection = documentService.findDocumentsList(category);
        return collection;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "위키 문서 상세 조회")
    public EntityModel<Document> findOne(@PathVariable Long id){
        //need to be changed
        EntityModel<Document> model = documentService.findDocumentById(id);
        return model;
    }

}

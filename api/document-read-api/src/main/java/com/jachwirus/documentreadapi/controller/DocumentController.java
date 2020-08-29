package com.jachwirus.documentreadapi.controller;

import com.jachwirus.documentreadapi.dto.model.DocumentDetailDto;
import com.jachwirus.documentreadapi.dto.model.DocumentInfoDto;
import com.jachwirus.documentreadapi.service.DocumentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/documents")
public class DocumentController {
    private final DocumentService documentService;
    public DocumentController(DocumentService documentService){
        this.documentService = documentService;
    }

    @GetMapping("")
    @ApiOperation(value = "위키 데이터 리스트 조회")
    public CollectionModel<EntityModel<DocumentInfoDto>> findList(
            @RequestParam(value = "category", required = false)
            Optional<String> category,
            @RequestParam(value = "sortBy", required = false)
            Optional<String> sortTarget
    ){
        CollectionModel<EntityModel<DocumentInfoDto>> collection
                = documentService.findDocumentsList(category, sortTarget);
        return collection;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "위키 문서 상세 조회")
    public EntityModel<DocumentDetailDto> findOne(@PathVariable Long id){
        EntityModel<DocumentDetailDto> model = documentService.findDocumentById(id);
        return model;
    }
}

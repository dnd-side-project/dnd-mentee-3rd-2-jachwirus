package com.jachwirus.documentreadapi.controller;

import com.jachwirus.documentreadapi.dto.model.DocumentDetailDto;
import com.jachwirus.documentreadapi.dto.model.DocumentInfoDto;
import com.jachwirus.documentreadapi.model.Document;
import com.jachwirus.documentreadapi.service.DocumentService;
import com.jachwirus.documentreadapi.service.RestService;
import io.swagger.annotations.ApiOperation;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/documents")
public class DocumentController {
    private final DocumentService documentService;
    private final RestService restService;
    public DocumentController(
            DocumentService documentService,
            RestService restService
    ){
        this.documentService = documentService;
        this.restService = restService;
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
        Document document = documentService.findDocumentById(id);
        String dataURL = document.getLatestVersion().getDataUrl();
        String contents = restService.getPlainText(dataURL);
        EntityModel<DocumentDetailDto> model = documentService.getDocumentDetailModel(document, contents);

        return model;
    }
}

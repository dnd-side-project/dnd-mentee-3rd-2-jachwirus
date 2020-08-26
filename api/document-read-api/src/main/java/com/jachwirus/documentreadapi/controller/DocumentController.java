package com.jachwirus.documentreadapi.controller;

import com.jachwirus.documentreadapi.dto.model.DocumentDetailDto;
import com.jachwirus.documentreadapi.dto.model.DocumentInfoDto;
import com.jachwirus.documentreadapi.service.DocumentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/documents")
public class DocumentController {
    public static final String ENTIRE_LIST = "ENTIRE";

    private final DocumentService documentService;
    public DocumentController(DocumentService documentService){
        this.documentService = documentService;
    }

    @GetMapping("")
    @ApiOperation(value = "위키 데이터 리스트 조회")
    public CollectionModel<EntityModel<DocumentInfoDto>> findList(
            @RequestParam(value = "category", name="category", required = false)
            String category
    ){
        CollectionModel<EntityModel<DocumentInfoDto>> collection
                = documentService.findDocumentsList(category);
        return collection;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "위키 문서 상세 조회")
    public EntityModel<DocumentDetailDto> findOne(@PathVariable Long id){
        //need to be changed
        EntityModel<DocumentDetailDto> model = documentService.findDocumentById(id);
        return model;
    }

    @GetMapping("/hot-chart")
    @ApiOperation(value = "인기 게시물 조회")
    public CollectionModel<EntityModel<DocumentInfoDto>> getHotDocument(){
        CollectionModel<EntityModel<DocumentInfoDto>> collection
                = documentService.getHotChartDocumentList();
        return collection;
    }

    @GetMapping("/recent")
    @ApiOperation(value = "최신 업데이트 게시물 조회")
    public CollectionModel<EntityModel<DocumentInfoDto>> getRecentDocumentList() {
        CollectionModel<EntityModel<DocumentInfoDto>> collection
                = documentService.getRecentChartDocumentList();
        return collection;
    }
}

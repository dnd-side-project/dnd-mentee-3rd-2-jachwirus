package com.jachwirus.documentreadapi.controller;

import com.jachwirus.documentreadapi.dto.model.DocumentDetailDto;
import com.jachwirus.documentreadapi.dto.model.DocumentInfoDto;
import com.jachwirus.documentreadapi.model.Document;
import com.jachwirus.documentreadapi.service.DocumentFindService;
import com.jachwirus.documentreadapi.service.EntityModelService;
import com.jachwirus.documentreadapi.service.RestService;
import com.jachwirus.documentreadapi.util.DefaultValue;
import io.swagger.annotations.ApiOperation;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/documents")
public class DocumentController {
    private final DocumentFindService documentFindService;
    private final EntityModelService entityModelService;
    private final RestService restService;

    public DocumentController(
            DocumentFindService documentFindService,
            EntityModelService entityModelService,
            RestService restService
    ){
        this.documentFindService = documentFindService;
        this.entityModelService = entityModelService;
        this.restService = restService;
    }

    @GetMapping("")
    @ApiOperation(value = "위키 데이터 리스트 조회")
    public CollectionModel<EntityModel<DocumentInfoDto>> findList(
            @RequestParam(value = "category", required = false, defaultValue = DefaultValue.category)
            String category,
            @RequestParam(value = "sortBy", required = false, defaultValue = DefaultValue.sortTarget)
            String sortTarget,
            @RequestParam(value = "page", required = false, defaultValue = DefaultValue.page)
            String page
    ){
        List<Document> list = documentFindService.findDocumentsList(category, sortTarget, page);
        CollectionModel<EntityModel<DocumentInfoDto>> collection
                = entityModelService.toCollectionModel(list, category, sortTarget, page);

        return collection;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "위키 문서 상세 조회")
    public EntityModel<DocumentDetailDto> findOne(@PathVariable Long id){
        Document document = documentFindService.findDocumentById(id);
        String dataURL = document.getLatestVersion().getDataUrl();
        String contents = restService.getPlainText(dataURL);
        EntityModel<DocumentDetailDto> model = entityModelService.toEntityModel(document, contents);

        return model;
    }
}

package com.jachwirus.documentreadapi.dto.assembler;

import com.jachwirus.documentreadapi.controller.DocumentController;
import com.jachwirus.documentreadapi.dto.model.DocumentDetailDto;
import com.jachwirus.documentreadapi.util.DefaultValue;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class DocumentDetailAssembler implements
        RepresentationModelAssembler<DocumentDetailDto, EntityModel<DocumentDetailDto>> {
    @Override
    public EntityModel<DocumentDetailDto> toModel(DocumentDetailDto documentDetailDto) {
        long id = documentDetailDto.getId();
        String category = documentDetailDto.getCategory();
        String defaultCategory = DefaultValue.category;
        String defaultSortTarget = DefaultValue.sortTarget;
        String defaultPage = DefaultValue.page;

        EntityModel<DocumentDetailDto> model = EntityModel.of(documentDetailDto,
                linkTo(methodOn(DocumentController.class).findOne(id)).withSelfRel(),
                linkTo(methodOn(DocumentController.class).findList(category, defaultSortTarget, defaultPage)).withRel("category"),
                linkTo(methodOn(DocumentController.class).findList(defaultCategory, defaultSortTarget, defaultPage)).withRel("documents")
        );
        return model;
    }
}
package com.jachwirus.documentreadapi.dto.assembler;

import com.jachwirus.documentreadapi.controller.DocumentController;
import com.jachwirus.documentreadapi.dto.model.DocumentInfoDto;
import com.jachwirus.documentreadapi.util.DefaultValue;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class DocumentInfoAssembler implements
        RepresentationModelAssembler<DocumentInfoDto, EntityModel<DocumentInfoDto>> {
    @Override
    public EntityModel<DocumentInfoDto> toModel(DocumentInfoDto documentInfoDto) {
        long id = documentInfoDto.getId();
        String category = documentInfoDto.getCategory();
        String defaultSortTarget = DefaultValue.sortTarget;
        String defaultCategory = DefaultValue.category;
        String defaultPage = DefaultValue.page;

        EntityModel<DocumentInfoDto> model = EntityModel.of(documentInfoDto,
                linkTo(methodOn(DocumentController.class).findOne(id)).withSelfRel(),
                linkTo(methodOn(DocumentController.class).findList(category, defaultSortTarget, defaultPage)).withRel("category"),
                linkTo(methodOn(DocumentController.class).findList(defaultCategory, defaultSortTarget, defaultPage)).withRel("documents")
                );
        return model;
    }
}

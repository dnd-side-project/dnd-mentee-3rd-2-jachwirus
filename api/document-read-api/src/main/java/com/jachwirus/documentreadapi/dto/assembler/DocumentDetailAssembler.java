package com.jachwirus.documentreadapi.dto.assembler;

import com.jachwirus.documentreadapi.controller.DocumentController;
import com.jachwirus.documentreadapi.dto.model.DocumentDetailDto;
import com.jachwirus.documentreadapi.util.DefaultValue;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class DocumentDetailAssembler implements
        RepresentationModelAssembler<DocumentDetailDto, EntityModel<DocumentDetailDto>> {
    @Override
    public EntityModel<DocumentDetailDto> toModel(DocumentDetailDto documentDetailDto) {
        Optional<String> category = Optional.of(documentDetailDto.getCategory());
        Optional<String> defaultSortTarget = Optional.of(DefaultValue.sortTarget);
        Optional<String> defaultCategory = Optional.of(DefaultValue.category);

        EntityModel<DocumentDetailDto> model = EntityModel.of(documentDetailDto,
                linkTo(methodOn(DocumentController.class).findOne(documentDetailDto.getId())).withSelfRel(),
                linkTo(methodOn(DocumentController.class).findList(category, defaultSortTarget)).withRel("category"),
                linkTo(methodOn(DocumentController.class).findList(defaultCategory, defaultSortTarget)).withRel("documents")
        );
        return model;
    }
}
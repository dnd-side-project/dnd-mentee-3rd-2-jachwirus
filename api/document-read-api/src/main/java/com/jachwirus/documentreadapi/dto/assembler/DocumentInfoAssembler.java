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
        Optional<String> category = Optional.of(documentInfoDto.getCategory());
        Optional<String> defaultSortTarget = Optional.of(DefaultValue.sortTarget);
        Optional<String> defaultCategory = Optional.of(DefaultValue.category);

        EntityModel<DocumentInfoDto> model = EntityModel.of(documentInfoDto,
                linkTo(methodOn(DocumentController.class).findOne(documentInfoDto.getId())).withSelfRel(),
                linkTo(methodOn(DocumentController.class).findList(category, defaultSortTarget)).withRel("category"),
                linkTo(methodOn(DocumentController.class).findList(defaultCategory, defaultSortTarget)).withRel("documents")
                );
        return model;
    }
}

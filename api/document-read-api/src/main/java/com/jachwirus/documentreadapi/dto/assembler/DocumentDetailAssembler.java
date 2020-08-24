package com.jachwirus.documentreadapi.dto.assembler;

import com.jachwirus.documentreadapi.controller.DocumentController;
import com.jachwirus.documentreadapi.dto.model.DocumentDetailDto;
import com.jachwirus.documentreadapi.dto.model.DocumentInfoDto;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static com.jachwirus.documentreadapi.controller.DocumentController.ENTIRE_LIST;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class DocumentDetailAssembler implements
        RepresentationModelAssembler<DocumentDetailDto, EntityModel<DocumentDetailDto>> {
    @Override
    public EntityModel<DocumentDetailDto> toModel(DocumentDetailDto documentDetailDto) {
        EntityModel<DocumentDetailDto> model = EntityModel.of(documentDetailDto,
                linkTo(methodOn(DocumentController.class).findOne(documentDetailDto.getId())).withSelfRel(),
                linkTo(methodOn(DocumentController.class).findList(documentDetailDto.getCategory())).withRel("category"),
                linkTo(methodOn(DocumentController.class).findList(ENTIRE_LIST)).withRel("documents")
        );
        return model;
    }
}
package com.jachwirus.documentreadapi.dto.assembler;

import static com.jachwirus.documentreadapi.controller.DocumentController.ENTIRE_LIST;
import com.jachwirus.documentreadapi.controller.DocumentController;
import com.jachwirus.documentreadapi.dto.model.DocumentInfoDto;
import com.jachwirus.documentreadapi.util.DefaultValue;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class DocumentInfoAssembler implements
        RepresentationModelAssembler<DocumentInfoDto, EntityModel<DocumentInfoDto>> {
    @Override
    public EntityModel<DocumentInfoDto> toModel(DocumentInfoDto documentInfoDto) {
        String category = documentInfoDto.getCategory();
        String sortTarget = DefaultValue.sortTarget;

        EntityModel<DocumentInfoDto> model = EntityModel.of(documentInfoDto,
                linkTo(methodOn(DocumentController.class).findOne(documentInfoDto.getId())).withSelfRel(),
                linkTo(methodOn(DocumentController.class).findList(category, sortTarget)).withRel("category"),
                linkTo(methodOn(DocumentController.class).findList(ENTIRE_LIST, sortTarget)).withRel("documents")
                );
        return model;
    }
}

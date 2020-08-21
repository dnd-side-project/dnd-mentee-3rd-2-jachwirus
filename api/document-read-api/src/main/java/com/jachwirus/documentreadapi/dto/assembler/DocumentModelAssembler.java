package com.jachwirus.documentreadapi.dto.assembler;

import static com.jachwirus.documentreadapi.controller.DocumentController.ENTIRE_LIST;
import com.jachwirus.documentreadapi.controller.DocumentController;
import com.jachwirus.documentreadapi.dto.model.DocumentDto;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class DocumentModelAssembler implements RepresentationModelAssembler<DocumentDto, EntityModel<DocumentDto>> {
    @Override
    public EntityModel<DocumentDto> toModel(DocumentDto documentDto) {
        EntityModel<DocumentDto> model = EntityModel.of(documentDto,
                linkTo(methodOn(DocumentController.class).findOne(documentDto.getId())).withSelfRel(),
                linkTo(methodOn(DocumentController.class).findList(documentDto.getCategory())).withRel("category"),
                linkTo(methodOn(DocumentController.class).findList(ENTIRE_LIST)).withRel("documents")
                );
        return model;
    }
}

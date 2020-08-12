package com.jachwirus.documentreadapi.model;

import com.jachwirus.documentreadapi.controller.DocumentController;
import com.jachwirus.documentreadapi.dto.Document;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class DocumentModelAssembler implements RepresentationModelAssembler<Document, EntityModel<Document>> {
    @Override
    public EntityModel<Document> toModel(Document document) {
        EntityModel<Document> model = EntityModel.of(document,
                linkTo(methodOn(DocumentController.class).one(document.getId())).withSelfRel(),
                linkTo(methodOn(DocumentController.class).all()).withRel("documents")
                );
        return model;
    }
}

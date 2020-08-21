package com.jachwirus.documentreadapi.dto.mapper;

import com.jachwirus.documentreadapi.dto.model.DocumentDto;
import com.jachwirus.documentreadapi.model.Document;
import com.jachwirus.documentreadapi.model.DocumentVersion;

import java.util.ArrayList;
import java.util.List;

public class DocumentDtoMapper {
    public static DocumentDto toDocumentDto(Document document) {
        List<DocumentVersion> versions = document.getVersions();
        DocumentVersion lastVersion = versions.get(versions.size() - 1);
        List<String> hashTags = new ArrayList<>();
        int numberOfComments = 0;

        return new DocumentDto()
                .setId(document.getId())
                .setTitle(document.getTitle())
                .setThumbnailURL(lastVersion.getThumbnailUrl())
                .setDataURL(lastVersion.getDataUrl())
                .setLikes(document.getLikes())
                .setDislikes(document.getDislikes())
                .setViewCount(document.getViewCount())
                .setCategory(document.getCategory())
                .setHashTags(hashTags)
                .setNumberOfComments(numberOfComments);
    }
}

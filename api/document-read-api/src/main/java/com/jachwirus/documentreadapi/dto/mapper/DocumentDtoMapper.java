package com.jachwirus.documentreadapi.dto.mapper;

import com.jachwirus.documentreadapi.dto.model.DocumentDto;
import com.jachwirus.documentreadapi.model.Document;
import com.jachwirus.documentreadapi.model.DocumentHashTag;
import com.jachwirus.documentreadapi.model.DocumentVersion;
import com.jachwirus.documentreadapi.model.HashTag;

import java.util.List;
import java.util.stream.Collectors;

public class DocumentDtoMapper {
    public static DocumentDto toDocumentDto(Document document) {
        List<DocumentVersion> versions = document.getVersions();
        DocumentVersion lastVersion = versions.get(versions.size() - 1);
        List<String> hashTags = document.getTags().stream()
                .map(DocumentHashTag::getHashTag)
                .map(HashTag::getTagName)
                .collect(Collectors.toList());
        int numberOfComments = document.getComments().size();

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

package com.jachwirus.documentreadapi.dto.mapper;

import com.jachwirus.documentreadapi.dto.model.DocumentInfoDto;
import com.jachwirus.documentreadapi.model.Document;
import com.jachwirus.documentreadapi.model.DocumentVersion;
import com.jachwirus.documentreadapi.util.HashTagParser;

import java.util.List;

public class DocumentInfoDtoMapper {
    public static DocumentInfoDto toDocumentInfoDto(Document document) {
        DocumentVersion lastVersion = document.getLatestVersion();
        List<String> hashTags = HashTagParser.extractHashTagsName(document.getTags());
        int numberOfComments = document.getComments().size();

        return new DocumentInfoDto()
                .setId(document.getId())
                .setTitle(document.getTitle())
                .setThumbnailURL(lastVersion.getThumbnailUrl())
                .setLikes(document.getLikes())
                .setDislikes(document.getDislikes())
                .setViewCount(document.getViewCount())
                .setCategory(document.getCategory())
                .setHashTags(hashTags)
                .setNumberOfComments(numberOfComments)
                .setLastModified(lastVersion.getCreatedAt());
    }
}

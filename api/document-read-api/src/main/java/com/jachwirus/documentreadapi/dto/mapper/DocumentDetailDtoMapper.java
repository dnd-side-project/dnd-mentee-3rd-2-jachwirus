package com.jachwirus.documentreadapi.dto.mapper;

import com.jachwirus.documentreadapi.dto.model.DocumentDetailDto;
import com.jachwirus.documentreadapi.model.Document;
import com.jachwirus.documentreadapi.model.DocumentVersion;
import com.jachwirus.documentreadapi.util.HashTagParser;

import java.util.List;

public class DocumentDetailDtoMapper {
    public static DocumentDetailDto toDocumentDetailDto(Document document, String contents) {
        DocumentVersion lastVersion = document.getLatestVersion();
        List<String> hashTags = HashTagParser.extractHashTagsName(document.getTags());

        return new DocumentDetailDto()
                .setId(document.getId())
                .setTitle(document.getTitle())
                .setThumbnailURL(lastVersion.getThumbnailUrl())
                .setContents(contents)
                .setLikes(document.getLikes())
                .setDislikes(document.getDislikes())
                .setViewCount(document.getViewCount())
                .setCategory(document.getCategory())
                .setHashTags(hashTags)
                .setLastModified(lastVersion.getCreatedAt())
                .setComments(document.getComments())
                .setFlag(lastVersion.getFlag());
    }
}

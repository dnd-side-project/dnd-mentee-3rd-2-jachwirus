package com.jachwirus.documentreadapi.dto.mapper;

import com.jachwirus.documentreadapi.dto.model.DocumentDetailDto;
import com.jachwirus.documentreadapi.model.Document;
import com.jachwirus.documentreadapi.model.DocumentVersion;

import java.util.List;

public class DocumentDetailDtoMapper {
    public static DocumentDetailDto toDocumentDetailDto(Document document) {
        DocumentVersion lastVersion = Util.getLatestVersion(document);
        List<String> hashTags = Util.getHashTags(document.getTags());

        return new DocumentDetailDto()
                .setId(document.getId())
                .setTitle(document.getTitle())
                .setDataURL(lastVersion.getDataUrl())
                .setLikes(document.getLikes())
                .setDislikes(document.getDislikes())
                .setViewCount(document.getViewCount())
                .setCategory(document.getCategory())
                .setHashTags(hashTags)
                .setComments(document.getComments())
                .setFlag(lastVersion.getFlag());
    }
}

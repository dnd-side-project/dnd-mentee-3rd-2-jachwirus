package com.jachwirus.documentreadapi.dto.mapper;

import com.jachwirus.documentreadapi.dto.model.CommentDto;
import com.jachwirus.documentreadapi.dto.model.DocumentDetailDto;
import com.jachwirus.documentreadapi.model.Document;
import com.jachwirus.documentreadapi.model.DocumentVersion;
import com.jachwirus.documentreadapi.model.User;
import com.jachwirus.documentreadapi.util.HashTagParser;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DocumentDetailDtoMapper {
    public static DocumentDetailDto toDocumentDetailDto(Document document, String contents) {
        DocumentVersion lastVersion = document.getLatestVersion();
        List<String> hashTags = HashTagParser.extractHashTagsName(document.getTags());
        List<CommentDto> comments = document.getComments().stream()
                .map(comment -> CommentDtoMapper.toCommentDto(comment))
                .collect(Collectors.toList());
        String contributor = Optional
                .ofNullable(lastVersion.getContributor().getNickname())
                .orElse("UNKNOWN");
        
        return new DocumentDetailDto()
                .setId(document.getId())
                .setTitle(document.getTitle())
                .setContributor(contributor)
                .setThumbnailURL(lastVersion.getThumbnailUrl())
                .setContents(contents)
                .setLikes(document.getLikes())
                .setDislikes(document.getDislikes())
                .setViewCount(document.getViewCount())
                .setCategory(document.getCategory())
                .setHashTags(hashTags)
                .setLastModified(lastVersion.getCreatedAt())
                .setComments(comments)
                .setFlag(lastVersion.getFlag());
    }
}

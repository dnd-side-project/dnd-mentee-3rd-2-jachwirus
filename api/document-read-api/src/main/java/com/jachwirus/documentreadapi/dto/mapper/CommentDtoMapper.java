package com.jachwirus.documentreadapi.dto.mapper;

import com.jachwirus.documentreadapi.dto.model.CommentDto;
import com.jachwirus.documentreadapi.model.Comment;

public class CommentDtoMapper {
    public static CommentDto toCommentDto(Comment comment) {
        CommentDto dto = new CommentDto()
                .setId(comment.getId())
                .setContents(comment.getContents())
                .setCreatedAt(comment.getCreatedAt())
                .setModified(comment.isModified())
                .setUserNickName(comment.getUser().getNickname());
        return dto;
    }
}

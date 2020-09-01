package com.jachwirus.documentreadapi.dto.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain=true)
public class CommentDto {
    private Long id;
    private String userNickName;
    private String contents;
    private Date createdAt;
    private boolean isModified;
}

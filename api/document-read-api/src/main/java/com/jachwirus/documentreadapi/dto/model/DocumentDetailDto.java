package com.jachwirus.documentreadapi.dto.model;

import com.jachwirus.documentreadapi.model.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Accessors(chain=true)
public class DocumentDetailDto {
    private Long id;
    private String title;
    private String dataURL;
    private int likes;
    private int dislikes;
    private int viewCount;
    private String category;
    private List<String> hashTags;
    private List<Comment> comments;
    private int flag;
}

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
public class DocumentDetailDto extends DocumentInfoDto{
    private List<Comment> comments;
    private int flag;
}

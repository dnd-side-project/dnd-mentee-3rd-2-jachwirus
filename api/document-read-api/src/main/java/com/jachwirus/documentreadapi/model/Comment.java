package com.jachwirus.documentreadapi.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class Comment {
    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "document_id")
    private Document document;
    private Long userId;
    private String contents;
    private Date createdAt;
    private boolean isModified;
}

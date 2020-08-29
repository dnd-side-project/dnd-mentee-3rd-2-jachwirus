package com.jachwirus.documentreadapi.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Comment {
    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "document_id")
    private Document document;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String contents;
    private Date createdAt;
    private boolean isModified;
}

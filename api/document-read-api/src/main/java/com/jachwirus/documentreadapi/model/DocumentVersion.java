package com.jachwirus.documentreadapi.model;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Accessors(chain=true)
public class DocumentVersion {
    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "document_id")
    private Document document;
    private Long contributorId;
    private String dataUrl;
    private String thumbnailUrl;
    private int flag;//unique
    private Date createdAt;//timestamp
    private String diff;
}

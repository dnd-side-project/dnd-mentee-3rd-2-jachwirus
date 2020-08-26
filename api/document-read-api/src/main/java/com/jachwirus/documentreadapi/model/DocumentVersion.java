package com.jachwirus.documentreadapi.model;

import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString(exclude = {"contributor", "document"})
@EqualsAndHashCode
@NoArgsConstructor
@Accessors(chain=true)
public class DocumentVersion {
    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "document_id")
    private Document document;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contributor_id")
    private User contributor;

    private String dataUrl;
    private String thumbnailUrl;
    private int flag;//unique
    private Date createdAt;//timestamp
    private String diff;
}

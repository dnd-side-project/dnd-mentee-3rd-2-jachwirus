package com.jachwirus.documentreadapi.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
public class HotChart {
    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "document_id")
    private Document document;
}

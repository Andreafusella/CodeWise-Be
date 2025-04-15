package com.codeWise.codeWise.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Valutation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comment;

    private String feedback;

    @OneToOne
    private Paper paper;
}
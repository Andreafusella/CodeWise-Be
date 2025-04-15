package com.codeWise.codeWise.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Paper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private Double grade;

    @ManyToOne
    private Exercise exercise;

    @ManyToOne
    private Student student;

    @OneToOne(mappedBy = "paper")
    private Valutation valutation;
}
package com.codeWise.codeWise.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Resource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;
    private String subject;
    private LocalDate uploadDate;

    @ManyToOne
    private Teacher teacher;

    public Resource(String text, String subject, LocalDate uploadDate, Teacher teacher) {
        this.text = text;
        this.subject = subject;
        this.uploadDate = uploadDate;
        this.teacher = teacher;
    }
}
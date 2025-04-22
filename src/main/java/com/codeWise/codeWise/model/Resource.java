package com.codeWise.codeWise.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

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

    @NonNull
    private String text;

    @NonNull
    private String subject;

    @NonNull
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
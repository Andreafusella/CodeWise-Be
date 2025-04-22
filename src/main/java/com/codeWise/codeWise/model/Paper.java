package com.codeWise.codeWise.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class Paper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String comment;

    @NonNull
    private LocalDate uploadDate;

    @NonNull
    private String fileUrl;

    @ManyToOne
    private Exercise exercise;

    @ManyToOne
    private Student student;

    public Paper(@NonNull String comment, @NonNull LocalDate uploadDate, @NonNull String fileUrl, Exercise exercise, Student student) {
        this.comment = comment;
        this.uploadDate = uploadDate;
        this.fileUrl = fileUrl;
        this.exercise = exercise;
        this.student = student;
    }
}
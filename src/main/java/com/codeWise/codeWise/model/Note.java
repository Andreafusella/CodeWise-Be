package com.codeWise.codeWise.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String title;

    @NotNull
    private String description;

    @NotNull
    private LocalDate uploadDate;

    @ManyToOne
    private Attachment attachment;

    @ManyToOne(cascade = CascadeType.REMOVE)
    private Student student;

    public Note(String title, String description, LocalDate uploadDate, Attachment attachment, Student student) {
        this.title = title;
        this.description = description;
        this.uploadDate = uploadDate;
        this.attachment = attachment;
        this.student = student;
    }
}
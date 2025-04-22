package com.codeWise.codeWise.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data
@NoArgsConstructor
public class Attachment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String fileName;

    private String fileUrl;

    @NonNull
    private String type;

    @ManyToOne
    private Course course;

    public Attachment(String fileName, String fileUrl, String type, Course course) {
        this.fileName = fileName;
        this.fileUrl = fileUrl;
        this.type = type;
        this.course = course;
    }
}
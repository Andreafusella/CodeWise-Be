package com.codeWise.codeWise.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class StudentResource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private LocalDate date;

    @ManyToOne
    private Student student;

    @ManyToOne
    private Resource resource;

    public StudentResource(LocalDate date, Student student, Resource resource) {
        this.date = date;
        this.student = student;
        this.resource = resource;
    }
}

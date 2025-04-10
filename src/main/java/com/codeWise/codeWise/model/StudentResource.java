package com.codeWise.codeWise.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class StudentResource {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDate date;

    @ManyToOne
    private Resource resource;

    @ManyToOne
    private Student student;

    public StudentResource(LocalDate date, Resource resource, Student student) {
        this.date = date;
        this.resource = resource;
        this.student = student;
    }
}

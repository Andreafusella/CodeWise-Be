package com.codeWise.codeWise.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String lastName;
    private String email;
    private LocalDate dateBirth;
    private String placeBirth;
    private Integer yearRegistration;

    @ManyToMany
    private List<Resource> resources;

    @ManyToOne
    private Course course;

    public Student(String name, String lastName, String email, LocalDate dateBirth, String placeBirth, Integer yearRegistration, Course course) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.dateBirth = dateBirth;
        this.placeBirth = placeBirth;
        this.yearRegistration = yearRegistration;
        this.course = course;
    }
}

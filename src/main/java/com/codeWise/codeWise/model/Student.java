package com.codeWise.codeWise.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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
    private LocalDate placeBirth;
    private Integer yearRegistration;

    @ManyToOne
    private Course course;

    public Student(String name, String lastName, String email, LocalDate dateBirth, LocalDate placeBirth, Integer yearRegistration) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.dateBirth = dateBirth;
        this.placeBirth = placeBirth;
        this.yearRegistration = yearRegistration;
    }
}

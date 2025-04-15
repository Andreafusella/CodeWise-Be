package com.codeWise.codeWise.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private String lastName;

    @NonNull
    @Email
    private String email;

    @NonNull
    private LocalDate dateBirth;

    @NonNull
    private String placeBirth;

    @NonNull
    private Integer yearRegistration;

    @ManyToOne
    private Course course;

    public Student(String name, String lastName, String email, LocalDate dateBirth, String placeBirth, Integer yearRegistration) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.dateBirth = dateBirth;
        this.placeBirth = placeBirth;
        this.yearRegistration = yearRegistration;
    }
}
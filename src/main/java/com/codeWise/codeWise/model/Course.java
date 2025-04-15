package com.codeWise.codeWise.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.NonNull;

@Entity
@Data
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private Integer accademicYear;

    @NonNull
    private Integer creditNumber;

    @NonNull
    private String degreeProgram;




}
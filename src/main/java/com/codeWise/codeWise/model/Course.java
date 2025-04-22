package com.codeWise.codeWise.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data
@NoArgsConstructor
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

    public Course(@NonNull String name, @NonNull Integer accademicYear, @NonNull Integer creditNumber) {
        this.name = name;
        this.accademicYear = accademicYear;
        this.creditNumber = creditNumber;
    }
}
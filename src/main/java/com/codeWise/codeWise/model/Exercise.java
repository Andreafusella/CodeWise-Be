package com.codeWise.codeWise.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private LocalDate dateStart;

    @NonNull
    private LocalDate dateEnd;

    @NonNull
    private String description;

    @ManyToOne
    private Teacher teacher;

    @ManyToOne
    private Course course;

    public Exercise(@NonNull LocalDate dateStart, @NonNull LocalDate dateEnd, @NonNull String description, Teacher teacher, Course course) {
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.description = description;
        this.teacher = teacher;
        this.course = course;
    }
}
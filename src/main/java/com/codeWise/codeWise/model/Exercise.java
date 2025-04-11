package com.codeWise.codeWise.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Boolean hasGrade;
    private LocalDate dateStart;
    private LocalDate dateEnd;
    private String description;

    @ManyToOne
    private Teacher teacher;

    public Exercise(Boolean hasGrade, LocalDate dateStart, LocalDate dateEnd, String description) {
        this.hasGrade = hasGrade;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.description = description;
    }
}

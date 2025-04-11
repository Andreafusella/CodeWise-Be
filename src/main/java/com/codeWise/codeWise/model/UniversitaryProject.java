package com.codeWise.codeWise.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class UniversitaryProject {

    @Id
    @GeneratedValue
    private Long id;

    private Integer maxValutation;

    @ManyToOne
    private Course course;

    public UniversitaryProject(Integer maxValutation) {
        this.maxValutation = maxValutation;
    }
}

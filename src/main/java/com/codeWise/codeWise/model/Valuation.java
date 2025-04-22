package com.codeWise.codeWise.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data
@NoArgsConstructor
public class Valuation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private Integer valuation;

    @NonNull
    private String comment;

    @OneToOne
    private Paper paper;

    public Valuation(@NonNull Integer valuation, @NonNull String comment, Paper paper) {
        this.valuation = valuation;
        this.comment = comment;
        this.paper = paper;
    }
}
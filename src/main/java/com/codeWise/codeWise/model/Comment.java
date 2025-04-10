package com.codeWise.codeWise.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String text;
    private LocalDate uploadDate;
    private Integer rating;

    public Comment(String text, LocalDate uploadDate, Integer rating) {
        this.text = text;
        this.uploadDate = uploadDate;
        this.rating = rating;
    }
}

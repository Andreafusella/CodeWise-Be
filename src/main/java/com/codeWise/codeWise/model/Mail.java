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
public class Mail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String subject;
    private String content;
    private LocalDate sendDate;

    public Mail(String subject, String content, LocalDate sendDate) {
        this.subject = subject;
        this.content = content;
        this.sendDate = sendDate;
    }
}

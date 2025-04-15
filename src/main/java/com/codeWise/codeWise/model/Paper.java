package com.codeWise.codeWise.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Paper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


}
package com.codeWise.codeWise.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class MailBox {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
}

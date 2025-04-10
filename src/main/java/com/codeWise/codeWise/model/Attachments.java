package com.codeWise.codeWise.model;

import com.codeWise.codeWise.type.TypeAttachments;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Attachments {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private TypeAttachments type;
    private String text;

    @ManyToOne
    private Course course;

    public Attachments(TypeAttachments type, String text) {
        this.type = type;
        this.text = text;
    }
}

package com.codeWise.codeWise.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class NewResourceDto {
    private String text;
    private String subject;
    private LocalDate uploadDate;
    private Long idTeacher;
}
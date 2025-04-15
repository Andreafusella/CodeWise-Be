package com.codeWise.codeWise.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class NewStudentResourceDto {

    private LocalDate date;
    private Long idStudent;
    private Long idResource;
}

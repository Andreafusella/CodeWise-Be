package com.codeWise.codeWise.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class NewStudentDto {

    private String name;
    private String lastName;
    private String email;
    private LocalDate dateBirth;
    private String placeBirth;
    private Integer yearRegistration;
}

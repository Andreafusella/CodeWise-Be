package com.codeWise.codeWise.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class NewExerciseDto {

    private LocalDate dateStart;
    private LocalDate dateEnd;
    private String description;
    private Long idTeacher;
    private Long idCourse;
}

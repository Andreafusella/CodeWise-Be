package com.codeWise.codeWise.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class ExcelResponseDto {
    private String nameCourse;
    private Integer accademicYear;
    private Integer creditNumber;
    private LocalDate dateStartExercise;
    private LocalDate dateEndExercise;
    private String descriptionExercise;
    private Long numberOfStudents;

    public ExcelResponseDto(
            String nameCourse,
            Integer accademicYear,
            Integer creditNumber,
            LocalDate dateStartExercise,
            LocalDate dateEndExercise,
            String descriptionExercise,
            Long numberOfStudents
    ) {
        this.nameCourse = nameCourse;
        this.accademicYear = accademicYear;
        this.creditNumber = creditNumber;
        this.dateStartExercise = dateStartExercise;
        this.dateEndExercise = dateEndExercise;
        this.descriptionExercise = descriptionExercise;
        this.numberOfStudents = numberOfStudents;
    }
}

package com.codeWise.codeWise.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SetStudentToCourseDto {

    private Long idCourse;
    private Long idStudent;
}

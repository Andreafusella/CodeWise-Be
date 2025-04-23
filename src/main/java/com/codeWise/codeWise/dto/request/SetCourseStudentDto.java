package com.codeWise.codeWise.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SetCourseStudentDto {

    private Integer idCourse;
    private Integer idStudent;
}

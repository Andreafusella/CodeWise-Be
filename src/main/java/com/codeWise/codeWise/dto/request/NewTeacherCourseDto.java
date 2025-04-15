package com.codeWise.codeWise.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NewTeacherCourseDto {
    private Long idTeacher;
    private Long idCourse;
}

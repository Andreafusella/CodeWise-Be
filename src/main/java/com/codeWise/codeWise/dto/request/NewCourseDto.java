package com.codeWise.codeWise.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class NewCourseDto {
    private String name;
    private Integer accademicYear;
    private Integer creditNumber;
    private String degreeProgram;
}

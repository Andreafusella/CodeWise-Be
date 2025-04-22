package com.codeWise.codeWise.dto.request;



import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class NewTeacherDto {

    private String name;
    private String lastName;
    private String email;
    private String role;
}

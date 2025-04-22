package com.codeWise.codeWise.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class NewPaperDto {

    private String comment;
    private LocalDate uploadDate;
    private String fileUrl;
    private Long idExercise;
    private Long idStudent;
}

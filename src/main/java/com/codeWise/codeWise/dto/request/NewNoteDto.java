package com.codeWise.codeWise.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class NewNoteDto {

    private String title;
    private String description;
    private LocalDate uploadDate;
    private Long idStudent;
    private Long idAttachment;


}

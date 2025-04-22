package com.codeWise.codeWise.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NewAttachmentDto {

    private Long idCourse;
    private String fileName;
    private String fileUrl;
    private String type;

    public NewAttachmentDto(Long idCourse, String fileName, String fileUrl, String type) {
        this.idCourse = idCourse;
        this.fileName = fileName;
        this.fileUrl = fileUrl;
        this.type = type;
    }
}

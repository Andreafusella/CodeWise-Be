package com.codeWise.codeWise.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NewValuationDto {

    private Integer valuation;
    private String comment;
    private Long idPaper;
}

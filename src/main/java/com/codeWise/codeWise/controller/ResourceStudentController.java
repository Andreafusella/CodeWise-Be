package com.codeWise.codeWise.controller;

import com.codeWise.codeWise.model.Resource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resource-student")
@Tag(name = "Resource Student Controller", description = "Gestione delle risorse dello studente")
public class ResourceStudentController {


    @Operation(summary = "Aggiunge uno student a una resource")
    @PostMapping("/{resourceId}/add-student/{studentId}")
    public Resource addStudentToResource(@PathVariable Long resourceId, @PathVariable Long studentId) {
        return resourceService.addStudentToResource(resourceId, studentId);
    }
}

package com.codeWise.codeWise.controller;

import com.codeWise.codeWise.model.UniversitaryProject;
import com.codeWise.codeWise.model.Student;
import com.codeWise.codeWise.service.UniversitaryProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/universitaryProject")
@Tag(name = "UniversitaryProject", description = "Endpoints for managing universitary projects")
public class UniversitaryProjectController {

    @Autowired
    private UniversitaryProjectService universitaryProjectService;

    // @Operation(summary = "Create a new universitary project")
    // @PostMapping
    
}
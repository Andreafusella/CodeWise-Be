package com.codeWise.codeWise.controller;

import com.codeWise.codeWise.model.Teacher;
import com.codeWise.codeWise.service.TeacherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teacher")
@Tag(name = "Teacher", description = "Endpoints for managing teachers")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    // @Operation(summary = "Create a new teacher")
    // @PostMapping
    
}
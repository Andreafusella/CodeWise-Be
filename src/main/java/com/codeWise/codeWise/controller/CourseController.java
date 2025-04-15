package com.codeWise.codeWise.controller;

import com.codeWise.codeWise.model.Course;
import com.codeWise.codeWise.model.Teacher;
import com.codeWise.codeWise.service.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/course")
@Tag(name = "Course", description = "Endpoints for managing courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    // @Operation(summary = "Create a new course")
    // @PostMapping
    
}
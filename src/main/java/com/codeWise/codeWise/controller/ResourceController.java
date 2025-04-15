package com.codeWise.codeWise.controller;

import com.codeWise.codeWise.model.Resource;
import com.codeWise.codeWise.service.ResourceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resource")
@Tag(name = "Resource", description = "Endpoints for managing resources")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    // @Operation(summary = "Create a new resource")
    // @PostMapping
    
}
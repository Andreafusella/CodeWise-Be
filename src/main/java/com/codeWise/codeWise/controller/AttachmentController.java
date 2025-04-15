package com.codeWise.codeWise.controller;

import com.codeWise.codeWise.model.Attachment;
import com.codeWise.codeWise.service.AttachmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attachment")
@Tag(name = "Attachment", description = "Endpoints for managing attachments")
public class AttachmentController {

    @Autowired
    private AttachmentService attachmentService;

    // @Operation(summary = "Create a new attachment")
    // @PostMapping
    
}
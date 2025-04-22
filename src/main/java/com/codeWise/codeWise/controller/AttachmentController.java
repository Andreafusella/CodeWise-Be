package com.codeWise.codeWise.controller;

import com.codeWise.codeWise.dto.request.NewAttachmentDto;
import com.codeWise.codeWise.exception.EntityNotFoundException;
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

    @Operation(summary = "Create a new attachment")
    @PostMapping
    public ResponseEntity<?> create(@RequestBody NewAttachmentDto dto) {
        try {
            Attachment attachment = attachmentService.createAttachments(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(attachment);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Get an attachment by ID")
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            Attachment attachment = attachmentService.getById(id);
            return ResponseEntity.ok().body(attachment);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Get all attachments")
    @GetMapping
    public ResponseEntity<?> getAll() {
        try {
            List<Attachment> list = attachmentService.getAll();
            return ResponseEntity.ok().body(list);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Delete an attachment by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        try {
            attachmentService.deleteAttachmentById(id);
            return ResponseEntity.ok().body("Attachment with ID " + id + " has been deleted.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
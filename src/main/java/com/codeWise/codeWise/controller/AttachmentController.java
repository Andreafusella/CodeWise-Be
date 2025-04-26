package com.codeWise.codeWise.controller;

import com.codeWise.codeWise.dto.request.NewAttachmentDto;
import com.codeWise.codeWise.exception.EntityNotFoundException;
import com.codeWise.codeWise.model.Attachment;
import com.codeWise.codeWise.service.AttachmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Create a new attachment",
            description = "Creates a new attachment linked to a course.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Attachment created successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Attachment.class))),
            @ApiResponse(responseCode = "404", description = "Course not found",
                    content = @Content(mediaType = "text/plain")),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "text/plain"))
    })
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

    @Operation(summary = "Get an attachment by ID",
            description = "Retrieves a specific attachment based on its unique ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Attachment found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Attachment.class))),
            @ApiResponse(responseCode = "404", description = "Attachment not found with the given ID",
                    content = @Content(mediaType = "text/plain")),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "text/plain"))
    })
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

    @Operation(summary = "Get all attachments",
            description = "Retrieves a list of all existing attachments.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of attachments retrieved successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = List.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "text/plain"))
    })
    @GetMapping
    public ResponseEntity<?> getAll() {
        try {
            List<Attachment> list = attachmentService.getAll();
            return ResponseEntity.ok().body(list);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Delete an attachment by ID",
            description = "Deletes an attachment based on its unique ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Attachment deleted successfully",
                    content = @Content(mediaType = "text/plain")),
            @ApiResponse(responseCode = "404", description = "Attachment not found with the given ID",
                    content = @Content(mediaType = "text/plain")),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "text/plain"))
    })
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
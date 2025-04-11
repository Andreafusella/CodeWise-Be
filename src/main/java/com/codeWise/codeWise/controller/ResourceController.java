package com.codeWise.codeWise.controller;

import com.codeWise.codeWise.model.Resource;
import com.codeWise.codeWise.service.ResourceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/resource")
@Tag(name = "Resource Controller", description = "Gestione delle risorse")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    @Operation(summary = "Crea una nuova resource")
    @PostMapping
    public Resource createResource(@RequestBody Resource resource) {
        return resourceService.createResource(resource);
    }

    @Operation(summary = "Recupera tutte le resource")
    @GetMapping
    public List<Resource> getAllResources() {
        return resourceService.getAllResources();
    }

    @Operation(summary = "Recupera una resource per id")
    @GetMapping("/{id}")
    public Resource getResourceById(@PathVariable Long id) {
        return resourceService.getResourceById(id);
    }

    @Operation(summary = "Aggiorna una resource")
    @PutMapping("/{id}")
    public Resource updateResource(@PathVariable Long id, @RequestBody Resource resource) {
        return resourceService.updateResource(id, resource);
    }

    @Operation(summary = "Elimina una resource")
    @DeleteMapping("/{id}")
    public void deleteResource(@PathVariable Long id) {
        resourceService.deleteResource(id);
    }

    @Operation(summary = "Aggiunge uno student a una resource")
    @PostMapping("/{resourceId}/add-student/{studentId}")
    public Resource addStudentToResource(@PathVariable Long resourceId, @PathVariable Long studentId) {
        return resourceService.addStudentToResource(resourceId, studentId);
    }
}

package com.codeWise.codeWise.service;

import com.codeWise.codeWise.exception.ResourceNotFoundException;
import com.codeWise.codeWise.model.Resource;
import com.codeWise.codeWise.model.Student;
import com.codeWise.codeWise.repository.ResourceRepository;
import com.codeWise.codeWise.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceService {

    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    private StudentRepository studentRepository;

    public Resource createResource(Resource resource) {
        return resourceRepository.save(resource);
    }

    public Resource getResourceById(Long id) {
        return resourceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found with id: " + id));
    }

    public List<Resource> getAllResources() {
        return resourceRepository.findAll();
    }

    public Resource updateResource(Long id, Resource updatedResource) {
        Resource existingResource = getResourceById(id);
        existingResource.setCode(updatedResource.getCode());
        existingResource.setSubject(updatedResource.getSubject());
        existingResource.setText(updatedResource.getText());
        existingResource.setUploadDate(updatedResource.getUploadDate());
        existingResource.setTeacher(updatedResource.getTeacher());
        return resourceRepository.save(existingResource);
    }

    public void deleteResource(Long id) {
        if (!resourceRepository.existsById(id)) {
            throw new ResourceNotFoundException("Resource not found with id: " + id);
        }
        resourceRepository.deleteById(id);
    }
}

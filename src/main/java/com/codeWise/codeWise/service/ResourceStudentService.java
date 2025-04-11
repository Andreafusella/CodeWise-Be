package com.codeWise.codeWise.service;

import com.codeWise.codeWise.exception.ResourceNotFoundException;
import com.codeWise.codeWise.model.Resource;
import com.codeWise.codeWise.model.Student;
import com.codeWise.codeWise.repository.ResourceRepository;
import com.codeWise.codeWise.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResourceStudentService {

    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    private StudentRepository studentRepository;

    public Resource addStudentToResource(Long resourceId, Long studentId) {
        Resource resource = getResourceById(resourceId);
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + studentId));
        resource.getStudents().add(student);
        return resourceRepository.save(resource);
    }

    private Resource getResourceById(Long id) {
        return resourceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found with id: " + id));
    }
}

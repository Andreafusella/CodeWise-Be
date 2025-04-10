package com.codeWise.codeWise.service;

import com.codeWise.codeWise.model.StudentResource;
import com.codeWise.codeWise.repository.StudentResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentResourceService {

    @Autowired
    private StudentResourceRepository studentResourceRepository;

    public StudentResource createStudentResource(StudentResource studentResource) {
        return studentResourceRepository.save(studentResource);
    }

    public Optional<StudentResource> getStudentResourceById(Long id) {
        return studentResourceRepository.findById(id);
    }

    public List<StudentResource> getAllStudentResources() {
        return studentResourceRepository.findAll();
    }

    public boolean deleteStudentResource(Long id) {
        if (studentResourceRepository.existsById(id)) {
            studentResourceRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}

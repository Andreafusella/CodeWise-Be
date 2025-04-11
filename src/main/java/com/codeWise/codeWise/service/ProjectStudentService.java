package com.codeWise.codeWise.service;

import com.codeWise.codeWise.model.ProjectStudent;
import com.codeWise.codeWise.repository.ProjectStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectStudentService {

    @Autowired
    private ProjectStudentRepository projectStudentRepository;

    public ProjectStudent createProjectStudent(ProjectStudent projectStudent) {
        return projectStudentRepository.save(projectStudent);
    }

    public Optional<ProjectStudent> getProjectStudentById(Long id) {
        return projectStudentRepository.findById(id);
    }

    public List<ProjectStudent> getAllProjectStudents() {
        return projectStudentRepository.findAll();
    }

    public boolean deleteProjectStudent(Long id) {
        if (projectStudentRepository.existsById(id)) {
            projectStudentRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

package com.codeWise.codeWise.service;

import com.codeWise.codeWise.model.UniversitaryProject;
import com.codeWise.codeWise.repository.UniversitaryProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UniversitaryProjectService {

    @Autowired
    private UniversitaryProjectRepository universitaryProjectRepository;

    public UniversitaryProject createUniversitaryProject(UniversitaryProject project) {
        return universitaryProjectRepository.save(project);
    }

    public Optional<UniversitaryProject> getUniversitaryProjectById(Long id) {
        return universitaryProjectRepository.findById(id);
    }

    public List<UniversitaryProject> getAllUniversitaryProjects() {
        return universitaryProjectRepository.findAll();
    }

    public boolean deleteUniversitaryProject(Long id) {
        if (universitaryProjectRepository.existsById(id)) {
            universitaryProjectRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}

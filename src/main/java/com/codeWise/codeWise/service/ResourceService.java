package com.codeWise.codeWise.service;

import com.codeWise.codeWise.dto.request.NewResourceDto;
import com.codeWise.codeWise.exception.EntityNotFoundException;
import com.codeWise.codeWise.model.Resource;
import com.codeWise.codeWise.model.Teacher;
import com.codeWise.codeWise.repository.ResourceRepository;
import com.codeWise.codeWise.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ResourceService {

    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    private TeacherRepository teacherRepository;


    public Resource newResource(NewResourceDto resourceDto) {
        Optional<Teacher> teacherOpt = teacherRepository.findById(resourceDto.getIdTeacher());

        if (teacherOpt.isEmpty()) {
            throw new EntityNotFoundException("Not found teacher with id: " + resourceDto.getIdTeacher());
        }

        Resource resource = new Resource(
                resourceDto.getText(),
                resourceDto.getSubject(),
                resourceDto.getUploadDate(),
                teacherOpt.get()
        );

        return resourceRepository.save(resource);
    }

    public Resource getById(Long id) {
        Optional<Resource> resource = resourceRepository.findById(id);

        if (resource.isPresent()) {
            return resource.get();
        }

        throw new EntityNotFoundException("Not exist Resource with id: " + id);
    }

    public List<Resource> getAll() {
        return resourceRepository.findAll();
    }

    public void deleteResourceById(Long id) {
        Optional<Resource> resource = resourceRepository.findById(id);

        if (resource.isPresent()){
            resourceRepository.delete(resource.get());
            return;
        }

        throw new EntityNotFoundException("Not exist Resource with id: " + id);
    }
}
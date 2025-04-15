package com.codeWise.codeWise.service;

import com.codeWise.codeWise.dto.request.NewStudentResourceDto;
import com.codeWise.codeWise.exception.EntityNotFoundException;
import com.codeWise.codeWise.model.Resource;
import com.codeWise.codeWise.model.Student;
import com.codeWise.codeWise.model.StudentResource;
import com.codeWise.codeWise.repository.ResourceRepository;
import com.codeWise.codeWise.repository.StudentRepository;
import com.codeWise.codeWise.repository.StudentResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class StudentResourceService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    private StudentResourceRepository studentResourceRepository;

    public StudentResource create(NewStudentResourceDto dto) {
        Optional<Student> student = studentRepository.findById(dto.getIdStudent());
        Optional<Resource> resource = resourceRepository.findById(dto.getIdResource());

        if (student.isPresent() && resource.isPresent()) {
            StudentResource studentResource = new StudentResource(
                    dto.getDate(),
                    student.get(),
                    resource.get()
            );

            return studentResourceRepository.save(studentResource);
        }

        if (student.isEmpty()) {
            throw new EntityNotFoundException("Not exist Student with id: " + dto.getIdStudent());
        } else {
            throw new EntityNotFoundException("Not exist Resource with id: " + dto.getIdResource());
        }
    }

    public StudentResource getById(Long id) {
        Optional<StudentResource> studentResource = studentResourceRepository.findById(id);

        if (studentResource.isPresent()) {
            return studentResource.get();
        }

        throw new EntityNotFoundException("Not exist Student Resource with id: " + id);
    }

    public List<StudentResource> getAll() {
        List<StudentResource> list = studentResourceRepository.findAll();
        return list;
    }

    public void deleteById(Long id) {
        Optional<StudentResource> resource = studentResourceRepository.findById(id);

        if (resource.isPresent()) {
            studentResourceRepository.deleteById(id);
        }

        throw new EntityNotFoundException("Not exist Student Resource with id: " + id);
    }
}

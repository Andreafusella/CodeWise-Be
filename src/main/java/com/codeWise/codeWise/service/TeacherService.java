package com.codeWise.codeWise.service;

import com.codeWise.codeWise.dto.request.NewTeacherDto;
import com.codeWise.codeWise.exception.EmailExistException;
import com.codeWise.codeWise.exception.EntityNotFoundException;
import com.codeWise.codeWise.model.Teacher;
import com.codeWise.codeWise.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import java.nio.charset.StandardCharsets;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    public Teacher createTeacher(NewTeacherDto dto) {
        Optional<Teacher> existingEmail = teacherRepository.findByEmail(dto.getEmail());

        if (!existingEmail.isPresent()) {
            Teacher teacher = new Teacher(
                    dto.getName(),
                    dto.getLastName(),
                    dto.getEmail(),
                    dto.getRole()
            );

            return teacherRepository.save(teacher);
        }
        throw new EmailExistException("Email Already Exist");
    }

    public Teacher getById(Long id) {
        Optional<Teacher> teacher = teacherRepository.findById(id);
        if (teacher.isPresent()) {
            return teacher.get();
        }
        throw new EntityNotFoundException("Not exist Teacher with id: " + id);
    }

    public List<Teacher> getAll() {
        return teacherRepository.findAll();
    }

    public void deleteById(Long id) {
        Optional<Teacher> teacher = teacherRepository.findById(id);

        if (teacher.isPresent()) {
            teacherRepository.delete(teacher.get());
            return;
        }

        throw new EntityNotFoundException("Not exist Teacher with id: " + id);
    }

    public ResponseEntity<ByteArrayResource> getCsv() {
        List<Teacher> teacherList = teacherRepository.findAll();

        StringBuilder csvBuilder = new StringBuilder();

        csvBuilder.append("Name,Last Name,Email,Role\n");

        for (Teacher teacher : teacherList) {
            csvBuilder
                    .append(escape(teacher.getId().toString())).append(",")
                    .append(escape(teacher.getName())).append(",")
                    .append(escape(teacher.getLastName())).append(",")
                    .append(escape(teacher.getEmail())).append(",")
                    .append(escape(teacher.getRole())).append("\n");
        }

        byte[] csvBytes = csvBuilder.toString().getBytes(StandardCharsets.UTF_8);
        ByteArrayResource resource = new ByteArrayResource(csvBytes);

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=teachers.csv")
                .header("Content-Type", "text/csv")
                .contentLength(csvBytes.length)
                .body(resource);
    }
    private String escape(String value) {
        if (value == null) return "";
        return "\"" + value.replace("\"", "\"\"") + "\"";
    }
}
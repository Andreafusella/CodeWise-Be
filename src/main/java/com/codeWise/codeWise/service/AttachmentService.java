package com.codeWise.codeWise.service;

import com.codeWise.codeWise.dto.request.NewAttachmentDto;
import com.codeWise.codeWise.exception.EntityNotFoundException;
import com.codeWise.codeWise.model.Attachment;
import com.codeWise.codeWise.model.Course;
import com.codeWise.codeWise.repository.AttachmentRepository;
import com.codeWise.codeWise.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AttachmentService {

    @Autowired
    private AttachmentRepository attachmentRepository;

    @Autowired
    private CourseRepository courseRepository;

    public Attachment createAttachments(NewAttachmentDto dto) {
        Optional<Course> course = courseRepository.findById(dto.getIdCourse());
        if (course.isPresent()) {
            Attachment attachment = new Attachment(
                    dto.getFileName(),
                    dto.getFileUrl(),
                    dto.getType(),
                    course.get()
            );

            return attachmentRepository.save(attachment);
        }
        throw new EntityNotFoundException("Not exist Course with id: " + dto.getIdCourse());
    }

    public Attachment getById(Long id) {
        Optional<Attachment> attachment = attachmentRepository.findById(id);
        if (attachment.isPresent()) {
            return attachment.get();
        }
        throw new EntityNotFoundException("Not exist Attachment with id: " + id);
    }

    public List<Attachment> getAll() {
        return attachmentRepository.findAll();
    }

    public void deleteAttachmentById(Long id) {
        Optional<Attachment> attachment = attachmentRepository.findById(id);

        if (attachment.isPresent()) {
            attachmentRepository.deleteById(id);
            return;
        }

        throw new EntityNotFoundException("Not exist Attachment with id: " + id);
    }



}
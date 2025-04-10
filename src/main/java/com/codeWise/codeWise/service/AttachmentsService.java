package com.codeWise.codeWise.service;

import com.codeWise.codeWise.model.Attachments;
import com.codeWise.codeWise.repository.AttachmentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AttachmentsService {

    @Autowired
    private AttachmentsRepository attachmentsRepository;

    public Attachments createAttachment(Attachments attachment) {
        return attachmentsRepository.save(attachment);
    }

    public Optional<Attachments> getAttachmentById(Long id) {
        return attachmentsRepository.findById(id);
    }

    public List<Attachments> getAllAttachments() {
        return attachmentsRepository.findAll();
    }

    public boolean deleteAttachment(Long id) {
        if (attachmentsRepository.existsById(id)) {
            attachmentsRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}

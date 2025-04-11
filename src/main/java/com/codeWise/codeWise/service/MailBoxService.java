package com.codeWise.codeWise.service;

import com.codeWise.codeWise.model.MailBox;
import com.codeWise.codeWise.repository.MailBoxRepository;
import jakarta.persistence.Entity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Entity
public class MailBoxService {

    @Autowired
    private MailBoxRepository mailBoxRepository;

    public MailBox createMailBox(MailBox mailBox) {
        return mailBoxRepository.save(mailBox);
    }

    public Optional<MailBox> getMailBoxById(Long id) {
        return mailBoxRepository.findById(id);
    }

    public List<MailBox> getAllMailBoxes() {
        return mailBoxRepository.findAll();
    }

    public boolean deleteMailBox(Long id) {
        if (mailBoxRepository.existsById(id)) {
            mailBoxRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}

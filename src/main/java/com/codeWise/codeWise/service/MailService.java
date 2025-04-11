package com.codeWise.codeWise.service;

import com.codeWise.codeWise.model.Mail;
import com.codeWise.codeWise.repository.MailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MailService {

    @Autowired
    private MailRepository mailRepository;

    public Mail createMail(Mail mail) {
        return mailRepository.save(mail);
    }

    public Optional<Mail> getMailById(Long id) {
        return mailRepository.findById(id);
    }

    public List<Mail> getAllMails() {
        return mailRepository.findAll();
    }

    public boolean deleteMail(Long id) {
        if (mailRepository.existsById(id)) {
            mailRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}

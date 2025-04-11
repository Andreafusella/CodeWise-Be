package com.codeWise.codeWise.service;

import com.codeWise.codeWise.model.ReceviedEmail;
import com.codeWise.codeWise.repository.ReceviedEmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReceviedEmailService {

    @Autowired
    private ReceviedEmailRepository receviedEmailRepository;

    public ReceviedEmail createReceviedEmail(ReceviedEmail receviedEmail) {
        return receviedEmailRepository.save(receviedEmail);
    }

    public Optional<ReceviedEmail> getReceviedEmailById(Long id) {
        return receviedEmailRepository.findById(id);
    }

    public List<ReceviedEmail> getAllReceviedEmails() {
        return receviedEmailRepository.findAll();
    }

    public boolean deleteReceviedEmail(Long id) {
        if (receviedEmailRepository.existsById(id)) {
            receviedEmailRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

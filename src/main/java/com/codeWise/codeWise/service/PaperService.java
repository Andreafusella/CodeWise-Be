package com.codeWise.codeWise.service;

import com.codeWise.codeWise.model.Paper;
import com.codeWise.codeWise.repository.PaperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaperService {

    @Autowired
    private PaperRepository paperRepository;

    public Paper createPaper(Paper paper) {
        return paperRepository.save(paper);
    }

    public Optional<Paper> getPaperById(Long id) {
        return paperRepository.findById(id);
    }

    public List<Paper> getAllPapers() {
        return paperRepository.findAll();
    }

    public boolean deletePaper(Long id) {
        if (paperRepository.existsById(id)) {
            paperRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}

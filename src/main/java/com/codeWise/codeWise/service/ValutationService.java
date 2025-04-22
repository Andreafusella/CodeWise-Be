package com.codeWise.codeWise.service;

import com.codeWise.codeWise.dto.request.NewValuationDto;
import com.codeWise.codeWise.exception.EntityNotFoundException;
import com.codeWise.codeWise.model.Paper;
import com.codeWise.codeWise.model.Valuation;
import com.codeWise.codeWise.repository.PaperRepository;
import com.codeWise.codeWise.repository.ValutationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ValutationService {

    @Autowired
    private ValutationRepository valutationRepository;

    @Autowired
    private PaperRepository paperRepository;

    public Valuation create(NewValuationDto dto) {
        Optional<Paper> paper = paperRepository.findById(dto.getIdPaper());

        if (paper.isPresent()) {
            Valuation valuation = new Valuation(
                    dto.getValuation(),
                    dto.getComment(),
                    paper.get()
            );
            return valutationRepository.save(valuation);
        }
        throw new EntityNotFoundException("Not found Paper with id: " + dto.getIdPaper());
    }

    public List<Valuation> getAll() {
        return valutationRepository.findAll();
    }

    public Valuation getById(Long id) {
        Optional<Valuation> valuation = valutationRepository.findById(id);

        if (valuation.isPresent()) {
            return valuation.get();
        }
        throw new EntityNotFoundException("Not found Valuation with id: " + id);
    }

    public void deleteById(Long id) {
        Optional<Valuation> valuation = valutationRepository.findById(id);

        if (valuation.isPresent()) {
            valutationRepository.delete(valuation.get());
            return;
        }
        throw new EntityNotFoundException("Not found Valuation with id: " + id);
    }
}
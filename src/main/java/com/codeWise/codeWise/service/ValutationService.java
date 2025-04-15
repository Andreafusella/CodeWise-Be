package com.codeWise.codeWise.service;

import com.codeWise.codeWise.exception.ResourceNotFoundException;
import com.codeWise.codeWise.model.Paper;
import com.codeWise.codeWise.model.Valutation;
import com.codeWise.codeWise.repository.PaperRepository;
import com.codeWise.codeWise.repository.ValutationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ValutationService {

    @Autowired
    private ValutationRepository valutationRepository;

    @Autowired
    private PaperRepository paperRepository;


}
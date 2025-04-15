package com.codeWise.codeWise.repository;

import com.codeWise.codeWise.model.Paper;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaperRepository extends JpaRepository<Paper, Long> {
}

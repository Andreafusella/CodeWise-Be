package com.codeWise.codeWise.repository;

import com.codeWise.codeWise.model.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceRepository extends JpaRepository<Resource, Long> {
}

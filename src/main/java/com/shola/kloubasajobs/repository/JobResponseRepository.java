package com.shola.kloubasajobs.repository;

import com.shola.kloubasajobs.model.response.JobResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobResponseRepository extends JpaRepository<JobResponse, Long> {

    JobResponse findFirstByJobResponseId(Long id);

    List<JobResponse> findByJobId(Long id);
}


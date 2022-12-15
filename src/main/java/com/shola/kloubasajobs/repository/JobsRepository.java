package com.shola.kloubasajobs.repository;

import com.shola.kloubasajobs.model.job.Jobs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobsRepository extends JpaRepository<Jobs, Long> {

    List<Jobs> findByEmployerId(Long employerId);

    List<Jobs> findByJobId(Long jobId);

    Optional<Jobs> findByLocationAndIndustrialSectorAndExperienceLevel(String location,
                                                                       String industrialSector,
                                                                       String experienceLevel);

    List<Jobs> findAllByLocationOrIndustrialSectorOrExperienceLevel(String location,
                                                                     String industrialSector,
                                                                     String experienceLevel);
}
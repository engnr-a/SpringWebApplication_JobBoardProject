package com.shola.kloubasajobs.repository;

import com.shola.kloubasajobs.model.feedback.FeedBack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedBackRepository extends JpaRepository<FeedBack, Long> {
}
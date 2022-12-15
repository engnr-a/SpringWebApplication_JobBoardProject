package com.shola.kloubasajobs.repository;

import com.shola.kloubasajobs.model.employer.Employer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployerRepository extends CrudRepository<Employer, Long> {

    Employer findByUsername(String username);

    Employer findByUsernameAndEmailAddress(String username, String emailAddress);

    Employer findByEmailAddress(String emailAddress);

    Employer findFirstByUsernameOrEmailAddress(String username, String emailAddress);
}

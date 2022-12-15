package com.shola.kloubasajobs.restcontroller;

import com.shola.kloubasajobs.model.employer.Employer;
import com.shola.kloubasajobs.repository.EmployerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/allvacancies")

@RestController
public class AllVacancies {

    @Autowired
    EmployerRepository employerRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<Employer>> allStudents(){
        return ResponseEntity.ok()
                .header("Message", "Your request was successful !")
                .body(employerRepository.findAll());
    }


}
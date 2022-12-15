package com.shola.kloubasajobs.controller.jobposting;

import com.shola.kloubasajobs.model.constants.*;
import com.shola.kloubasajobs.model.employer.Employer;
import com.shola.kloubasajobs.model.job.Jobs;
import com.shola.kloubasajobs.repository.EmployerRepository;
import com.shola.kloubasajobs.repository.JobsRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * A controller class that handles the creation of <b>Jobs</b> object, its association with an <b>Employer</b> object and its persistence</br>
 * to the database.
 * @author  Shola Suleman
 * @version 0.1
 * @since   27.11.2022
 */
@Controller
@RequestMapping("/newjob")
public class JobPostingController {

    @Autowired
    private EmployerRepository employerRepository;

    @Autowired
    private JobsRepository jobsRepository;

    private static final Logger logger = LoggerFactory.getLogger(JobPostingController.class);

    List<IndustrialSector> industrialSector = Arrays.asList(IndustrialSector.values());

    List<CzechCities> czechCities = Arrays.asList(CzechCities.values());

    List<RequiredEducation> requiredEducation = Arrays.asList(RequiredEducation.values());

    List<RequiredLanguage> requiredLanguage = Arrays.asList(RequiredLanguage.values());

    List<ExperienceLevel> experienceLevel = Arrays.asList(ExperienceLevel.values());

    @ModelAttribute
    public void addIndustrialSector(Model model){
        model.addAttribute("industrialSector", industrialSector);
    }

    @ModelAttribute
    public void addCzechCities(Model model){
        model.addAttribute("cities", czechCities);
    }

    @ModelAttribute
    public void addEmployer(Model model, Authentication authentication){
        Employer employer = (Employer) authentication.getPrincipal();
        model.addAttribute("employer", employer);
    }

    @ModelAttribute
    public void addRequiredEducation(Model model){
        model.addAttribute("requiredEducation", requiredEducation);
    }

    @ModelAttribute
    public void addRequiredLanguage(Model model){
        model.addAttribute("requiredLanguage", requiredLanguage);
    }

    @ModelAttribute
    public void addExperienceLevel(Model model){
        model.addAttribute("experienceLevel", experienceLevel);
    }

    @GetMapping
    public String newJobForm(Model model){
        model.addAttribute("jobs", new Jobs());
        return "jobPostingForm";
    }

    @PostMapping
    public String createJob(@Valid Jobs jobs, Errors errors, Authentication authentication ){

        if(errors.hasErrors()){
            return "jobPostingForm";
        }

        Employer employer = (Employer) authentication.getPrincipal();

        jobs.setEmployerName(employer.getOrganizationName());
        jobs.setPostDate(new Date());
        jobs.setEmployer(employer); //This is important but easily forgotten
        jobs.setEmployerId(employer.getId());

        jobsRepository.save(jobs);

        employer.addJob(jobs);
        employerRepository.save(employer);

        logger.info(String.format("Job with id %d has been successfully created and persisted to database.", jobs.getJobId()));

        return "redirect:/userHome";

    }

}

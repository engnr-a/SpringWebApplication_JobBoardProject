package com.shola.kloubasajobs.controller;

import com.shola.kloubasajobs.model.employer.Employer;
import com.shola.kloubasajobs.model.job.Jobs;
import com.shola.kloubasajobs.model.response.JobResponse;
import com.shola.kloubasajobs.repository.EmployerRepository;
import com.shola.kloubasajobs.repository.JobResponseRepository;
import com.shola.kloubasajobs.repository.JobsRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * This controller handles several GET requests associated with employer functionalities with respect to creating vacancy postings,
 * viewing responses to posted vacancies, as well as navigations URLS within the employer home domain.
 * @author  Shola Suleman
 * @version 0.1
 * @since   27.11.2022
 */
@Controller
@RequestMapping("/userHome")
public class UserHomeController {

    private static final Logger logger = LoggerFactory.getLogger(UserHomeController.class);

    @Autowired
    private EmployerRepository employerRepository;

    @Autowired
    private JobsRepository jobsRepository;

    @Autowired
    private JobResponseRepository jobResponseRepository;

    @GetMapping
    public String userHome(Model model, Authentication authentication){

        //authentication.getPrincipal returns the authenticated employer
        Employer employer = (Employer) authentication.getPrincipal();

        if(employer != null){
            model.addAttribute("employer", employer);
        }

        return "employerHome";
    }

    @GetMapping("/myposting")
    public String myPostings(Model model, Authentication authentication) {

        Employer employer = (Employer) authentication.getPrincipal();

        model.addAttribute("myPostings",   jobsRepository.findByEmployerId(employer.getId()));
        return "employerPostedJobs.html";
    }

    @GetMapping("myposting/respoonsetomyposting")
    public String responseToMyPosting(Model model, @RequestParam("id") Long id){
        List<Jobs> jobsOptional = jobsRepository.findByJobId(id);
        model.addAttribute("actualJob" , jobsOptional);
        List<JobResponse> jobResponses = jobResponseRepository.findByJobId(id);
        model.addAttribute("jobResponses", jobResponses);
        return "responseToPosting";
    }

    @GetMapping("myposting/respoonsetomyposting/download")
    public void downloadResume(
            @RequestParam("id") Long id,
            Model model,
            HttpServletResponse response) {

        Optional<JobResponse> jobResponseOptional = jobResponseRepository.findById(id);
        //Convert the optional to actual JobResponse object

        JobResponse jobResponse = jobResponseOptional.get();

        //Define and set the contentType and the header information needed for the file to be made available for download from the page
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=" +jobResponse.getResumeFileName();
        response.setHeader(headerKey, headerValue);

        //Write the content into an output stream of the HttpServletResponse
        try(
                ServletOutputStream outputStream = response.getOutputStream();
                ){
            outputStream.write(jobResponse.getResumeFile());

        }catch(IOException ioException){
            logger.debug("Problem encountered while writing byte stream of the file into HttpServletResponse output stream.");

        }
    }

}

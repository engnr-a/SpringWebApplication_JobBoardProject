package com.shola.kloubasajobs.controller.jobresponse;

import com.shola.kloubasajobs.model.job.Jobs;
import com.shola.kloubasajobs.model.response.JobResponse;
import com.shola.kloubasajobs.repository.JobResponseRepository;
import com.shola.kloubasajobs.repository.JobsRepository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * A controller class that handles the creation of <b>JobsResponse</b> object, its association with a <b>Jobs</b> object and its persistence</br>
 * to the database. The POST request parameter <b>resumeFile</b> is of the type <b>MultipartFile</b>, and converted to the matching
 * <b>byte</b> type of the <b>JobsResponse</b> field.
 * @author  Shola Suleman
 * @version 0.1
 * @since   27.11.2022
 */
@Controller
@RequestMapping("apply")
public class JobResponseController {

    private static final Logger logger = LoggerFactory.getLogger(JobResponseController.class);

    //Field injection is not recommended, but I understand this won't be a problem given the small scope of the project
    @Autowired
    private JobsRepository jobsRepository;

    @Autowired
    private JobResponseRepository jobResponseRepository;

    //This fields will be set using the submitted data from the post request.
    @Autowired
    JobResponse jobresponse;

    @GetMapping()
    public String jobResponseForm(@RequestParam("id") Long id, Model model, HttpServletResponse response) throws Exception {
        model.addAttribute("job", jobsRepository.findByJobId(id));
        return "jobapplication";
    }

    @PostMapping
    public String uploadJobResponse(
            //@RequestParam("id") Long id,
            @RequestParam Long jobId,
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String emailAddress,
            @RequestParam MultipartFile resumeFile,
            RedirectAttributes ra
    ) {

        Optional<Jobs> jobsOptional = jobsRepository.findById(jobId);

        Jobs particularJob = jobsOptional.get();

        String fileName = StringUtils.cleanPath(resumeFile.getOriginalFilename());

        //Setting the fields of the jobResponse object to be persisted to the database.
        jobresponse.setFirstName(firstName);
        jobresponse.setLastName(lastName);
        jobresponse.setResumeFileName(fileName);
        jobresponse.setEmailAddress(emailAddress);

        byte [] submittedResume = new byte[0];

        try{
            submittedResume =resumeFile.getBytes();
        }catch(IOException ioException){
            logger.error(ioException.getLocalizedMessage(),  "%n Unable to process the submitted resume file.");
        }

        jobresponse.setResumeFile(submittedResume);
        jobresponse.setResponseDate(LocalDateTime.now());
        jobresponse.setJobs(particularJob); //This is important but easily forgotten. It completes the ManyToOne relationship in Java
        jobresponse.setJobId(particularJob.getJobId());

        particularJob.addJobResponse(jobresponse);


        /*
        There is an issue with Hibernate persistence session
         */

        jobsRepository.save(particularJob);
        logger.info(String.format("Job response with jobResponseId %d has been successfully created.", jobresponse.getJobResponseId()));
        logger.info(String.format("Job response with jobResponseId %d has been successfully created.", jobresponse.getJobResponseId()));
        //A flash message that is displayed on redirect to the homepage after successful submission of job application.
        ra.addFlashAttribute("jobResponseSuccessMessage", "Your application has been successfully submitted !");
        jobResponseRepository.save(jobresponse);



        return "redirect:/";
    }

}
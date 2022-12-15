package com.shola.kloubasajobs.controller.employer;

import com.shola.kloubasajobs.model.constants.CzechCities;
import com.shola.kloubasajobs.model.employer.Employer;
import com.shola.kloubasajobs.model.employer.EmployerRegistrationForm;
import com.shola.kloubasajobs.model.constants.IndustrialSector;
import com.shola.kloubasajobs.repository.EmployerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

/**
 * A controller class that deals with user (Employer) registration. It handles the creation of the <b>Employer</b> object,
 * as well as its persistence to the database. The <b>EmployerRegistrationForm</b> is a class that backs the <b>Employer</b> object
 * @author  Shola Suleman
 * @version 0.1
 * @since   27.11.2022
 */
@Controller
@RequestMapping("/employerRegister")
public class EmployerRegistrationController {

    public static final Logger logger = LoggerFactory.getLogger(EmployerRegistrationController.class);

    List<IndustrialSector> listOfIndustrialSector = Arrays.asList(IndustrialSector.values());

    List<CzechCities> listOfCities = Arrays.asList(CzechCities.values());

    private final EmployerRepository employerRepo;

    private final PasswordEncoder passwordEncoder;

    public EmployerRegistrationController(
            EmployerRepository employerRepo, PasswordEncoder passwordEncoder) {
        this.employerRepo = employerRepo;
        this.passwordEncoder = passwordEncoder;
    }


    @GetMapping
    public String registerEmployerForm(Model model){
        model.addAttribute("employerRegistrationForm", new EmployerRegistrationForm());
        return "employerRegForm";
    }


    @PostMapping
    public String registerFormPost(@Valid EmployerRegistrationForm employerRegistrationForm, Errors errors){

        if (errors.hasErrors()) {
            return "employerRegForm";
        }

        Employer employerCheck;

        try{
            employerCheck = employerRepo.findFirstByUsernameOrEmailAddress(employerRegistrationForm.getUsername(), employerRegistrationForm.getEmailAddress());

            if(employerCheck == null){

                employerRepo.save(employerRegistrationForm.toEmployer(passwordEncoder));
                logger.info(String.format("Employer with username %s has been successfully registered", employerRegistrationForm.getUsername()) );

            } else if (employerCheck !=null) {

                errors.rejectValue("username", "username",
                        "Either username and or email address already exist.");

                logger.info("An attempt to register with an existing email address or username.");

                return "employerRegForm";
            }


        }catch(Exception ex){
            logger.info(ex.getMessage());
            logger.info("Some error with persistence uniqueness of the Employer object.");
        }

        return "redirect:/";

    }


    //Add model czechCities enum for rendering on the registration form.
    @ModelAttribute
    public void addCzechCities(Model model){
        model.addAttribute("cities", listOfCities);
    }

    //Add model industrialSector enum for rendering on the registration form.
    @ModelAttribute
    public void addIndustrialSector(Model model){
        model.addAttribute("industrialSector", listOfIndustrialSector);
    }

}

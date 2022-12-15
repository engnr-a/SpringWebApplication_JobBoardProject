package com.shola.kloubasajobs.controller;

import com.shola.kloubasajobs.model.constants.CzechCities;
import com.shola.kloubasajobs.model.constants.ExperienceLevel;
import com.shola.kloubasajobs.model.constants.IndustrialSector;
import com.shola.kloubasajobs.model.constants.RequiredLanguage;
import com.shola.kloubasajobs.repository.JobsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.xml.datatype.Duration;
import java.util.Date;

/**
 * A controller class that handles the rendering of the homepage and its supporting models.
 * @author  Shola Suleman
 * @version 0.1
 * @since   27.11.2022
 */

@RequestMapping("/")
@Controller
public class HomeController {

    @Autowired
    private JobsRepository jobsRepository;

    @GetMapping("/")
    public String homeMapping(Model model){

        model.addAttribute("alljobs", jobsRepository.findAll());
        model.addAttribute("todayNow", new Date());
        model.addAttribute("czechCities",CzechCities.values() );
        model.addAttribute("industrialSectors", IndustrialSector.values());
        model.addAttribute("requiredLanguages", RequiredLanguage.values());
        model.addAttribute("experienceLevel", ExperienceLevel.values());

        //Conditional display of the CZK symbol doesn't work via hardcoding into the thymeleaf template..
            //Hence, the need to add it as model for evaluation conditionally

//        List<String> formattedSalaryBand = new LinkedList<>();
//        for(Jobs job : jobsRepository.findAll()){
//            if(!job.getSalaryBand().equals(null)){
//                formattedSalaryBand.add(String.format("%s, Kc", job.getSalaryBand() ));
//            }
//        }
//        model.addAttribute("salaryBands", formattedSalaryBand);

        return "home";
    }

    //Post requests on home page is handled by a different controller. See the action of the search form on the homepage.

}

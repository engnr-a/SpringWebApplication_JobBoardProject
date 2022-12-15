package com.shola.kloubasajobs.controller;

import com.shola.kloubasajobs.model.constants.CzechCities;
import com.shola.kloubasajobs.model.constants.ExperienceLevel;
import com.shola.kloubasajobs.model.constants.IndustrialSector;
import com.shola.kloubasajobs.model.constants.RequiredLanguage;
import com.shola.kloubasajobs.repository.JobsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

/**
 * This controller is used in the homepage(home.html) to pass search request parameters to the <b>JobRepository</b> . Its view(jobSearchResultView.html) is
 *  almost entirely same as the homepage view(home.html)...Only that the returned data in homepage view contains all Job object,
 *  while the JobSearchResultView contains only Job objects/models that matches the request parameters.
 * @author  Shola Suleman
 * @version 0.1
 * @since   27.11.2022
 */

@Controller
@RequestMapping("/search")
public class JobSearchController {

    @Autowired
    JobsRepository jobsRepository;

    /*
    I experimented with removing the GetMapping....since all the models was added via PostMapping(adding the models via GetMapping\
     doesn't actually add the model to the view.
    Woooow....a controller that renders a view can exist without a GetMapping...I need to read why or how this is possible.!
     */

//    @GetMapping("/returnResult")
//    public String searchResult(Model model){
//
//        return "jobSearchResultView";
//
//    }

    @PostMapping("/postSearch")
    public String postSearch(@RequestParam String location,
                             @RequestParam String sector,
                             @RequestParam String experienceLevel,
                             Model model){
        model.addAttribute("searchResult",
                jobsRepository.findAllByLocationOrIndustrialSectorOrExperienceLevel(location,sector, experienceLevel));

        model.addAttribute("todayNow", new Date()); //Used for manipulating the Date object within the model
        model.addAttribute("czechCities", CzechCities.values() );
        model.addAttribute("industrialSectors", IndustrialSector.values());
        model.addAttribute("requiredLanguages", RequiredLanguage.values());
        model.addAttribute("experienceLevel", ExperienceLevel.values());

        return "jobSearchResultView";
    }
}

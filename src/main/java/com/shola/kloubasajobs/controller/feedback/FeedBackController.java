package com.shola.kloubasajobs.controller.feedback;


import com.shola.kloubasajobs.model.constants.IndustrialSector;
import com.shola.kloubasajobs.model.feedback.FeedBack;
import com.shola.kloubasajobs.repository.FeedBackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping("/feedback")

public class FeedBackController {

    @Autowired
    FeedBackRepository feedBackRepository;


    @GetMapping
    public String displayForm(Model model){
            model.addAttribute("professionalSector", IndustrialSector.values());
            return "feedBackForm";
    }


    @PostMapping
    public String submitForm(@RequestParam String name,
                             @RequestParam String emailAddress,
                             @RequestParam String professionalSector,
                             @RequestParam String feedBackComment){
        FeedBack feedBack = new FeedBack();
        feedBack.setName(name);
        feedBack.setEmailAddress(emailAddress);
        feedBack.setProfessionalSector(professionalSector);
        feedBack.setFeedBackComment(feedBackComment);
        feedBack.setDateCreated(new Date());

        feedBackRepository.save(feedBack);
        return "redirect:/";

    }

    @ModelAttribute
    public void addFeedBack(Model model){
        model.addAttribute("submittedFeedBack", new FeedBack());
    }



}

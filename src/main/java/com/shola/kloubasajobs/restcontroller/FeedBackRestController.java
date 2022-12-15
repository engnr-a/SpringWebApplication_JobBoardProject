package com.shola.kloubasajobs.restcontroller;

import com.shola.kloubasajobs.model.feedback.FeedBack;
import com.shola.kloubasajobs.repository.FeedBackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;

@RestController
@RequestMapping("/feedBackREST")
public class FeedBackRestController {
    @Autowired
    FeedBackRepository feedBackRepository;

    @GetMapping(value = "show", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<FeedBack>> allFeedBacks(){
        return ResponseEntity.ok().header("Message", "Thank you!").body(feedBackRepository.findAll());
    }

}

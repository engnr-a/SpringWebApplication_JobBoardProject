package com.shola.kloubasajobs.model.job;

import com.shola.kloubasajobs.model.employer.Employer;
import com.shola.kloubasajobs.model.response.JobResponse;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * A class that models a real world vacancy that can be posted on a job board.
 * @author  Shola Suleman
 * @version 0.1
 * @since   27.11.2022
 */

@Data
@NoArgsConstructor(force = true)
@Entity
@Table(name = "jobs")
@Validated
public class Jobs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="jobId")
    private Long jobId;

    @NotBlank(message = "Please enter the job title")
    @Column(name = "title")
    private String title;

    @NotBlank(message = "Please enter job description")
    @Column(name = "jobdescription")
    private String jobDescription;

    @Column(name="requirededucation")
    private String requiredEducation;

    @Column(name = "requiredlanguage")
    private String requiredLanguage;

    //@NotBlank(message = "Please select a category")
    @Column(name = "industrialSector")
    private String industrialSector;

    //@NotBlank(message = "Please chose a location")
    @Column(name = "location")
    private String location;

    @Column(name = "salaryband")
    private String salaryBand;

    @Column(name = "experiencelevel")
    private String experienceLevel;

    @Column(name = "postdate")
    private Date postDate;

    @Column(name = "employerid", insertable = false, updatable = false, nullable = false)
    private Long employerId;

    @Column(name = "employername")
    private String employerName;

    @ManyToOne
    @JoinColumn(name = "employerid") //NOTE: This refers to the columns within the employer table itself.
    private Employer employer;

    @OneToMany(mappedBy = "jobs", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Collection<JobResponse> jobResponseCollection = new ArrayList<>();

    public void addJobResponse(JobResponse jobResponse){
        jobResponseCollection.add(jobResponse);
    }

}

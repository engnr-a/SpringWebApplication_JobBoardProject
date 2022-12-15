package com.shola.kloubasajobs.model.response;

import com.shola.kloubasajobs.model.job.Jobs;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * A class that models a real world response/reply/application to a posted job vacancy on a job board.
 * @author  Shola Suleman
 * @version 0.1
 * @since   27.11.2022
 */
@Data
//@AllArgsConstructor
@Entity
//@Validated
@NoArgsConstructor(access = AccessLevel.PUBLIC, force = true)
@AllArgsConstructor
@Component
public class JobResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jobResponseId;

    //@NotBlank(message = "Please enter your first name")
    private String firstName;

    //@NotBlank(message = "Please enter your last name")
    private String lastName;

    //@Email(message = "Please enter a valid email", regexp = ".+\\@.+\\..+")
    private String emailAddress;
    @Column(name = "fileName", nullable = false, unique = true)
    private String resumeFileName;

    @Lob
    //@NotNull(message = "Kindly WOW the employer with your resume !")
    private byte[] resumeFile;

    private LocalDateTime responseDate;

    @Column(name="jobId", insertable = false, updatable = false, nullable = false)
    private Long jobId;

    @ManyToOne
    @JoinColumn(name = "jobId")
    private Jobs jobs;

}

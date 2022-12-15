//package com.shola.kloubasajobs.model.response;
//
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.validation.constraints.Email;
//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.NotEmpty;
//import javax.validation.constraints.NotNull;
//
//
//@Data
//@NoArgsConstructor(force = true)
//@Validated
//public class JobResponseForm {
//
//    @NotBlank(message = "Please enter your first name NOTBLANK ")
//    //@NotNull(message = "Please enter your first name NOTNULL")
//    private String firstName;
//
//    @NotBlank(message = "Please enter your last name NOTBLANK")
//    private String lastName;
//
//    @Email(message = "Please enter a valid email", regexp = ".+\\@.+\\..+")
//    private String emailAddress;
//
//
//    @NotEmpty (message = "Kindly wow the employer with your resume !")
//    @NotNull (message = "Kindly wow the employer with your resume !")
//    private MultipartFile multipartFileSubmitted;
//
//
//
//}

package com.shola.kloubasajobs.model.employer;

import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.Date;

/**
 * A form-backing class that with corresponding fields of the <b>Employer</b> object and a method for creating an <b>Employer</b>
 * from a submitted data from a POST request form.
 * @author  Shola Suleman
 * @version 0.1
 * @since   27.11.2022
 */

@Data
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Validated
@Component
public class EmployerRegistrationForm {

    @Size(min=4,  message = "Please chose a valid username")
    private String username;

    @Size(min=8, message="Invalid password. C'mon take security seriously!")
    private String password;

    @Valid
    private String confirmPassword;

    @Email(message = "Invalid email address",
            //WOW...The dark at of regex. I should check for a shorter email validation regex.
            regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")

    private String emailAddress;

    @NotBlank(message = "Organization name should not be empty")
    private String organizationName;

    @NotNull(message = "Please chose a sector")
    private String industrialSector;


    //Registered Office Address
    @NotNull(message = "Please enter street address of your organization")
    private String street;

    @NotNull(message = "Please chose a city")
    @Valid
    private String city;

    @Size(min = 5,  message="Minimum of 5 numbers")
    @Digits(integer=5, fraction=0, message="Please enter a czech zip code. 5 digits without space")
    private String zip;

    @Digits(integer=10, fraction=0, message="Please specify a 10 digit phone number")
    @Size(min = 10,  message="Minimum of 10 digits")
    private String phone;

    @NotBlank (message = "Kindly agree that this is not a real company.")
    private String dataResponsibility;

    public final Date accountCreationDate;

    //A creation pattern for creating an Employer object from the EmployerRegistration form object
    public Employer toEmployer(PasswordEncoder passwordEncoder) {

        Employer employer = new Employer();
        employer.setUsername(username);
        employer.setPassword(passwordEncoder.encode(password));
        employer.setEmailAddress(emailAddress);
        employer.setOrganizationName(organizationName);
        employer.setIndustrialSectors(industrialSector);
        employer.setStreet(street);
        employer.setCity(city);
        employer.setZip(zip);
        employer.setPhone(phone);
        employer.setAccountCreationDate(new Date());

        return  employer;
    }

}
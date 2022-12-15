package com.shola.kloubasajobs.model.employer;

import com.shola.kloubasajobs.model.job.Jobs;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.*;

/**
 * A class that models an Employer with fields resembling common information particular to an organization.<br/>
 * The class implements <b>UserDetails</b> interface from Spring Security framework.
 * @author  Shola Suleman
 * @version 0.1
 * @since   27.11.2022
 */
@Data
//@XmlRootElement
@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PUBLIC, force = true)
@AllArgsConstructor
public class Employer implements UserDetails {


    /*
    Basic information
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String emailAddress;

    /*
    Organizational Profile
     */
    private String organizationName;

    private String industrialSectors;

    //Registered Office Address
    private String street;

    private String city;

    private String zip;

    private String phone;

    private Date accountCreationDate;

    @OneToMany(mappedBy = "employer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    //"employer" refers to the field within the Jobs class
    private Collection<Jobs> jobsCollection = new ArrayList<>();

    public void addJob(Jobs jobs){
        jobsCollection.add(jobs);
    }

    /*
    Overridden methods of the implemented 'UserDetails' interface
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       return Arrays.asList(new SimpleGrantedAuthority("ROLE_EMPLOYER"));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
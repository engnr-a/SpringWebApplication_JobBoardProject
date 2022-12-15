package com.shola.kloubasajobs.model.constants;
/**
 * Container for required education level.
 * @author  Shola Suleman
 * @version 0.1
 * @since   27.11.2022
 */
public enum RequiredEducation {

    NotRequired("Not Required"),
    HighSchoolDiplomaOrEquivalent("High School Diploma or Equivalent"),
    College("College"),
    Apprenticeship("Apprenticeship"),
    BachelorsDegree("Bachelor's Degree"),
    MastersDegree("Master's Degree"),
    DoctoralDegree("Doctoral Degree");

    private String name;
    RequiredEducation(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    @Override
    public String toString(){
        return name;
    }
}

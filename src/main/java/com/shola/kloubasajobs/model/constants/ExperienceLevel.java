package com.shola.kloubasajobs.model.constants;

/**
 * Container for work experience
 * @author  Shola Suleman
 * @version 0.1
 * @since   27.11.2022
 */
public enum ExperienceLevel {
    EntryLevel("Entry Level"),
    Intermediate("Intermediate Level"),
    MidLevel("Mid Level"),
    SeniorLevel("Senior Level"),
    ExecutiveLevel("Executive Level");

    private String name;

    ExperienceLevel(String name){
        this.name =name;
    }

    public String getName(){
        return this.name;
    }

    @Override
    public String toString(){
        return this.name;
    }
}

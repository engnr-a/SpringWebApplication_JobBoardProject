package com.shola.kloubasajobs.model.constants;


import org.springframework.validation.annotation.Validated;

/**
 * Container for economic sectors.
 * @author  Shola Suleman
 * @version 0.1
 * @since   27.11.2022
 */
@Validated
public enum IndustrialSector {

    Mining_And_Agriculture("Mining and Agriculture"),
    Manufacturing("Manufacturing"),
    Construction("Construction"),
    Utilities("Utilities"),
    TransportationAndDistribution("Transport and Distribution"),
    Retail("Retail"),
    RealEstate("Real Estate"),
    FinancialServices("Financial Services"),
    LegalServices("Legal Services"),
    HealthcareServices("Healthcare Services"),
    TourismAndHospitality("Tourism and Hospitality"),
    Communication("Communication"),
    InformationTechnology("Information Technology (IT)"),
    Education("Education"),
    ResearchAndDevelopment("Research and Development"),
    PublicSector("Public Sector"),
    NonGovernmentalOrganization("Non-governmental Organization");

    private String name;

    IndustrialSector(String name){
            this.name =name;
    }

    public String getName() {
        return this.name;
    }
    @Override public String toString() { return name; }

}

package com.shola.kloubasajobs.model.constants;

import org.springframework.validation.annotation.Validated;

/**
 * Container for cities within Czechia.
 * @author  Shola Suleman
 * @version 0.1
 * @since   27.11.2022
 */
@Validated
public enum CzechCities {

    Prague ("Prague"),
    Brno("Brno"),
    Ostrava("Ostrava"),
    Plzeň("Plzen"),
    Liberec("Liberec"),
    Olomouc("Olomouc"),
    ČeskéBudějovice("České Budějovice"),
    HradecKrálové("Hradec Králové"),
    ÚstínadLabem("Ústí nad Labem"),
    Pardubice("Pardubice"),
    Zlín("Zlín"),
    Havířov("Havířov"),
    Kladno("Kladno"),
    Most("Most"),
    Opava("Opava"),
    FrýdekMístek("Frýdek-Místek"),
    Jihlava("Jihlava"),
    Karviná("Karviná"),
    Teplice("Teplice"),
    Děčín("Děčín"),
    Chomutov("Chomutov"),
    KarlovyVary("Karlovy Vary"),
    JablonecnadNisou("Jablonec nad Nisou"),
    Prostějov("Prostějov");

    String czechCities;
    CzechCities(String czechCities){
     this.czechCities = czechCities;
    }

    @Override public String toString() { return czechCities; }






    }




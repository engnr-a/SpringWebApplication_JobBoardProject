package com.shola.kloubasajobs.model.constants;

/**
 * Container for required languages.
 * @author  Shola Suleman
 * @version 0.1
 * @since   27.11.2022
 */
public enum RequiredLanguage {
    Yoruba("Yoruba Language"),
    English("English Language"),
    German("German Language"),
    French("French Language"),
    Czech("Czech Language"),
    Polish("Polish Language"),
    Arabic("Arabic Language");

    private String name;

    RequiredLanguage(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    @Override
    public String toString(){
        return this.name;
    }


}

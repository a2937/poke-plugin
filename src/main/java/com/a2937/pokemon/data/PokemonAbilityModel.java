package com.a2937.pokemon.data;

public class PokemonAbilityModel
{
    private String desc;
    private String shortDesc;
    private String name;
    private float rating;


    public String getDesc() {
        return desc;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public String getName() {
        return name;
    }

    public float getRating() {
        return rating;
    }

    // Setter Methods

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

}

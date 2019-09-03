package com.avairebot.pokemon.data;

import java.util.Arrays;
import java.util.HashMap;

public class PokemonModel
{
    @Override
    public String toString() {
        return "PokemonModel{" +
                "num=" + num +
                ", name='" + name + '\'' +
                ", Species='" + species + '\'' +
                ", Types=" + Arrays.toString(types) +
                ", GenderRatio=" + genderRatio +
                ", BaseStats=" + baseStats +
                ", Abilities=" + abilities +
                ", HeightM=" + heightm +
                ", WeightKg=" + weightkg +
                ", Color='" + color + '\'' +
                ", Evos=" + Arrays.toString(evos) +
                ", EggGroups=" + Arrays.toString(eggGroups) +
                '}';
    }

    public String getAbilitiesFormatted()
    {
        StringBuilder stringBuilder = new StringBuilder();
        abilities.forEach((key, value) -> stringBuilder.append(value).append("\n"));
        return stringBuilder.toString();
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public void setTypes(String[] types) {
        this.types = types;
    }

    public void setGenderRatio(GenderRatio genderRatio) {
        this.genderRatio = genderRatio;
    }

    public void setBaseStats(BaseStats baseStats) {
        this.baseStats = baseStats;
    }

    public void setAbilities(HashMap<String, String> abilities) {
        this.abilities = abilities;
    }


    public void setHeightm(float heightm) {
        this.heightm = heightm;
    }

    public void setWeightkg(float weightkg) {
        this.weightkg = weightkg;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setEvos(String[] evos) {
        this.evos = evos;
    }

    public void setEggGroups(String[] eggGroups) {
        this.eggGroups = eggGroups;
    }

    private int num;
    private String name;
    private String species ;
    private String[] types ;
    private GenderRatio genderRatio ;
    private BaseStats baseStats ;
    private HashMap<String, String> abilities ;
    private float heightm;
    private float weightkg;
    private String color;
    private String[] evos;
    private String[] eggGroups;

    public int getNum() {
        return num;
    }

    public String getName() {
        return name;
    }

    public String getSpecies() {
        return species;
    }

    public String[] getTypes() {
        return types;
    }

    public GenderRatio getGenderRatio() {
        return genderRatio;
    }

    public BaseStats getStats() {
        return baseStats;
    }

    public HashMap<String, String> getAbilities() {
        return abilities;
    }

    public float getHeightm() {
        return heightm;
    }

    public float getWeightkg() {
        return weightkg;
    }

    public String getColor() {
        return color;
    }

    public String[] getEvos() {
        return evos;
    }

    public String[] getEggGroups() {
        return eggGroups;
    }

    public class GenderRatio {
        private float M;
        private float F;


        // Getter Methods

        public float getM() {
            return M;
        }

        public float getF() {
            return F;
        }

        // Setter Methods

        public void setM(float M) {
            this.M = M;
        }

        public void setF(float F) {
            this.F = F;
        }
    }

    public class BaseStats {
        private float hp;
        private float atk;
        private float def;
        private float spa;
        private float spd;
        private float spe;


        // Getter Methods

        public float getHp() {
            return hp;
        }

        public float getAttack() {
            return atk;
        }

        public float getDefense() {
            return def;
        }

        public float getSpecialAttack() {
            return spa;
        }

        public float getSpecialDefense() {
            return spd;
        }

        public float getSpeed() {
            return spe;
        }

        // Setter Methods

        public void setHp(float hp) {
            this.hp = hp;
        }

        public void setAttack(float atk) {
            this.atk = atk;
        }

        public void setDefense(float def) {
            this.def = def;
        }

        public void setSpecialAttack(float spa) {
            this.spa = spa;
        }

        public void setSpecialDefense(float spd) {
            this.spd = spd;
        }

        public void setSpeed(float spe) {
            this.spe = spe;
        }

        @Override
        public String toString()
        {
            StringBuilder builder = new StringBuilder();
            builder.append("\uD83D\uDC9A**HP:**").append("\t").append(hp).append("\n");
            builder.append("\u2694**ATK:**").append("\t").append(atk).append("\n");
            builder.append("\uD83D\uDEE1**DEF:**").append("\t").append(def).append("\n");
            builder.append("\u2728**SPA:**").append("\t").append(spa).append("\n");
            builder.append("\u2728**SPD:**").append("\t").append(spd).append("\n");
            builder.append("\uD83D\uDCA8**SPE:**").append("\t").append(spe).append("\n");


            return builder.toString();
        }
    }

}

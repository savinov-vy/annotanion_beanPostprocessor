package ru.savinov.springcontext;

import ru.savinov.springcontext.anotation.CalculateCatStrength;

public class CombatCat {

    private String name;

    @CalculateCatStrength(minStrenght = 4, maxStrenght = 10)
    private Integer strength;

    public String getName() {
        return name;
    }

    public void setName(java.lang.String name) {
        this.name = name;
    }

    public Integer getStrength() {
        return strength;
    }

    public void setStrength(Integer strength) {
        this.strength = strength;
    }

    @Override
    public String toString() {
        return "CombatCat{" +
                "name='" + name + '\'' +
                ", strength=" + strength +
                '}';
    }
}

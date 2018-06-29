package com.mrb.swingy.model.character;

/**
 * Created by chvs on 19.06.2018.
 */
public abstract class HeroFactory {

    public static Hero newHero(String name, String heroClass) {
        switch (heroClass.toUpperCase()) {
            case "WARRIOR":
                return Director.createWarrior(name);
            case "SHAMAN":
                return Director.createShaman(name);
            case "PRIEST":
                return Director.createPriest(name);
            case "PALADIN":
                return Director.createPaladin(name);
            case "MAGE":
                return Director.createMage(name);
            case "HUNTER":
                return Director.createHunter(name);
            default:
                throw new IllegalArgumentException("Invalid hero class: " + heroClass);
        }
    }
}

package com.mrb.swingy.model.character;

public abstract class HeroFactory {

    public static Hero newHero(String name, String heroClass) {
        return switch (heroClass.toUpperCase()) {
            case "WARRIOR" -> Director.createWarrior(name);
            case "SHAMAN" -> Director.createShaman(name);
            case "PRIEST" -> Director.createPriest(name);
            case "PALADIN" -> Director.createPaladin(name);
            case "MAGE" -> Director.createMage(name);
            case "HUNTER" -> Director.createHunter(name);
            default -> throw new IllegalArgumentException("Invalid hero class: " + heroClass);
        };
    }
}

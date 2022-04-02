package com.mrb.swingy.model.character;

public abstract class HeroFactory {

    public static Hero newHero(String name, String heroClass) {
        return switch (heroClass.toUpperCase()) {
            case "WARRIOR" -> createWarrior(name);
            case "SHAMAN" -> createShaman(name);
            case "PRIEST" -> createPriest(name);
            case "PALADIN" -> createPaladin(name);
            case "MAGE" -> createMage(name);
            case "HUNTER" -> createHunter(name);
            default -> throw new IllegalArgumentException("Invalid hero class: " + heroClass);
        };
    }

    private static Hero create(String name, int atack, int def, int points, String clazz) {
        return Hero.builder().name(name).attack(atack).defense(def).hitPoints(points).heroClass(clazz).build();
    }

    private static Hero createWarrior(String name) {
        return create(name, 40, 20, 100, "Warrior");
    }

    private static Hero createShaman(String name) {
        return create(name, 30, 15, 90, "Shaman");
    }

    private static Hero createPriest(String name) {
        return create(name, 25, 25, 90, "Priest");
    }

    private static Hero createPaladin(String name) {
        return create(name, 40, 30, 120, "Paladin");
    }

    private static Hero createMage(String name) {
        return create(name, 45, 10, 80, "Mage");

    }

    private static Hero createHunter(String name) {
        return create(name, 25, 20, 110, "Hunter");
    }
}

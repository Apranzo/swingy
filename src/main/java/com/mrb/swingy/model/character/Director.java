package com.mrb.swingy.model.character;

/**
 * Created by chvs on 19.06.2018.
 */
public class Director {

    private static Hero create(String name, int atack, int def, int points, String clazz) {
        return Hero.builder().name(name).attack(atack).defense(def).hitPoints(points).heroClass(clazz).build();
    }
    public static Hero createWarrior(String name) {
        return create(name, 40, 20, 100, "Warrior");
    }

    public static Hero createShaman(String name) {
        return create(name, 30, 15, 90, "Shaman");
    }

    public static Hero createPriest(String name) {
        return create(name, 25, 25, 90, "Priest");
    }

    public static Hero createPaladin(String name) {
        return create(name, 40, 30, 120, "Paladin");
    }

    public static Hero createMage(String name) {
        return create(name, 45, 10, 80, "Mage");

    }

    public static Hero createHunter(String name) {
        return create(name, 25, 20, 110, "Hunter");
    }
}

package com.mrb.swingy.model.character;

import com.mrb.swingy.model.artifact.Armor;
import com.mrb.swingy.model.artifact.Helm;
import com.mrb.swingy.model.artifact.Weapon;

/**
 * Created by chvs on 18.06.2018.
 */
public class Hero extends Character {

    private Weapon weapon;
    private Armor armor;
    private Helm helm;

    private int level;
    private int experience;
    private String heroClass;

    public Hero(String name, int attack, int defense, int hitPoints, String heroClass,
                int level, int experience, Weapon weapon, Armor armor, Helm helm) {
        super(name, attack, defense, hitPoints);
        this.weapon = weapon;
        this.armor = armor;
        this.helm = helm;
        this.level = level;
        this.experience = experience;
        this.heroClass = heroClass;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Armor getArmor() {
        return armor;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }

    public Helm getHelm() {
        return helm;
    }

    public void setHelm(Helm helm) {
        this.helm = helm;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public String getHeroClass() {
        return heroClass;
    }

    public void setHeroClass(String heroClass) {
        this.heroClass = heroClass;
    }
}

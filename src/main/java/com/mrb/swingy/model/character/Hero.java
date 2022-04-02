package com.mrb.swingy.model.character;

import com.mrb.swingy.exception.HeroValidationException;
import com.mrb.swingy.model.artifact.Armor;
import com.mrb.swingy.model.artifact.Helm;
import com.mrb.swingy.model.artifact.Weapon;
import lombok.Builder;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Data
public class Hero extends Character {

    private Weapon weapon;
    private Armor armor;
    private Helm helm;

    private int level;

    private int experience;

    private String heroClass;

    private int id;

    @Builder
    public Hero(String name, int attack, int defense, int hitPoints, int id, String heroClass,
                int level, int experience, Weapon weapon, Armor armor, Helm helm) {
        super(name, attack, defense, hitPoints);
        this.id = id;
        this.weapon = weapon;
        this.armor = armor;
        this.helm = helm;
        this.level = level;
        this.experience = experience;
        this.heroClass = heroClass;
    }

    private List<String> validate() {
        List<String> err = new LinkedList<>();
        if (level < 0) err.add("Level should not be less than 0");
        if (experience < 0) err.add("Experience should not be less than 0");
        if (heroClass == null) err.add("Hero class cannot be null");
        if (name == null) err.add("Name cannot be null");
        else if (name.length() < 2 || name.length() > 16) err.add("");
        if (attack < 0) err.add("Attack should not be less than 0");
        if (defense < 0) err.add("Defense should not be less than 0");
        if (hitPoints < 1) err.add("Hit points should not be less than 1");
        return err;
    }

    public Hero validateHero() throws HeroValidationException {

        List<String> err = validate();
        if (err.size() != 0) {
            StringBuilder sb = new StringBuilder();
            sb.append(String.format("Hero validation error(s): %d%n", err.size()));
//            sb.append(err.size());
//            sb.append("\n");
            for (String s : err) {
                sb.append(String.format("message: %s%n", s));
//                sb.append("property: [");
//                sb.append(cv.getPropertyPath());
//                sb.append("], value: [");
//                sb.append(cv.getInvalidValue());
//                sb.append("], message: [");
//                sb.append(cv.getMessage());
//                sb.append("]\n");
            }
            throw new HeroValidationException(sb.toString());
        }
        return this;
    }

    public void equipWeapon(Weapon weapon) {
        if (this.weapon != null) {
            this.attack -= this.weapon.getPoints();
        }
        this.attack += weapon.getPoints();
        this.weapon = weapon;
    }

    public void equipHelm(Helm helm) {
        if (this.helm != null) {
            this.hitPoints -= this.helm.getPoints();
            if (this.hitPoints + helm.getPoints() <= 0) {
                this.hitPoints += this.helm.getPoints();
                return;
            }
        }
        this.hitPoints += helm.getPoints();
        this.helm = helm;
    }

    public void equipArmor(Armor armor) {
        if (this.armor != null) {
            this.defense -= this.armor.getPoints();
        }
        this.defense += armor.getPoints();
        this.armor = armor;
    }

    public void addExperience(int addXP) {
        int nextLevel = (level + 1) * 1000 + level * level * 450;

        if (experience + addXP >= nextLevel)
            levelUp();
        experience += addXP;
    }

    private void levelUp() {
        level++;
        hitPoints += 50 + level * 10;
        attack += level * 3;
        defense += level * 2;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(name).append("\n");
        sb.append("Class: ").append(heroClass).append("\n");
        sb.append("Level: ").append(level).append("\n");
        sb.append("XP: ").append(experience).append("\n");
        sb.append("Attack: ").append(attack).append("\n");
        sb.append("Defense: ").append(defense).append("\n");
        sb.append("HP: ").append(hitPoints).append("\n");

        sb.append("Weapon: ");
        if (weapon != null)
            sb.append(weapon.getName()).append(" (attack +").append(weapon.getPoints()).append(")\n");
        else
            sb.append(" no weapon\n");

        sb.append("Helm: ");
        if (helm != null)
            sb.append(helm.getName()).append(" (hp +").append(helm.getPoints()).append(")\n");
        else
            sb.append(" no helmet\n");

        sb.append("Armor: ");
        if (armor != null)
            sb.append(armor.getName()).append(" (defense +").append(armor.getPoints()).append(")\n");
        else
            sb.append(" no armor\n");
        return sb.toString();
    }
}

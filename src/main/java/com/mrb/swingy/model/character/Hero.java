package com.mrb.swingy.model.character;

import com.mrb.swingy.exception.HeroValidationException;
import com.mrb.swingy.model.artifact.Armor;
import com.mrb.swingy.model.artifact.Helm;
import com.mrb.swingy.model.artifact.Weapon;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;
//import lombok.*;

import javax.validation.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.logging.Level;

/**
 * Created by chvs on 18.06.2018.
 */
@SuperBuilder
@Data
public class Hero extends Character {

    private Weapon weapon;
    private Armor armor;
    private Helm helm;

    @Min(value = 0, message = "Level should not be less than 0")
    private int level;

    @Min(value = 0, message = "Experience should not be less than 0")
    private int experience;

    @NotNull(message = "Hero class cannot be null")
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

    public void validateHero() throws HeroValidationException {
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<Hero>> constraintViolations = validator.validate(this);
        if (constraintViolations.size() != 0) {
            StringBuilder sb = new StringBuilder();
            sb.append(String.format("Hero validation error(s): {}\n", constraintViolations.size()));
//            sb.append(constraintViolations.size());
//            sb.append("\n");
            for (ConstraintViolation<Hero> cv : constraintViolations) {
                sb.append(String.format("property: {}, value: {}, message: {}\n",
                                cv.getPropertyPath(), cv.getInvalidValue(), cv.getMessage()));
//                sb.append("property: ["); // TODO format string
//                sb.append(cv.getPropertyPath());
//                sb.append("], value: [");
//                sb.append(cv.getInvalidValue());
//                sb.append("], message: [");
//                sb.append(cv.getMessage());
//                sb.append("]\n");
            }
            throw new HeroValidationException(sb.toString());
        }
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

//    @Override
//    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        sb.append("Name: ").append(name).append("\n");
//        sb.append("Class: ").append(heroClass).append("\n");
//        sb.append("Level: ").append(level).append("\n");
//        sb.append("XP: ").append(experience).append("\n");
//        sb.append("Attack: ").append(attack).append("\n");
//        sb.append("Defense: ").append(defense).append("\n");
//        sb.append("HP: ").append(hitPoints).append("\n");
//
//        sb.append("Weapon: ");
//        if (weapon != null)
//            sb.append(weapon.getName()).append(" (attack +").append(weapon.getPoints()).append(")\n");
//        else
//            sb.append(" no weapon\n");
//
//        sb.append("Helm: ");
//        if (helm != null)
//            sb.append(helm.getName()).append(" (hp +").append(helm.getPoints()).append(")\n");
//        else
//            sb.append(" no helmet\n");
//
//        sb.append("Armor: ");
//        if (armor != null)
//            sb.append(armor.getName()).append(" (defense +").append(armor.getPoints()).append(")\n");
//        else
//            sb.append(" no armor\n");
//        return sb.toString();
//    }
}

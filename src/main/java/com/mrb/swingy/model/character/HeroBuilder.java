package com.mrb.swingy.model.character;

import com.mrb.swingy.model.artifact.Armor;
import com.mrb.swingy.model.artifact.Helm;
import com.mrb.swingy.model.artifact.Weapon;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by chvs on 19.06.2018.
 */
@Setter
public class HeroBuilder {
    private int id;
    private String name;
    private int attack;
    private int defense;
    private int hitPoints;
    private String heroClass;
    private int level;
    private int experience;
    private Weapon weapon;
    private Armor armor;
    private Helm helm;

    public HeroBuilder(String name, int level, int experience) {
        this.name = name;
        this.level = level;
        this.experience = experience;
    }
    public Hero getHero() {
        return new Hero(name, attack, defense, hitPoints, id, heroClass, level, experience, weapon, armor, helm);
    }
}

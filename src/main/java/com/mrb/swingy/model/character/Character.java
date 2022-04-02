package com.mrb.swingy.model.character;

import com.mrb.swingy.util.Utils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


/**
 * Created by chvs on 18.06.2018.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder
public abstract class Character {

    protected String name;
    protected int attack;
    protected int defense;
    protected int hitPoints;

    private void attack(Character opponent) {
        if (this.attack > opponent.defense) {
            opponent.takeDamage(attack - defense);
        } else if (Utils.getRandom(0, 10) <= 2) {
            opponent.takeDamage(attack);
        }
    }

    public void takeDamage(int atack) {
        setHitPoints(hitPoints - Math.max(0, atack));
    }

    public boolean fight(Character opponent) {
        while (opponent.getHitPoints() > 0 && this.getHitPoints() > 0) {
            this.attack(opponent);
            opponent.attack(this);
        }
        return this.getHitPoints() > 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }
}

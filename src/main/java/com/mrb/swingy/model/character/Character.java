package com.mrb.swingy.model.character;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by chvs on 18.06.2018.
 */
public abstract class Character {

    @NotNull(message = "Name cannot be null")
    @Size(min = 2, message = "Name length should not be less than 2")
    protected String name;

    @Min(value = 0, message = "Attack should not be less than 0")
    protected int attack;

    @Min(value = 0, message = "Defense should not be less than 0")
    protected int defense;

    @Min(value = 1, message = "Hit points should not be less than 1")
    protected int hitPoints;

    public Character(String name, int attack, int defense, int hitPoints) {
        this.name = name;
        this.attack = attack;
        this.defense = defense;
        this.hitPoints = hitPoints;
    }

    private void attack(Character opponent){
        if (this.attack > opponent.defense){
            System.out.println(opponent.getName() + " attacked");
            opponent.setHitPoints(opponent.getHitPoints() - (this.attack - opponent.defense));
        }
    }

    public boolean fight(Character opponent){
        while (opponent.getHitPoints() > 0 && this.getHitPoints() > 0){
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

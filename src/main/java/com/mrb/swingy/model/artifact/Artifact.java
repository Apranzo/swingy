package com.mrb.swingy.model.artifact;

/**
 * Created by chvs on 18.06.2018.
 */
public abstract sealed class Artifact permits Armor, Helm, Weapon{

    private int points;
    protected String name;

    public Artifact(String name, int points) {
        this.name = name;
        this.points = points;
    }

    public int getPoints() {
        return points;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + " (+" + points + ")";
    }
}

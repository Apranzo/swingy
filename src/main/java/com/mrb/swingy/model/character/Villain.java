package com.mrb.swingy.model.character;

import com.mrb.swingy.model.artifact.Artifact;

/**
 * Created by chvs on 18.06.2018.
 */
public class Villain extends Character {

    private Artifact artifact;

    public Villain(String name, int attack, int defense, int hitPoints, Artifact artifact) {
        super(name, attack, defense, hitPoints);
        this.artifact = artifact;
    }
}

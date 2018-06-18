package com.mrb.swingy.model.character;

import com.mrb.swingy.model.artifact.Armor;
import com.mrb.swingy.model.artifact.Helm;
import com.mrb.swingy.model.artifact.Weapon;

/**
 * Created by chvs on 18.06.2018.
 */
public abstract class Hero extends Character {

    protected Weapon weapon;
    protected Armor armor;
    protected Helm helm;

    protected int level;
    protected int experience;
}

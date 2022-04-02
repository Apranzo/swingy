package com.mrb.swingy.model.artifact;

import com.mrb.swingy.model.character.Hero;
import com.mrb.swingy.view.game.GameView;

/**
 * Created by chvs on 18.06.2018.
 */
public class Weapon extends Artifact {

    public Weapon(String name, int points) {
        super(name, points);
    }

    @Override
    public void takeMe(Hero h, GameView view) {
        h.equipWeapon(this);
        view.showMessage("You equipped new weapon: " + this);
    }

    @Override
    public boolean itIs(Hero h) {
        return h.getWeapon() != null;
    }
}

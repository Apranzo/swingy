package com.mrb.swingy.model.artifact;

import com.mrb.swingy.model.character.Hero;
import com.mrb.swingy.view.game.GameView;

/**
 * Created by chvs on 18.06.2018.
 */
public class Helm extends Artifact {

    public Helm(String name, int points) {
        super(name, points);
    }

    @Override
    public void takeMe(Hero h, GameView view) {
        h.equipHelm(this);
        view.showMessage("You equipped new helm: " + this);
    }

    @Override
    public boolean itIs(Hero h) {
        return null != h.getHelm();
    }
}

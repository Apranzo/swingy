package com.mrb.swingy.model.artifact;

import com.mrb.swingy.model.character.Hero;
import com.mrb.swingy.view.game.GameView;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class Artifact {

    protected String name;
    private int points;

    public void equiping(Hero h, GameView view) {
        if (!itIs(h)) {
            if (view.replaceArtifact("your weapon: " + h.getWeapon() + ", found: " + this)) {
                takeMe(h, view);
            }
        }
    }

    public abstract void takeMe(Hero h, GameView view);

    public abstract boolean itIs(Hero h);

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

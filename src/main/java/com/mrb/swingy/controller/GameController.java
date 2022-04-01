package com.mrb.swingy.controller;

import com.mrb.swingy.model.Game;
import com.mrb.swingy.model.artifact.Armor;
import com.mrb.swingy.model.artifact.Artifact;
import com.mrb.swingy.model.artifact.Helm;
import com.mrb.swingy.model.artifact.Weapon;
import com.mrb.swingy.model.character.Hero;
import com.mrb.swingy.model.character.Villain;
import com.mrb.swingy.util.DataBase;
import com.mrb.swingy.util.Point;
import com.mrb.swingy.view.game.GameView;

import javax.validation.constraints.NotNull;
import java.util.Random;

/**
 * Created by chvs on 19.06.2018.
 */
public class GameController {

    private final GameView view;
    private final Game game;
    private Hero hero;
    private final Point previousPosition;

    public GameController(GameView view) {
        this.view = view;
        game = Game.getInstance();
        hero = game.getHero();
        previousPosition = new Point(0, 0);
    }

    public void onStart() {
        view.update(game);
    }

    public void onPrintMap() {
        view.printMap(game.getMap(), game.getHeroCoord());
        view.update(game);
    }

    public void onMove(@NotNull String direction) {
        int x = game.getHeroCoord().x;
        int y = game.getHeroCoord().y;
        previousPosition.x = x;
        previousPosition.y = y;

        switch (direction.toUpperCase()) {
            case "NORTH": y--;
            case "EAST": x++;
            case "SOUTH": y++;
            case "WEST": x--;
        }

        if (x < 0 || y < 0 || x >= game.getMapSize() || y >= game.getMapSize()) {
            winGame();
            return;
        }

        game.getHeroCoord().x = x;
        game.getHeroCoord().y = y;

        if (game.getMap()[y][x]) {
            villainCollision();
        }

        if (hero.getHitPoints() > 0)
            view.update(game);
    }

    private void winGame() {
        view.showMessage("You win! And got additional " + game.getMapSize() * 100 + "xp!");
        addExperience(game.getMapSize() * 100);
        updateDataBase();
        view.gameFinished();
    }

    private void updateDataBase() {
        DataBase.updateHero(hero);
    }

    private void villainCollision() {
        view.getVillainCollisionInput();
    }

    public void onRun() {
        if (new Random().nextBoolean()) {
            view.showMessage("You are lucky! And moved to previous position!");
            game.getHeroCoord().x = previousPosition.x;
            game.getHeroCoord().y = previousPosition.y;
        } else {
            view.showMessage("You have to fight");
            onFight();
        }
    }

    private void setArtifact(Artifact artifact) {
        switch (artifact) {
            case Weapon w -> {
                if (null != hero.getWeapon()) {
                        if (view.replaceArtifact("your weapon: " + hero.getWeapon() + ", found: " + w)) {
                            hero.equipWeapon(w);
                            view.showMessage("You equipped new weapon: " + w);
                        }
                    }
            }
            case Helm h    -> {
                if (null != hero.getHelm()) {
                    if (view.replaceArtifact("your helmet: " + hero.getHelm() + ", found: " + h)) {
                        hero.equipHelm(h);
                        view.showMessage("You equipped new helm: " + h);
                    }
                }
            }
            case Armor a  -> {
                if (null != hero.getHelm()) {
                    if (view.replaceArtifact("your armor: " + hero.getArmor() + ", found: " + a)) {
                        hero.equipArmor(a);
                        view.showMessage("You equipped new armor: " + a);
                    }
                }
            }
        }
    }

    public void onFight() {
        Villain villain = game.generateVillain();
        int xp = game.fightResult(villain);

        if (xp >= 0) {
            view.showMessage("You win, and got " + xp + "xp.");
            addExperience(xp);
            game.getMap()[game.getHeroCoord().y][game.getHeroCoord().x] = false;
            setArtifact(villain.getArtifact());
        } else {
            view.showMessage("Game over :(");
            view.gameFinished();
        }
    }

    private void addExperience(int addXP) {
        int level = hero.getLevel();
        hero.addExperience(addXP);
        if (level != hero.getLevel())
            view.showMessage("Level UP!\nHP, attack and defense were increased!");
    }

    public void onSwitchButtonPressed() {
        view.switchView();
    }
}

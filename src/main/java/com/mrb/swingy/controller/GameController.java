package com.mrb.swingy.controller;

import com.mrb.swingy.model.Game;
import com.mrb.swingy.model.character.Hero;
import com.mrb.swingy.model.character.Villain;
import com.mrb.swingy.util.DataBase;
import com.mrb.swingy.util.Point;
import com.mrb.swingy.view.game.GameView;

import java.util.Random;

/**
 * Created by chvs on 19.06.2018.
 */
public class GameController {

    private GameView view;
    private Game game;
    private Point previousPosition;

    public GameController(GameView view){
        this.view = view;
        game = Game.getInstance();
        previousPosition = new Point(0,0);
    }

    public void onStart(){
        view.update(game);
    }

    public void onPrintMap(){
        view.printMap(game.getMap(), game.getHeroCoord());
        view.update(game);
    }

    public void onMove(String direction){
        int x = game.getHeroCoord().getX();
        int y = game.getHeroCoord().getY();
        previousPosition.setX(x);
        previousPosition.setY(y);

        switch (direction.toUpperCase()){
            case "NORTH":
                y--;
                break;
            case "EAST":
                x++;
                break;
            case "SOUTH":
                y++;
                break;
            case "WEST":
                x--;
                break;
        }

        if (x < 0 || y < 0 || x >= game.getMapSize() || y >= game.getMapSize()) {
            winGame();
            return;
        }

        game.getHeroCoord().setX(x);
        game.getHeroCoord().setY(y);

        if (game.getMap()[y][x]){
            villainCollision();
        }

        view.update(game);
    }

    private void winGame(){
        game.getHero().setExperience(game.getHero().getExperience() + game.getMapSize() * 100);
        updateDataBase();
        view.showMessage("You win! And got additional " + game.getMapSize() * 100 + "xp!");
        view.gameFinished();
    }

    private void updateDataBase(){
        Hero hero = game.getHero();
        DataBase.update(hero.getId(), hero.getLevel(), hero.getExperience(), hero.getAttack(), hero.getDefense(), hero.getHitPoints());
    }

    private void villainCollision(){
        view.getVillainCollisionInput();
    }

    public void onRun(){
        if (new Random().nextBoolean()){
            view.showMessage("You are lucky! And moved to previous position!");
            game.getHeroCoord().setX(previousPosition.getX());
            game.getHeroCoord().setY(previousPosition.getY());
        } else {
            view.showMessage("You have to fight");
            onFight();
        }
    }

    public void onFight(){
        Villain villain = game.generateVillain();
        int xp = game.fightResult(villain);
        if (xp >= 0) {
            view.showMessage("You win, and got " + xp + "xp.");
            game.getHero().setExperience(game.getHero().getExperience() + xp);
            game.getMap()[game.getHeroCoord().getY()][game.getHeroCoord().getX()] = false;
        } else{
            view.showMessage("Game over :(");
            view.gameFinished();
        }
    }
}

package com.mrb.swingy.controller;

import com.mrb.swingy.model.Game;
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

    public void onPrintMap(){
        view.printMap(game.getMap(), game.getHeroCoord());
        view.getUserInput();
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
            finishGame();
            return;
        }

        game.getHeroCoord().setX(x);
        game.getHeroCoord().setY(y);

        if (game.getMap()[y][x]){
            villainCollision();
        }

        view.getUserInput();
    }

    private void finishGame(){
        game.getHero().setExperience(game.getHero().getExperience() + game.getMapSize() * 100);
        view.gameFinished();
    }

    private void villainCollision(){
        view.getVillainCollisionInput();
    }

    public void onRun(){
        if (new Random().nextBoolean()){
            System.out.println("You are lucky"); //todo: message
            game.getHeroCoord().setX(previousPosition.getX());
            game.getHeroCoord().setY(previousPosition.getY());
        } else {
            System.out.println("You have to fight"); //todo: message
            onFight();
        }
    }

    public void onFight(){
        //todo: fight
    }
}

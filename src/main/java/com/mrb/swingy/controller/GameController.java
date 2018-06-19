package com.mrb.swingy.controller;

import com.mrb.swingy.model.Game;
import com.mrb.swingy.view.game.GameView;

/**
 * Created by chvs on 19.06.2018.
 */
public class GameController {

    private GameView view;
    private Game game;

    public GameController(GameView view){
        this.view = view;
        game = Game.getInstance();
    }

    public void onPrintMap(){
        view.printMap(game.getMap(), game.getHeroCoord());
    }
}

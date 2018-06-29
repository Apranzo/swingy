package com.mrb.swingy.view.game;

import com.mrb.swingy.model.Game;
import com.mrb.swingy.util.Point;

/**
 * Created by chvs on 19.06.2018.
 */
public interface GameView {

    void start();

    void printMap(boolean[][] map, Point heroCoord);

    void update(Game game);

    void gameFinished();

    void showMessage(String message);

    void getVillainCollisionInput();

    boolean replaceArtifact(String replaceMessage);

    void switchView();
}

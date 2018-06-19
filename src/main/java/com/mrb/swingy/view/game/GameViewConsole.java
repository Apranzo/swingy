package com.mrb.swingy.view.game;

import com.mrb.swingy.controller.GameController;
import com.mrb.swingy.util.Point;

import java.util.Scanner;

/**
 * Created by chvs on 19.06.2018.
 */
public class GameViewConsole implements GameView {

    private GameController controller;

    @Override
    public void start() {
        System.out.println("Game View Console");
        controller = new GameController(this);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Command(MAP):");
        while (scanner.hasNext()){
            String input = scanner.nextLine();

            if ("map".equalsIgnoreCase(input)){
                controller.onPrintMap();
                break;
            }
            else {
                System.out.println("Unknown command");
            }
        }
        scanner.close();
    }

    @Override
    public void printMap(boolean [][] map, Point heroCoord){
        System.out.printf("MAP %dx%d", map.length, map.length);
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (heroCoord.getX() == i && heroCoord.getY() == j)
                    System.out.print("H ");
                else if (map[i][j])
                    System.out.print("* ");
                else
                    System.out.print(". ");
            }
            System.out.println();
        }
    }
}

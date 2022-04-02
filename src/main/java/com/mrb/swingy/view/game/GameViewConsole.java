package com.mrb.swingy.view.game;

import com.mrb.swingy.Main;
import com.mrb.swingy.controller.GameController;
import com.mrb.swingy.model.Game;
import com.mrb.swingy.util.Point;

import java.util.Scanner;

public class GameViewConsole implements GameView {

    private GameController controller;

    @Override
    public void start() {
        controller = new GameController(this);
        controller.onStart();
    }

    @Override
    public void update(Game game) {
        System.out.printf("""
                ----------INFO----------
                %s Position: (%d,%d)
                ------------------------%n""", game.getHero().toString(), game.getX(), game.getY());
        getUserInput();
    }

    private void getUserInput() {
        Scanner scanner = Main.getScanner();
        System.out.println("""
                NORTH, EAST, SOUTH, WEST - to move to this direction
                SWITCH - to switch to GUI view
                Commands (NORTH, EAST, SOUTH, WEST, SWITCH):""");
        while (scanner.hasNext()) {
            String input = scanner.nextLine();
            switch (input.toLowerCase()) {
                case "map": controller.onPrintMap();
                case "north":
                case "east":
                case "south":
                case "west":
                    controller.onMove(input);
                case "switch": controller.onSwitchButtonPressed();
                default:  System.out.println("Unknown command");
            }
        }
    }

    @Override
    public void printMap(boolean[][] map, Point heroCoord) {
        System.out.printf("MAP %dx%d%n", map.length, map.length);
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (heroCoord.x == j && heroCoord.y == i)
                    System.out.print("H ");
                else if (map[i][j])
                    System.out.print("* ");
                else
                    System.out.print(". ");
            }
            System.out.println();
        }
    }

    @Override
    public void gameFinished() {
        System.out.println("See you!");
        Main.getFrame().dispose();
        Main.closeConnections();
    }

    @Override
    public void showMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void getVillainCollisionInput() {
        Scanner scanner = Main.getScanner();
        System.out.println("""
        
        You moved to position occupied by villain
        FIGHT - to fight with villain
        RUN - to run, 50% chance to move to the previous position
        Commands (FIGHT, RUN):""");

        while (scanner.hasNext()) {
            String input = scanner.nextLine();
            switch (input.toLowerCase()) {
                case "fight": controller.onFight();
                case "run": controller.onRun();
                default: System.out.println("Unknown command");
            }
        }
    }

    @Override
    public boolean replaceArtifact(String replaceMessage) {
        Scanner scanner = Main.getScanner();
        System.out.printf("""
        %nWould you like to replace %s?
        LEAVE - to leave your artifactREPLACE - to replace by new artifact
        Commands (LEAVE, REPLACE):%n""", replaceMessage);

        while (scanner.hasNext()) {
            switch (scanner.nextLine().toLowerCase()) {
                case "leave": return false;
                case "replace": return true;
                default: System.out.println("Unknown command");
            }
        }
        return false;
    }

    @Override
    public void switchView() {
        new GameViewGUI().start();
    }
}

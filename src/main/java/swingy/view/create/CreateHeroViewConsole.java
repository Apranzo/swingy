package swingy.view.create;

import main.java.swingy.Main;
import swingy.controller.CreateHeroController;
import swingy.view.game.GameViewConsole;

import java.util.Scanner;

public class CreateHeroViewConsole implements CreateHeroView {

    private CreateHeroController controller;

    @Override
    public void start() {
        controller = new CreateHeroController(this);

        getUserInput();
    }

    @Override
    public void getUserInput() {
        Scanner scanner = Main.getScanner();

        System.out.println("""
                            To create hero enter his name and class
                            Enter name:""");
        String name = scanner.nextLine();
        System.out.println("""
                Classes: attack  defense   hp
                Warrior    40      20      100
                Shaman     30      15      90
                Priest     25      25      90
                Paladin    40      30      120
                Mage       45      10      80
                Hunter     25      20      110
                Enter class name:\040""");
        controller.onCreateButtonPressed(name, scanner.nextLine());
//        while (scanner.hasNext()) {
//            String input = scanner.nextLine();
//
//            if ("create".equalsIgnoreCase(input)) {
//                controller.onCreateButtonPressed(name, heroClass);
//                break;
//            } else {
//                System.out.println("Unknown command");
//            }
//        }
    }

    @Override
    public void showErrorMessage(String message) {
        System.out.println("Error: " + message);
    }

    @Override
    public void openGame() {
        new GameViewConsole().start();
    }
}

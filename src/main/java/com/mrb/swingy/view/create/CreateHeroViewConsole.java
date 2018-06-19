package com.mrb.swingy.view.create;

import com.mrb.swingy.controller.CreateHeroController;

import java.util.Scanner;

/**
 * Created by chvs on 18.06.2018.
 */
public class CreateHeroViewConsole implements CreateHeroView{

    private CreateHeroController controller;

    @Override
    public void start() {
        System.out.println("Start Create Hero Console");
        controller = new CreateHeroController(this);

        getUserInput();
    }

    @Override
    public void getUserInput() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("To create hero enter his name and class.");
        System.out.println("Name:");
        String name = scanner.nextLine();
        System.out.println("Available classes(Warrior, Shaman, Priest, Paladin, Mage, Hunter):");
        String heroClass = scanner.nextLine();

        System.out.println("Command(CREATE):");
        while (scanner.hasNext()){
            String input = scanner.nextLine();

            if ("create".equalsIgnoreCase(input)){
                controller.onCreateButtonPressed(name, heroClass);
                break;
            }
            else {
                System.out.println("Unknown command");
            }
        }
        scanner.close();
    }

    @Override
    public void showErrorMessage(String message) {
        System.out.println("Error: " + message);
    }
}

package com.mrb.swingy.view.start;

import com.mrb.swingy.controller.StartController;
import com.mrb.swingy.view.create.CreateHeroViewConsole;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Created by chvs on 18.06.2018.
 */
public class StartViewConsole implements StartView{

    private StartController controller;

    @Override
    public void start() {
        System.out.println("Start View Console");
        controller = new StartController(this);

        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("Command:");
            String input = scanner.nextLine();

            if ("create".equalsIgnoreCase(input)){
                controller.onCreateHeroButtonPressed();
                break;
            } else {
                System.out.println("Unknown command");
            }
        }
        scanner.close();
    }

    @Override
    public void openCreateHero() {
        new CreateHeroViewConsole().start();
    }
}

package com.mrb.swingy;

import com.mrb.swingy.view.start.StartViewConsole;
import com.mrb.swingy.view.start.StartViewGUI;

public class Main {

    public static void main(String[] args) {
        if (args.length != 1 || (!args[0].equals("console") && !args[0].equals("gui"))){
            System.out.println("Usage: program console | gui");
            System.exit(1);
        }
        if (args[0].equals("console"))
            new StartViewConsole().start();
        else if (args[0].equals("gui"))
            new StartViewGUI().start();
    }
}

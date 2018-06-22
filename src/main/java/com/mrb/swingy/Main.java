package com.mrb.swingy;

import com.mrb.swingy.util.DataBase;
import com.mrb.swingy.view.start.StartViewConsole;
import com.mrb.swingy.view.start.StartViewGUI;

import javax.swing.*;

public class Main {

    private static JFrame frame;

    public static void main(String[] args) {
        if (args.length != 1 || (!args[0].equals("console") && !args[0].equals("gui"))){
            System.out.println("Usage: program console | gui");
            System.exit(1);
        }
        DataBase.connect();
        if (args[0].equals("console"))
            new StartViewConsole().start();
        else if (args[0].equals("gui")){
            new StartViewGUI().start();
        }
        DataBase.close();
    }

    public static JFrame getFrame(){
        if (frame == null) {
            frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500, 500);
        }
        return frame;
    }

    public static void showFrame(){
        if (frame != null)
            frame.setVisible(true);
    }

    public static void hideFrame(){
        if (frame != null)
            frame.setVisible(false);
    }
}

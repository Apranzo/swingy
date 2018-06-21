package com.mrb.swingy.view.start;

import com.mrb.swingy.Main;
import com.mrb.swingy.controller.StartController;
import com.mrb.swingy.view.create.CreateHeroViewGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by chvs on 18.06.2018.
 */
public class StartViewGUI extends JPanel implements StartView{

    private JLabel windowLabel = new JLabel("Start");
    private JButton createHeroButton = new JButton("Create Hero");
    private JButton switchViewButton = new JButton("Switch to console");

    private StartController controller;

    @Override
    public void start() {
        System.out.println("Start View GUI");
        controller = new StartController(this);

        buildUI();
    }

    private void buildUI(){
        this.add(windowLabel);
        this.add(createHeroButton);
        this.add(switchViewButton);
        this.setVisible(true);

        Main.getFrame().setContentPane(this);
        Main.getFrame().revalidate();
        Main.showFrame();

        createHeroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.onCreateHeroButtonPressed();
            }
        });
        switchViewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.onSwitchButtonPressed();
            }
        });
    }

    @Override
    public void openCreateHero() {
        this.setVisible(false);
        new CreateHeroViewGUI().start();
    }

    @Override
    public void switchView() {
        Main.hideFrame();
        new StartViewConsole().start();
    }
}

package com.mrb.swingy.view.start;

import com.mrb.swingy.controller.StartController;
import com.mrb.swingy.view.create.CreateHeroViewGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by chvs on 18.06.2018.
 */
public class StartViewGUI extends JFrame implements StartView{

    private JLabel windowLabel = new JLabel("Start");
    private JButton createHeroButton = new JButton("Create Hero");

    private StartController controller;

    @Override
    public void start() {
        System.out.println("Start View GUI");
        controller = new StartController(this);

        JPanel panel = new JPanel();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500,500);

        panel.add(windowLabel);
        panel.add(createHeroButton);
        this.add(panel);

        this.setVisible(true);

        createHeroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.onCreateHeroButtonPressed();
            }
        });
    }

    @Override
    public void openCreateHero() {
        this.setVisible(false);
        this.dispose();
        new CreateHeroViewGUI().start();
    }
}

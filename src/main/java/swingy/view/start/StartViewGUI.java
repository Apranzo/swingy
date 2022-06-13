package swingy.view.start;

import main.java.swingy.Main;
import swingy.controller.StartController;
import swingy.view.ViewGUI;
import swingy.view.create.CreateHeroViewGUI;
import swingy.view.select.SelectHeroViewGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartViewGUI extends ViewGUI implements StartView {

    private JButton createHeroButton = new JButton("Create Hero");
    private JButton selectHeroButton = new JButton("Select Hero");
    private JButton switchViewButton = new JButton("Switch to console");

    private StartController controller;

    @Override
    public void start() {
        controller = new StartController(this);

        buildUI();
    }

    private void buildUI() {
        Main.getFrame().setTitle("Start");
        GridBagConstraints gbc = initialGrid();
        this.add(createHeroButton, gbc);
        this.add(selectHeroButton, gbc);
        this.add(switchViewButton, gbc);

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
        selectHeroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.onSelectHeroButtonPressed();
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

    @Override
    public void openSelectHero() {
        this.setVisible(false);
        new SelectHeroViewGUI().start();
    }
}

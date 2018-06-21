package com.mrb.swingy.view.game;

import com.mrb.swingy.Main;
import com.mrb.swingy.controller.GameController;
import com.mrb.swingy.model.Game;
import com.mrb.swingy.util.Point;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by chvs on 21.06.2018.
 */
public class GameViewGUI extends JPanel implements GameView {

    private String[] directions = {"North", "East", "South", "West"};
    private JComboBox<String> directionsComboBox = new JComboBox<>(directions);
    private JButton moveButton = new JButton("Move");

    private JLabel heroCoordLabel = new JLabel("Position: ");
    private JLabel heroCoord = new JLabel("");

    private GameController controller;

    @Override
    public void start() {
        System.out.println("Game View GUI");
        controller = new GameController(this);

        buildUI();
        controller.onStart();
    }

    private void buildUI(){
        Main.getFrame().setTitle("Game");
        this.setLayout(new GridBagLayout());
        this.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5,5,5,5);

        JPanel heroCoordPanel = new JPanel();
        heroCoordPanel.add(heroCoordLabel, gbc);
        heroCoordPanel.add(heroCoord, gbc);
        this.add(heroCoordPanel, gbc);

        directionsComboBox.setSelectedIndex(0);
        this.add(directionsComboBox, gbc);
        this.add(moveButton, gbc);

        this.setVisible(true);
        Main.getFrame().setContentPane(this);
        Main.getFrame().revalidate();
        Main.showFrame();

        moveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.onMove((String)directionsComboBox.getSelectedItem());
            }
        });
    }

    @Override
    public void printMap(boolean[][] map, Point heroCoord) {

    }

    @Override
    public void update(Game game) {
        heroCoord.setText("(" + game.getHeroCoord().getX() + "," + game.getHeroCoord().getY() + ")");
        heroCoord.validate();
    }

    @Override
    public void gameFinished() {
        JOptionPane.showMessageDialog(Main.getFrame(), "Game finished");
        Main.hideFrame();
    }

    @Override
    public void getVillainCollisionInput() {
        Object options[] = {"Fight", "Run"};

        int result = JOptionPane.showOptionDialog(Main.getFrame(), "Fight or run?", "Title", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        if (result == JOptionPane.YES_OPTION)
            controller.onFight();
        else
            controller.onRun();
    }
}

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

    private JLabel heroNameLabel = new JLabel("Name: ");
    private JLabel heroName = new JLabel("");
    private JLabel heroCoordLabel = new JLabel("Position: ");
    private JLabel heroCoord = new JLabel("");
    private JLabel heroLevelLabel = new JLabel("Level: ");
    private JLabel heroLevel = new JLabel("");
    private JLabel heroXPLabel = new JLabel("XP: ");
    private JLabel heroXP = new JLabel("");
    private JLabel heroHPLabel = new JLabel("HP: ");
    private JLabel heroHP = new JLabel("");
    private JLabel heroAttackLabel = new JLabel("Attack: ");
    private JLabel heroAttack = new JLabel("");
    private JLabel heroDefenseLabel = new JLabel("Defense: ");
    private JLabel heroDefense = new JLabel("");

    private JEditorPane mapPane = new JEditorPane();

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

        this.add(getPanel(heroNameLabel, heroName), gbc);
        this.add(getPanel(heroCoordLabel, heroCoord), gbc);
        this.add(getPanel(heroLevelLabel, heroLevel), gbc);
        this.add(getPanel(heroXPLabel, heroXP), gbc);
        this.add(getPanel(heroHPLabel, heroHP), gbc);
        this.add(getPanel(heroAttackLabel, heroAttack), gbc);
        this.add(getPanel(heroDefenseLabel, heroDefense), gbc);
        gbc.insets = new Insets(5,5,5,5);

        mapPane.setEditable(false);
        mapPane.setText("Map");
        JScrollPane mapScroll = new JScrollPane(mapPane);
        mapScroll.setPreferredSize(new Dimension(300, 300));
        mapScroll.setMinimumSize(new Dimension(200, 200));
        this.add(mapScroll);

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

    private JPanel getPanel(JLabel first, JLabel second){
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel.add(first);
        panel.add(second);
        return panel;
    }

    @Override
    public void printMap(boolean[][] map, Point heroCoord) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("MAP %dx%d\n", map.length, map.length));
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (heroCoord.getX() == j && heroCoord.getY() == i)
                    stringBuilder.append("! ");
                else if (map[i][j])
                    stringBuilder.append("* ");
                else
                    stringBuilder.append(". ");
            }
            stringBuilder.append("\n");
        }
        mapPane.setText(stringBuilder.toString());
    }

    @Override
    public void update(Game game) {
        heroName.setText(game.getHero().getName());
        heroName.validate();
        heroCoord.setText("(" + game.getHeroCoord().getX() + "," + game.getHeroCoord().getY() + ")");
        heroCoord.validate();
        heroLevel.setText(Integer.toString(game.getHero().getLevel()));
        heroLevel.validate();
        heroXP.setText(Integer.toString(game.getHero().getExperience()));
        heroXP.validate();
        heroHP.setText(Integer.toString(game.getHero().getHitPoints()));
        heroHP.validate();
        heroAttack.setText(Integer.toString(game.getHero().getAttack()));
        heroAttack.validate();
        heroDefense.setText(Integer.toString(game.getHero().getDefense()));
        heroDefense.validate();

        printMap(game.getMap(), game.getHeroCoord());
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

package swingy.view.game;

import main.java.swingy.Main;
import swingy.controller.GameController;
import swingy.model.Game;
import swingy.util.Point;
import swingy.view.ViewGUI;

import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;
import java.awt.*;

public class GameViewGUI extends ViewGUI implements GameView {

    private String[] directions = {"North", "East", "South", "West"};
    private JComboBox<String> directionsComboBox = new JComboBox<>(directions);
    private JButton moveButton = new JButton("Move");
    private JButton switchButton = new JButton("Switch to console");

    private JEditorPane infoPane = new JEditorPane();
    private JEditorPane mapPane = new JEditorPane();

    private GameController controller;

    @Override
    public void start() {
        controller = new GameController(this);

        buildUI();
        controller.onStart();
    }

    private void buildUI() {
        Main.getFrame().setTitle("Game");
        this.setLayout(new GridBagLayout());
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        infoPane.setEditable(false);
        infoPane.setText("Select hero to see information");
        infoPane.setPreferredSize(new Dimension(220, 190));
        infoPane.setMinimumSize(new Dimension(200, 200));
        this.add(infoPane, gbc);
        gbc.insets = new Insets(5, 5, 5, 5);

        mapPane.setEditable(false);
        mapPane.setText("Map");
        JScrollPane mapScroll = new JScrollPane(mapPane);
        mapScroll.setPreferredSize(new Dimension(300, 300));
        mapScroll.setMinimumSize(new Dimension(200, 200));

        JPanel arrows = new JPanel(new BorderLayout());
        BasicArrowButton north = new BasicArrowButton(BasicArrowButton.NORTH);
        BasicArrowButton east = new BasicArrowButton(BasicArrowButton.EAST);
        BasicArrowButton west = new BasicArrowButton(BasicArrowButton.WEST);
        BasicArrowButton south = new BasicArrowButton(BasicArrowButton.SOUTH);
        north.addActionListener(e -> controller.onMove("north"));
        east.addActionListener(e -> controller.onMove("east"));
        west.addActionListener(e -> controller.onMove("west"));
        south.addActionListener(e -> controller.onMove("south"));
        arrows.add(north, BorderLayout.NORTH);
        arrows.add(east, BorderLayout.EAST);
        arrows.add(west, BorderLayout.WEST);
        arrows.add(south, BorderLayout.SOUTH);
        this.add(arrows, gbc);

        this.setVisible(true);
        Main.getFrame().setContentPane(this);
        Main.getFrame().revalidate();
        Main.showFrame();
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
        infoPane.setText(game.getHero().toString() +
                "Position: " + "(" + game.getHeroCoord().getX() +
                "," + game.getHeroCoord().getY() + ")");

        printMap(game.getMap(), game.getHeroCoord());
    }

    @Override
    public void gameFinished() {
        Main.hideFrame();
        Main.getFrame().dispose();
        Main.closeConnections();
    }

    @Override
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(Main.getFrame(), message);
    }

    @Override
    public void getVillainCollisionInput() {
        Object options[] = {"Fight", "Run"};

        int result = JOptionPane.showOptionDialog(Main.getFrame(),
                "You moved to position occupied by villain",
                "Fight or run?", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        if (result == JOptionPane.YES_OPTION)
            controller.onFight();
        else
            controller.onRun();
    }

    @Override
    public boolean replaceArtifact(String replaceMessage) {
        Object[] options = {"Replace", "Leave"};

        int result = JOptionPane.showOptionDialog(Main.getFrame(),
                "Would you like to replace " + replaceMessage + "?",
                "Replace or leave?", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        return result == JOptionPane.YES_OPTION;
    }

    @Override
    public void switchView() {
        Main.hideFrame();
        new GameViewConsole().start();
    }
}

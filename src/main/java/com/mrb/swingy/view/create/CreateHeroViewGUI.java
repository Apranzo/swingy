package com.mrb.swingy.view.create;

import com.mrb.swingy.Main;
import com.mrb.swingy.controller.CreateHeroController;
import com.mrb.swingy.view.game.GameViewGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by chvs on 18.06.2018.
 */
public class CreateHeroViewGUI extends JPanel implements CreateHeroView{

    private JLabel heroNameLabel = new JLabel("Hero name:");
    private JTextField heroNameField = new JTextField("Default hero");
    private JButton createHeroButton = new JButton("Create Hero");
    private String[] heroClasses = {"Warrior", "Shaman", "Priest", "Paladin", "Mage", "Hunter"};
    private JComboBox<String> classesComboBox = new JComboBox<>(heroClasses);
    private JEditorPane infoPane = new JEditorPane();

    private CreateHeroController controller;

    @Override
    public void start() {
        System.out.println("Start Create Hero GUI");
        controller = new CreateHeroController(this);

        buildUI();
    }

    private void buildUI(){
        Main.getFrame().setTitle("Create Hero");
        this.setLayout(new GridBagLayout());
        this.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5,5,5,5);

        JPanel createHeroPanel = new JPanel();
        createHeroPanel.add(heroNameLabel);
        createHeroPanel.add(heroNameField);
        createHeroPanel.setVisible(true);
        this.add(createHeroPanel, gbc);

        classesComboBox.setSelectedIndex(0);
        this.add(classesComboBox, gbc);

        infoPane.setEditable(false);
        infoPane.setText("           attack defense hp\n" +
                        "Warrior    40      20      100\n" +
                        "Shaman   30      15      90\n" +
                        "Priest       25      25      90\n" +
                        "Paladin    40      30      120\n" +
                        "Mage       45      10      80\n" +
                        "Hunter    25      20      110\n");
        infoPane.setPreferredSize(new Dimension(200, 120));
        infoPane.setMinimumSize(new Dimension(200, 120));
        this.add(infoPane, gbc);

        this.add(createHeroButton, gbc);
        this.setVisible(true);

        Main.getFrame().setContentPane(this);
        Main.getFrame().revalidate();
        Main.showFrame();

        createHeroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.onCreateButtonPressed(heroNameField.getText(), (String)classesComboBox.getSelectedItem());
            }
        });
    }

    @Override
    public void getUserInput() {}

    @Override
    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(Main.getFrame(), message);
    }

    @Override
    public void openGame() {
        this.setVisible(false);
        new GameViewGUI().start();
    }
}

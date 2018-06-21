package com.mrb.swingy.view.create;

import com.mrb.swingy.Main;
import com.mrb.swingy.controller.CreateHeroController;
import com.mrb.swingy.view.game.GameViewGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by chvs on 18.06.2018.
 */
public class CreateHeroViewGUI extends JPanel implements CreateHeroView{

    private JLabel windowLabel = new JLabel("Create Hero");
    private JLabel heroNameLabel = new JLabel("Hero name:");
    private JTextField heroNameField = new JTextField(10);
    private JButton createHeroButton = new JButton("Create Hero");
    String[] heroClasses = {"Warrior", "Shaman", "Priest", "Paladin", "Mage", "Hunter"};
    JComboBox<String> classesComboBox = new JComboBox<>(heroClasses);

    private CreateHeroController controller;

    @Override
    public void start() {
        System.out.println("Start Create Hero GUI");
        controller = new CreateHeroController(this);

        buildUI();
    }

    private void buildUI(){
        this.add(windowLabel);

        JPanel createHeroPanel = new JPanel();
        createHeroPanel.add(heroNameLabel);
        createHeroPanel.add(heroNameField);
        createHeroPanel.setVisible(true);
        this.add(createHeroPanel);

        classesComboBox.setSelectedIndex(0);
        this.add(classesComboBox);

        this.add(createHeroButton);
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

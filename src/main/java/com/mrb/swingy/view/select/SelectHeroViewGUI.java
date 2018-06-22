package com.mrb.swingy.view.select;

import com.mrb.swingy.Main;
import com.mrb.swingy.controller.SelectHeroController;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

/**
 * Created by chvs on 22.06.2018.
 */
public class SelectHeroViewGUI extends JPanel implements SelectHeroView {

    JEditorPane infoPane = new JEditorPane();
    private SelectHeroController controller;

    @Override
    public void start() {
        System.out.println("Select Hero View GUI");
        controller = new SelectHeroController(this);

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

        String[] data = controller.getListData();
        final JList list = new JList(data);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(-1);
        JScrollPane listScroller = new JScrollPane(list);
        listScroller.setPreferredSize(new Dimension(200, 200));
        this.add(listScroller);

        infoPane.setEditable(false);
        infoPane.setText("Select hero to see information");
        JScrollPane infoScroller = new JScrollPane(infoPane);
        infoScroller.setPreferredSize(new Dimension(200,200));
        infoScroller.setMinimumSize(new Dimension(10,10));
        this.add(infoScroller);

        this.setVisible(true);

        Main.getFrame().setContentPane(this);
        Main.getFrame().revalidate();
        Main.showFrame();

        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()){
                    if (list.getSelectedIndex() != -1)
                        controller.onListElementSelected(list.getSelectedIndex());
                }
            }
        });
    }

    @Override
    public void updateInfo(String info) {
        infoPane.setText(info);
    }

}

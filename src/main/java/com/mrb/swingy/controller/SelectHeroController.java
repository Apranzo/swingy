package com.mrb.swingy.controller;

import com.mrb.swingy.util.DataBase;
import com.mrb.swingy.view.select.SelectHeroView;

import java.util.ArrayList;

/**
 * Created by chvs on 22.06.2018.
 */
public class SelectHeroController {

    SelectHeroView view;

    public SelectHeroController(SelectHeroView view){
        this.view = view;
    }

    public void onListElementSelected(int idx){
        System.out.println("controller index - " + idx);
        ArrayList<String> hero = DataBase.selectById(idx + 1);

        view.updateInfo("Name: " + hero.get(0) + "\n" +
                "Class: " + hero.get(1) + "\n" +
                "Level: " + hero.get(2) + "\n" +
                "XP: " + hero.get(3) + "\n" +
                "Attack: " + hero.get(4) + "\n" +
                "Defense: " + hero.get(5) + "\n" +
                "HP: " + hero.get(6));
    }

    public String[] getListData(){
        ArrayList<String> list = DataBase.selectAll();
        String[] listArr = new String[list.size()];
        listArr = list.toArray(listArr);
        return listArr;
    }
}

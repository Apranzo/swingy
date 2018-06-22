package com.mrb.swingy.controller;

import com.mrb.swingy.exception.HeroValidationException;
import com.mrb.swingy.model.Game;
import com.mrb.swingy.model.character.Hero;
import com.mrb.swingy.model.character.HeroBuilder;
import com.mrb.swingy.model.character.HeroFactory;
import com.mrb.swingy.util.DataBase;
import com.mrb.swingy.view.select.SelectHeroView;

import java.util.ArrayList;

/**
 * Created by chvs on 22.06.2018.
 */
public class SelectHeroController {

    private SelectHeroView view;
    private Game game;

    public SelectHeroController(SelectHeroView view){
        this.view = view;
        game = Game.getInstance();
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

    public void onSelectButtonPressed(int idx){
        System.out.println("controller selected index - " + idx);
        ArrayList<String> heroDb = DataBase.selectById(idx + 1);

        Hero hero;
        try {
            HeroBuilder builder = new HeroBuilder();
            builder.setName(heroDb.get(0));
            builder.setHeroClass(heroDb.get(1));
            builder.setLevel(Integer.parseInt(heroDb.get(2)));
            builder.setExperience(Integer.parseInt(heroDb.get(3)));
            builder.setAttack(Integer.parseInt(heroDb.get(4)));
            builder.setDefense(Integer.parseInt(heroDb.get(5)));
            builder.setHitPoints(Integer.parseInt(heroDb.get(6)));
            builder.setId(Integer.parseInt(heroDb.get(7)));
            hero = builder.getHero();
            hero.validateHero();
        } catch (HeroValidationException e){
            view.showErrorMessage(e.getMessage());
            return;
        }
        game.initGame(hero);
        view.openGame();
    }
}

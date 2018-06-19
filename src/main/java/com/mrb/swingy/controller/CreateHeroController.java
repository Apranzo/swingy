package com.mrb.swingy.controller;

import com.mrb.swingy.model.character.Hero;
import com.mrb.swingy.model.character.HeroFactory;
import com.mrb.swingy.view.create.CreateHeroView;

/**
 * Created by chvs on 19.06.2018.
 */
public class CreateHeroController {

    private CreateHeroView view;

    public CreateHeroController(CreateHeroView view){
        this.view = view;
    }

    public void onCreateButtonPressed(String name, String heroClass){
        System.out.println("Controller: " + name + " " + heroClass);
        Hero hero = null;
        try {
            hero = HeroFactory.newHero(name, heroClass);
        } catch (IllegalArgumentException e){
            view.showErrorMessage(e.getMessage());
            view.getUserInput();
        }

    }
}

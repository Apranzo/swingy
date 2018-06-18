package com.mrb.swingy.controller;

import com.mrb.swingy.view.start.StartView;

/**
 * Created by chvs on 18.06.2018.
 */
public class StartController {

    private StartView view;

    public StartController(StartView view) {
        this.view = view;
    }

    public void onCreateHeroButtonPressed(){
        view.openCreateHero();
    }

    public void onSwitchButtonPressed(){
        view.switchView();
    }
}

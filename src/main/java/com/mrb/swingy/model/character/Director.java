package com.mrb.swingy.model.character;

/**
 * Created by chvs on 19.06.2018.
 */
public class Director {

    public static Hero createWarrior(String name){
        HeroBuilder builder = new HeroBuilder();
        builder.setName(name);
        builder.setAttack(40);
        builder.setDefense(20);
        builder.setHitPoints(100);
        builder.setHeroClass("Warrior");
        builder.setWeapon(null);
        builder.setArmor(null);
        builder.setHelm(null);
        builder.setLevel(1);
        builder.setExperience(0);
        return builder.getHero();
    }
}

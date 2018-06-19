package com.mrb.swingy.model;

import com.mrb.swingy.model.character.Hero;
import com.mrb.swingy.util.Point;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by chvs on 19.06.2018.
 */
public class Game {

    private static Game instance = null;

    private Hero hero;
    private Point heroCoord;
    private int mapSize;
    private boolean[][] map;

    private Game(){
    }

    public static Game getInstance(){
        if (instance == null){
            instance = new Game();
        }
        return instance;
    }

    public void initGame(Hero hero){
        this.hero = hero;
        generateMap();
        generateVillains();
        putHero();
    }

    private void generateMap(){
        int level = hero.getLevel();
        mapSize = (level - 1) * 5 + 10 - (level % 2);
        map = new boolean[mapSize][mapSize];
    }

    private void generateVillains(){
        int rand;
        int level = hero.getLevel();

        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                rand = ThreadLocalRandom.current().nextInt(0, 101);
                if ((level + 1) * 10 >= rand)
                    map[i][j] = true;
            }
        }
    }

    private void putHero(){
        heroCoord = new Point(mapSize / 2, mapSize / 2);
    }

    public int getMapSize() {
        return mapSize;
    }

    public void setMapSize(int mapSize) {
        this.mapSize = mapSize;
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public Point getHeroCoord() {
        return heroCoord;
    }

    public void setHeroCoord(Point heroCoord) {
        this.heroCoord = heroCoord;
    }

    public boolean[][] getMap() {
        return map;
    }

    public void setMap(boolean[][] map) {
        this.map = map;
    }
}

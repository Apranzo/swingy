package com.mrb.swingy.model;

import com.mrb.swingy.model.artifact.Armor;
import com.mrb.swingy.model.artifact.Artifact;
import com.mrb.swingy.model.artifact.Helm;
import com.mrb.swingy.model.artifact.Weapon;
import com.mrb.swingy.model.character.Character;
import com.mrb.swingy.model.character.Hero;
import com.mrb.swingy.model.character.Villain;
import com.mrb.swingy.util.Point;
import static com.mrb.swingy.util.Utils.getRandom;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class Game {

    private static Game instance = null;

    private Hero hero;
    private Point heroCoord;
    private int mapSize;
    private boolean[][] map;

    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }

    public void initGame(Hero hero) {
        this.hero = hero;
        generateMap();
        generateVillains();
        putHero();
    }

    private void generateMap() {
        int level = hero.getLevel();
        mapSize = (level - 1) * 5 + 10 - (level % 2);
        map = new boolean[mapSize][mapSize];
    }

    private void generateVillains() {
        int rand;
        int level = hero.getLevel();

        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                rand = getRandom(0, 101);
                if ((level + 1) * 10 >= rand)
                    map[i][j] = true;
            }
        }
    }

    public Villain generateVillain() {
        int attack = getRandom(hero.getAttack() - 20, hero.getAttack() + 2 + hero.getLevel());
        int defense = getRandom(hero.getDefense() - 20, hero.getDefense() + 2 + hero.getLevel());
        int hitPoints = getRandom(hero.getHitPoints() - 50, hero.getHitPoints() + 20 + hero.getLevel());
        Artifact artifact = generateArtifact();
        return new Villain("Villain", attack, defense, hitPoints, artifact);
    }

    private Artifact generateArtifact() {
//        int rand = getRandom(0, 10);
        switch (getRandom(0, 10)) {
            case 0: return new Weapon("Sword", getRandom(1, 5 * (hero.getLevel() + 1)));
            case 1: return new Helm("Pot", getRandom(1, 3 * (hero.getLevel() + 1)));
            case 2: return new Armor("Shield", getRandom(1, 4 * (hero.getLevel() + 1)));
            default: return null;
        }
//        if (rand == 0)
//            artifact = new Weapon("Sword", getRandom(1, 5 * (hero.getLevel() + 1)));
//        else if (rand == 1)
//            artifact = new Helm("Pot", getRandom(1, 3 * (hero.getLevel() + 1)));
//        else if (rand == 2)
//            artifact = new Armor("Shield", getRandom(1, 4 * (hero.getLevel() + 1)));
//        return artifact;
    }

    public int fightResult(Character villain) {
        int xp = villain.getAttack() + villain.getDefense() + villain.getHitPoints();
        int rand = getRandom(0, 101);
        return (hero.fight(villain) && rand <= 98) || rand < 3 ? xp : -1;
//        if (rand < 3)
//            return xp;
//        else if (rand > 98)
//            return -1;
//        return hero.fight(villain) ? xp : -1;
    }

    private void putHero() {
        heroCoord = new Point(mapSize / 2, mapSize / 2);
        map[heroCoord.y][heroCoord.x] = false;
    }

    public int getX() {
        return getHeroCoord().x;
    }

    public int getY() {
        return getHeroCoord().y;
    }

}

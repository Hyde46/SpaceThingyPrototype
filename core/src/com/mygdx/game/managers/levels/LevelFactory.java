package com.mygdx.game.managers.levels;

/**
 * Created by denis on 5/18/16.
 */
public class LevelFactory {

    private LevelLoader ll;

    public LevelFactory(){

    }


    public static Level loadLevel(int levelId){
        Level lvl = new Level();


        return lvl;
    }
}

package com.mygdx.game.managers.levels;

import com.mygdx.game.managers.UnitManager;
import com.mygdx.game.managers.background.ParallaxBackgroundManager;
import com.mygdx.game.managers.camera.CameraManager;
import com.mygdx.game.prototypeUtils.CameraHelper;
import com.mygdx.game.utils.SpacePhysiX;

/**
 * Created by denis on 5/18/16.
 */
public class Level {
    public UnitManager unitManager;
    public SpacePhysiX spacePhysiX;
    public ParallaxBackgroundManager parallaxBackgroundManager;

    public Level(){
        unitManager = new UnitManager();
        spacePhysiX = new SpacePhysiX();
        parallaxBackgroundManager = new ParallaxBackgroundManager();
    }
}

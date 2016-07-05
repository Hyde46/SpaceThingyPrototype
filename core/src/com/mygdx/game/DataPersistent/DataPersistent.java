package com.mygdx.game.DataPersistent;

import com.badlogic.gdx.graphics.g3d.particles.ResourceData;

/**
 * Created by Mechandrius on 19.06.2016.
 */
public class DataPersistent
{
    private static DataPersistent instance;
    private DataSaverLibGDX dataSaver;

    public DataSaved data;

    public DataPersistent()
    {
        dataSaver = new DataSaverLibGDX<DataSaved>(new DataSaved(), "SLOT0");
        data = (DataSaved)dataSaver.load_object();
    }

    public static DataPersistent get()
    {
        if(instance == null){ instance = new DataPersistent(); }
        return instance;
    }

    public void save()
    {
        dataSaver.save_object(data);
    }

    private void load()
    {
        data = (DataSaved)dataSaver.load_object();
    }

    public class DataSaved
    {
        /* Here can be stored everything */

        // Settings data
        // Level data
        // Player data

        public int nthGame = 0;
    }
}

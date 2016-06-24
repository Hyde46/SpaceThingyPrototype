package com.mygdx.game.DataPersistent;

/**
 * Created by Mechandrius on 19.06.2016.
 */
public class DataPersistent
{
    public static DataPersistent get;

    DataSaverLibGDX dataSaver;

    public DataSaved data;

    public DataPersistent()
    {
        dataSaver = new DataSaverLibGDX<DataSaved>(new DataSaved(), "SLOT0");
        data = (DataSaved)dataSaver.load_object();
    }

    public static void setup()
    {
        if(get == null)
        {
            get = new DataPersistent();
        }
    }

    public void save()
    {
        dataSaver.save_object(data);
    }

    public void load()
    {
        data = (DataSaved)dataSaver.load_object();
    }

    public class DataSaved
    {
        /* Here can stored everything */

        // Settings data

        // Level data

        // Player data
    }
}

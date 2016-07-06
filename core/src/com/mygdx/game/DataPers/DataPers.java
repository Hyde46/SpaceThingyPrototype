package com.mygdx.game.DataPers;

import com.badlogic.gdx.Gdx;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by Mechandrius on 05.07.2016.
 */
public class DataPers
{
    private static final String NAME_SAFE = "save.sav";
    private static final String PATH = Gdx.files.getLocalStoragePath();
    private static File file;

    private static DataSavable data;

    public static DataSavable data()
    {
        if(data == null)
        {
            file = new File(PATH, "/" + NAME_SAFE);
            if(!file.exists())
            {
                data = new DataSavable();
                save();
            }
            else
            {
                load();
            }
        }
        return data;
    }

    private static void ini()
    {
        data = new DataSavable();
        save();
    }

    public static void save()
    {
        try
        {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(data);
            oos.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            //Gdx.app.exit();
        }
    }

    public static void load()
    {
        try
        {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            data = (DataSavable) ois.readObject();
            ois.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            //Gdx.app.exit();
        }
    }
}

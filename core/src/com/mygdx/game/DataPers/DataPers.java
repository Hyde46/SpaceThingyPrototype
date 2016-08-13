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
    private static final String[] NAME_SAFE = new String[]{ "saveHangar", "saveShop", "saveProgress", "saveMisc" };

 //   private static final String NAME_SAFE = "save.sav";
    private static final String PATH = Gdx.files.getLocalStoragePath();

    private static File file[] = new File[4];
    private static DataSavable data[] = new DataSavable[4];

 //   private static boolean isFirstRequest = true;

    public static DataSavable data(int idSaveSlot)
    {
//        if(isFirstRequest)
//        {
//            isFirstRequest = false;
//
//        }

        if(data[idSaveSlot] == null)
        {
            file[idSaveSlot] = new File(PATH, "/" + NAME_SAFE[idSaveSlot]);
            if(!file[idSaveSlot].exists())
            {
                switch(idSaveSlot)
                {
                    case 0: data[0] = new DataSavableProgress(); break;
                    case 1: data[1] = new DataSavableShop(); break;
                    case 2: data[2] = new DataSavableHangar(); break;
                    case 3: data[3] = new DataSavableMisc(); break;
                }
                data[idSaveSlot] = new DataSavable();
                save(idSaveSlot);
            }
            else
            {
                load(idSaveSlot);
            }
        }
        return data[idSaveSlot];
    }

    public static void save(int idSaveSlot)
    {

        try
        {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file[idSaveSlot]));
            oos.writeObject(data);
            oos.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            //Gdx.app.exit();
        }
    }

    public static void load(int idSaveSlot)
    {
        try
        {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file[idSaveSlot]));
            data[idSaveSlot] = (DataSavable) ois.readObject();
            ois.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            //Gdx.app.exit();
        }
    }
}

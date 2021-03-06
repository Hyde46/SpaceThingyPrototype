/*
    need function for every dataclass. data(x) does not work because return type has to differ from super class
    else the cast to sub class savable must be done every time on usage
*/

package com.mygdx.game.dataPersistence;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.dataPersistence.saveClasses.ADataSavable;
import com.mygdx.game.dataPersistence.saveClasses.DataSavableHangar;
import com.mygdx.game.dataPersistence.saveClasses.DataSavableMisc;
import com.mygdx.game.dataPersistence.saveClasses.DataSavableProgress;
import com.mygdx.game.dataPersistence.saveClasses.DataSavableShop;

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
    // save names need to be changed every time variables are added or removed to a save class
    private static final String[] NAME_SAFE = new String[]{ "save0", "save1", "save2", "save3" };
    private static File file[] = new File[4];
    private static ADataSavable data[] = new ADataSavable[4];
    private static final String PATH = Gdx.files.getLocalStoragePath();

    public static DataSavableProgress dataP(){ return (DataSavableProgress)data(0); }
    public static DataSavableShop dataS(){ return (DataSavableShop)data(1); }
    public static DataSavableHangar dataH(){ return (DataSavableHangar)data(2); }
    public static DataSavableMisc dataM(){ return (DataSavableMisc)data(3); }

    public static void saveP(){ save(0); }
    public static void saveS(){ save(1); }
    public static void saveH(){ save(2); }
    public static void saveM(){ save(3); }

    public static void resetP(){ reset(0); }
    public static void resetS(){ reset(1); }
    public static void resetH(){ reset(2); }
    public static void resetM(){ reset(3); }

    // local
    private static ADataSavable data(int idSaveSlot)
    {
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
                save(idSaveSlot);
            }
            else
            {
                load(idSaveSlot);
            }
        }
        return data[idSaveSlot];
    }

    private static void reset(int idSaveSlot)
    {
        data[0] = new DataSavableProgress();
        data[1] = new DataSavableShop();
        data[2] = new DataSavableHangar();
        data[3] = new DataSavableMisc();

        for(int i = 0; i < 4; i++) save(i);
    }

    private static void save(int idSaveSlot)
    {
        try
        {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file[idSaveSlot]));
            oos.writeObject(data[idSaveSlot]);
            oos.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    private static void load(int idSaveSlot)
    {
        try
        {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file[idSaveSlot]));
            data[idSaveSlot] = (ADataSavable) ois.readObject();
            ois.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
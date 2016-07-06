package com.mygdx.game.DataPersistent;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.google.gson.Gson;

public class DataSaverLibGDX<T>
{
	private final Gson GSON = new Gson();
    private final Preferences PREFS;
    private final String NAME_PREFS = "PREFS_0";
	private final String SAVE_NAME;
	private final T DEFAULT_VALUE;

    public DataSaverLibGDX(T obj_default, String save_name)
    {
        this.PREFS = Gdx.app.getPreferences("NAME_PREFS");
        this.DEFAULT_VALUE = obj_default;
        this.SAVE_NAME = save_name;
        save_object(obj_default);
    }

	public void save_object(T object)
	{
		String json_object = GSON.toJson(object);
		PREFS.putString(SAVE_NAME, json_object);
        PREFS.flush();
	}

	public T load_object()
	{
		String json_object = PREFS.getString(SAVE_NAME, "");
        PREFS.flush();

        System.out.println("saved string + " + json_object);

		if (json_object == "")
		{
			//save_object(DEFAULT_VALUE);
			return DEFAULT_VALUE;
		}
		else
		{
			return (T)GSON.fromJson(json_object, DEFAULT_VALUE.getClass());
		}						
	}

    public void object_set_default()
    {
        save_object(DEFAULT_VALUE);
    }

    public void save_bool(String save_name, boolean value)
        {PREFS.putBoolean(save_name, value);}
    public void save_int(String save_name, int value)
        {PREFS.putInteger(save_name, value);}
    public void save_float(String save_name, float value)
        {PREFS.putFloat(save_name, value);}

    public boolean load_bool(String save_name, boolean default_value)
        {return PREFS.getBoolean(save_name, default_value);}
    public int load_int(String save_name, int default_value)
        {return PREFS.getInteger(save_name, default_value);}
    public float load_float(String save_name, float default_value)
        {return PREFS.getFloat(save_name, default_value);}

    public void prefs_reset()   {   PREFS.remove(NAME_PREFS);   }
}
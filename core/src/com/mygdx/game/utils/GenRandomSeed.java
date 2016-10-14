package com.mygdx.game.utils;

import java.util.Random;

/**
 * Created by ilost on 11.10.2016.
 */

public class GenRandomSeed
{
    private long seed;
    private Random rdm;

    public GenRandomSeed(long seed)
    {
        this.seed = seed;
        this.rdm = new Random();
    }

    public int getInt(int limitUpper)
    {
        rdm.setSeed(seed);
        int num = rdm.nextInt(limitUpper);
        seed += 1337;
        return num;
    }

    public int getInt()
    {
        rdm.setSeed(seed);
        int num = rdm.nextInt();
        seed += 1337;
        return num;
    }

    public float getFloat()
    {
        rdm.setSeed(seed);
        float num = rdm.nextFloat();
        seed += 1337;
        return num;
    }
}
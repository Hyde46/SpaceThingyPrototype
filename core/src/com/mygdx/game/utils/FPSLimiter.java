package com.mygdx.game.utils;
import com.badlogic.gdx.utils.TimeUtils;

public class FPSLimiter {

    // thread sleep verhindert auch andere berechnungen,
    // vielleicht günstiger nur das rendering synchron (langsam) zu halten
    // und den thread im background noch für andere asynchrone prozesse verfügbar zu halten

    private long previousTime = TimeUtils.nanoTime();
    private long currentTime = TimeUtils.nanoTime();
    private long deltaTime = 0;
    private float fps;

    public FPSLimiter(float fps) {
        this.fps = fps;
    }

    public void delay() {
        currentTime = TimeUtils.nanoTime();
        deltaTime += currentTime - previousTime;
        while (deltaTime < 1000000000 / fps) {
            previousTime = currentTime;
            long diff = (long) (1000000000 / fps - deltaTime);
            if (diff / 1000000 > 1) {
                try {
                    Thread.currentThread().sleep(diff / 1000000 - 1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            currentTime = TimeUtils.nanoTime();
            deltaTime += currentTime - previousTime;
            previousTime = currentTime;
        }
        deltaTime -= 1000000000 / fps;
    }
}
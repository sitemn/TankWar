package com.site.tankgame;

/**
 * @author : Site Yin
 * @mailto : site.yin@yahoo.com
 * @created : 10/10/2021, Sunday
 **/
public class Explosion {
    int x; // Explosion x coordinate
    int y; //Explosion y coordiante
    int duration = 10;
    boolean isLive = true;

    public Explosion(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void durationDown(){
        if(duration > 0) {
            duration--;
        }
        else{
            isLive = false;
        }
    }
}

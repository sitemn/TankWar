package com.site.tankgame;

import java.util.Vector;

public class MyTank extends Tank{
    //attack object
    Attack attack = null;
    Vector<Attack> attacks = new Vector<>();
    public MyTank(int x, int y) {
        super(x, y);
    }
    int maxBullet = 7;
    //attack
    public void attackEnemy(){
        //new attack based on moving direction
        if(attacks.size() == maxBullet){
            return;
        }
        switch (getDirection()){
            case 0: //UP
                attack = new Attack(getX() + 25, getY(), 0); break;
            case 1: //right
                attack = new Attack(getX() + 55, getY() + 25, 1); break;
            case 2: //down
                attack = new Attack(getX() + 25, getY() + 55, 2); break;
            case 3: //left
                attack = new Attack(getX(), getY() + 25, 3); break;
            default:
                throw new IllegalStateException("Unexpected value: " + getDirection());
        }

        attacks.add(attack);
        //start a new attack thread
        new  Thread(attack).start();
    }

}

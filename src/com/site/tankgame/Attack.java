package com.site.tankgame;

/**
 * @author : Site Yin
 * @mailto : site.yin@yahoo.com
 * @created : 10/9/2021, Saturday
 **/
public class Attack implements Runnable{
    private int x; // bullet x coordinate
    private int y; //bullet y coordiante
    private int direction;
    private int moveSpeed = 3;
    private boolean isMoving = true;

    public Attack(int x, int y, int direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getMoveSpeed() {
        return moveSpeed;
    }

    public void setMoveSpeed(int moveSpeed) {
        this.moveSpeed = moveSpeed;
    }

    public boolean isMoving() {
        return isMoving;
    }

    public void setMoving(boolean moving) {
        isMoving = moving;
    }

    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //moving based on direction
            switch(direction){
                case 0: //UP
                    y -= moveSpeed; break;
                case 1: //right
                    x += moveSpeed; break;
                case 2: //down
                    y += moveSpeed; break;
                case 3: //left
                    x -= moveSpeed; break;
                default:
                    throw new IllegalStateException("Unexpected value: " + direction);
            }

            //when bullet reach edges or hit enemy tank
            if(!(x >= 0 && x <= 1000 && y >= 0 && y<= 750) && isMoving){
                isMoving = false;
                break;
            }
        }
    }
}

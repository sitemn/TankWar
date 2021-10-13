package com.site.tankgame;

public class Tank {

    private int x; // tank x coordinate
    private int y; //tank y coordiante
    private int direction;
    private int moveSpeed = 2;
    private boolean isLive = true;

    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
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

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
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

    public void moveUp(){
        y -= moveSpeed;
    }
    public void moveRight(){
        x += moveSpeed;
    }
    public void moveDown(){
        y += moveSpeed;
    }
    public void moveLeft(){
        x -= moveSpeed;
    }
}

package com.site.tankgame;

import java.util.Vector;

/**
 * @author : Site Yin
 * @mailto : site.yin@yahoo.com
 * @created : 10/8/2021, Friday
 **/
public class EnemyTank extends Tank implements Runnable{
    //Use vector keep multiple tanks
    Vector<EnemyTank> enemyTank = new Vector<>();
    //Use vector keep multiple attacks
    Vector<Attack> attacks = new Vector<>();
    public EnemyTank(int x, int y) {
        super(x, y);
    }

    public void setEnemyTank(Vector<EnemyTank> enemy ){
        this.enemyTank = enemy;
    }

    //check if current enemy tank reaches other enemy tanks
    public boolean isMeet(){
        switch(this.getDirection()){
            case 0:
                for (int i = 0; i < enemyTank.size(); i++) {
                    EnemyTank enemy = enemyTank.get(i);
                    if(enemy != this){
                        if(enemy.getDirection() == 0 || enemy.getDirection() == 2){
                            if(this.getX() >= enemy.getX() && this.getX() <= enemy.getX() + 50 &&
                            this.getY() >= enemy.getY() && this.getY() <= enemy.getY() +55){
                                return true;
                            }

                            if(this.getX() + 50 >= enemy.getX() && this.getX() + 50<= enemy.getX() + 50 &&
                                    this.getY() >= enemy.getY() && this.getY() <= enemy.getY() +55){
                                return true;
                            }
                        }
                        if(enemy.getDirection() == 1 || enemy.getDirection() == 3){
                            if(this.getX() >= enemy.getX() && this.getX() <= enemy.getX() + 55 &&
                                    this.getY() >= enemy.getY() && this.getY() <= enemy.getY() +50){
                                return true;
                            }

                            if(this.getX() + 50 >= enemy.getX() && this.getX() + 50<= enemy.getX() + 55 &&
                                    this.getY() >= enemy.getY() && this.getY() <= enemy.getY() +50){
                                return true;
                            }
                        }
                    }
                } break;
            case 1:
                for (int i = 0; i < enemyTank.size(); i++) {
                    EnemyTank enemy = enemyTank.get(i);
                    if(enemy != this){
                        if(enemy.getDirection() == 0 || enemy.getDirection() == 2){
                            if(this.getX()+55 >= enemy.getX() && this.getX()+55 <= enemy.getX() + 50 &&
                                    this.getY() >= enemy.getY() && this.getY() <= enemy.getY() +55){
                                return true;
                            }

                            if(this.getX() + 55 >= enemy.getX() && this.getX() + 55<= enemy.getX() + 50 &&
                                    this.getY() +50 >= enemy.getY() && this.getY()+50 <= enemy.getY() +55){
                                return true;
                            }
                        }
                        if(enemy.getDirection() == 1 || enemy.getDirection() == 3){
                            if(this.getX()+ 55 >= enemy.getX() && this.getX()+55 <= enemy.getX() + 55 &&
                                    this.getY() >= enemy.getY() && this.getY() <= enemy.getY() +50){
                                return true;
                            }

                            if(this.getX() + 55 >= enemy.getX() && this.getX() + 55<= enemy.getX() + 55 &&
                                    this.getY()+50 >= enemy.getY() && this.getY()+50 <= enemy.getY() +50){
                                return true;
                            }
                        }
                    }
                } break;
            case 2:
                for (int i = 0; i < enemyTank.size(); i++) {
                    EnemyTank enemy = enemyTank.get(i);
                    if(enemy != this){
                        if(enemy.getDirection() == 0 || enemy.getDirection() == 2){
                            if(this.getX() >= enemy.getX() && this.getX() <= enemy.getX() + 50 &&
                                    this.getY()+55 >= enemy.getY() && this.getY()+55 <= enemy.getY() +55){
                                return true;
                            }

                            if(this.getX()+50 >= enemy.getX() && this.getX() + 50 <= enemy.getX() + 50 &&
                                    this.getY() +55 >= enemy.getY() && this.getY()+55 <= enemy.getY() +55){
                                return true;
                            }
                        }
                        if(enemy.getDirection() == 1 || enemy.getDirection() == 3){
                            if(this.getX() >= enemy.getX() && this.getX() <= enemy.getX() + 55 &&
                                    this.getY()+55 >= enemy.getY() && this.getY()+55 <= enemy.getY() +50){
                                return true;
                            }

                            if(this.getX() + 50 >= enemy.getX() && this.getX() + 50<= enemy.getX() + 55 &&
                                    this.getY()+55 >= enemy.getY() && this.getY()+55 <= enemy.getY() +50){
                                return true;
                            }
                        }
                    }
                } break;
            case 3:
                for (int i = 0; i < enemyTank.size(); i++) {
                    EnemyTank enemy = enemyTank.get(i);
                    if(enemy != this){
                        if(enemy.getDirection() == 0 || enemy.getDirection() == 2){
                            if(this.getX() >= enemy.getX() && this.getX() <= enemy.getX() + 50 &&
                                    this.getY()>= enemy.getY() && this.getY()<= enemy.getY() +55){
                                return true;
                            }

                            if(this.getX()>= enemy.getX() && this.getX()<= enemy.getX() + 50 &&
                                    this.getY() +50 >= enemy.getY() && this.getY()+50 <= enemy.getY() +55){
                                return true;
                            }
                        }
                        if(enemy.getDirection() == 1 || enemy.getDirection() == 3){
                            if(this.getX() >= enemy.getX() && this.getX() <= enemy.getX() + 55 &&
                                    this.getY()>= enemy.getY() && this.getY() <= enemy.getY() +50){
                                return true;
                            }

                            if(this.getX()>= enemy.getX() && this.getX()<= enemy.getX() + 55 &&
                                    this.getY()+50 >= enemy.getY() && this.getY()+50 <= enemy.getY() +50){
                                return true;
                            }
                        }
                    }
                } break;
            default:
                throw new IllegalStateException("Unexpected value: " + getDirection());

        }
        return false;
    }

    public void randomlyMoving(int direction){
        //randomly moving 15~30 steps in one direction
        int steps = (int) ((Math.random() * 25) + 15);
        for (int i = 0; i < steps; i++) {
            if(direction == 0 && getY() > 0 && !isMeet()){
                moveUp();
            }else if (direction == 1 && (getX() + 55) < 1000 && !isMeet()){
                moveRight();
            }else if (direction == 2 && (getY() + 55) < 750 && !isMeet()){
                moveDown();
            }else if (direction == 3 && getX() > 0 && !isMeet()){
                moveLeft();
            }
            //sleep 50ms
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        while (isLive()) {
            if(attacks.size() == 0){
                Attack a = null;
                switch (getDirection()){
                    case 0:
                        a = new Attack(getX()+25, getY(), 0); break;
                    case 1:
                        a = new Attack(getX()+55, getY() + 25, 1); break;
                    case 2:
                        a = new Attack(getX()+25, getY() + 55, 2); break;
                    case 3:
                        a = new Attack(getX(), getY()+25, 3); break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + getDirection());
                }
                attacks.add(a);
                new Thread(a).start();
            }
            randomlyMoving(getDirection());
            //randomly set tank's moving direction
            setDirection((int) (Math.random() * 4));
        }
    }
}

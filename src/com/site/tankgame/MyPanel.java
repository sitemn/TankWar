package com.site.tankgame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

/**
 * @author : Site Yin
 * @mailto : site.yin@yahoo.com
 * @created : 10/8/2021, Friday
 * tank war mapping
 **/
public class MyPanel extends JPanel implements KeyListener, Runnable {
    MyTank mytank = null;
    Vector<EnemyTank> enemyTank = new Vector<>();
    Vector<Explosion> explosions = new Vector<>();
    Vector<Node> nodes = new Vector<>();
    int enemyTankSize = 3;

    //explosion effection images
    Image image1 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/explosion1.png"));
    Image image2 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/explosion2.png"));
    Image image3 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/explosion3.png"));
    MediaTracker tracker = new MediaTracker(this);

    public MyPanel(int key){
        //get enemy tank info from file
        nodes = Score.getEnemyTankInfo();
        //set enemyTanks to Score class
        Score.setEnemyTank(enemyTank);
        //initialize my tank
        mytank = new MyTank(600, 600);
        mytank.setMoveSpeed(4);

        switch (key){
            case 1:
                //initialize enemy tanks
                for (int i = 0; i < enemyTankSize; i++) {
                    EnemyTank enemy = new EnemyTank(100 * (i + 1), 0);
                    enemy.setEnemyTank(enemyTank);
                    enemy.setDirection(2); //set enemy direction
                    new Thread(enemy).start(); //start enemy tank thread
                    //add a attack action to an enemy tank
                    Attack attack = new Attack(enemy.getX() + 25, enemy.getY() + 55, enemy.getDirection());
                    enemy.attacks.add(attack);
                    new Thread(attack).start(); //start attack object
                    enemyTank.add(enemy);
                }
                break;
            case 2: //resume
                //initialize enemy tanks
                for (int i = 0; i < nodes.size(); i++) {
                    Node node = nodes.get(i);
                    EnemyTank enemy = new EnemyTank(node.getX(), node.getY());
                    enemy.setEnemyTank(enemyTank);
                    enemy.setDirection(node.getDirection()); //set enemy direction
                    new Thread(enemy).start(); //start enemy tank thread
                    //add a attack action to an enemy tank
                    Attack attack = new Attack(enemy.getX() + 25, enemy.getY() + 55, enemy.getDirection());
                    enemy.attacks.add(attack);
                    new Thread(attack).start(); //start attack object
                    enemyTank.add(enemy);
                }
            default:
                System.out.println("Invalid input!");
        }
        //intialize images
        tracker.addImage(image1, 1);
        tracker.addImage(image2, 1);
        tracker.addImage(image3, 1);
    }

    //display scores
    public void displayScores(Graphics g){
        g.setColor(Color.BLACK);
        Font font = new Font("TimesRoman", Font.BOLD, 24);
        g.setFont(font);

        g.drawString("Total destroyed enemy tanks", 1024, 24);
        drawTank(1024, 50, g, 0, 1);
        g.setColor(Color.BLACK);
        g.drawString(Score.getDestroyedEnemy()+"", 1100, 100);
    }
    @Override
    public void paint(Graphics g){
        super.paint(g);
        //game area
        g.fillRect(0,0,1000,750);
        displayScores(g);
        //draw my tank
        if(mytank != null && mytank.isLive()){
            drawTank(mytank.getX(), mytank.getY(), g, mytank.getDirection(), 0);
        }
        //draw bullets
        for (int i = 0; i < mytank.attacks.size(); i++) {
            Attack attack = mytank.attacks.get(i);
            if(attack != null && attack.isMoving()){
                g.draw3DRect(attack.getX(), attack.getY(), 3,3,false);
            }else{//remove attack
                mytank.attacks.remove(i);
            }
        }

        try {
            tracker.waitForID(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //explosion effection
        for (int i = 0; i < explosions.size(); i++) {
            Explosion explosion = explosions.get(i);

            if(explosion.duration > 6){
                g.drawImage(image3, explosion.x, explosion.y, 50, 50, this);
            } else if(explosion.duration > 3){
                g.drawImage(image2, explosion.x, explosion.y, 60, 60, this);
            } else {
                g.drawImage(image1, explosion.x, explosion.y, 70, 70, this);
            }
            explosion.durationDown();
            if(explosion.duration == 0){
                explosions.remove(explosion);
            }

        }
        //draw enemy tank
        for (int i = 0; i < enemyTank.size(); i++) {
            EnemyTank enemy = enemyTank.get(i);
            //Draw alive tanks
            if(enemy.isLive() == false){
                enemyTank.remove(i);
                continue;
            }else{
                drawTank(enemy.getX(), enemy.getY(), g, enemy.getDirection(), 1);
                //draw enemy's all bullets
                for (int j = 0; j < enemy.attacks.size(); j++) {
                    Attack attack = enemy.attacks.get(j);
                    if(attack.isMoving()){
                        g.draw3DRect(attack.getX(), attack.getY(), 3,3,false);
                    }else{//remove attack from vector if bulet is not moving
                        enemy.attacks.remove(attack);
                    }
                }
            }
        }
    }


    //Draw tanks based on moving direction and tank types
    public void drawTank(int x, int y, Graphics g, int direction, int type){
        //set tank color
        switch (type){
            case 0: //my tank
                g.setColor(Color.ORANGE);
                break;
            case 1: //enemy tank
                g.setColor(Color.green);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }

        //draw tanks based on moving direction
        switch (direction){
            case 0: //up
                g.fill3DRect(x, y, 15, 55, false); //left wheel
                g.fill3DRect(x+35, y, 15, 55, false);  //right wheel
                g.fill3DRect(x+15, y+10, 20, 35, false); //center body
                g.fillOval(x+15, y+17, 20, 20);
                g.drawLine(x+25, y, x+25, y+30);
                break;
            case 1: //right
                g.fill3DRect(x, y, 55, 15, false); //left wheel
                g.fill3DRect(x, y+35, 55, 15, false);  //right wheel
                g.fill3DRect(x+10, y+15, 35, 20, false); //center body
                g.fillOval(x+17, y+15, 20, 20);
                g.drawLine(x+25, y+25, x+55, y+25);
                break;
            case 2: //down
                g.fill3DRect(x, y, 15, 55, false); //left wheel
                g.fill3DRect(x+35, y, 15, 55, false);  //right wheel
                g.fill3DRect(x+15, y+10, 20, 35, false); //center body
                g.fillOval(x+15, y+17, 20, 20);
                g.drawLine(x+25, y+30, x+25, y+55);
                break;
            case 3: //left
                g.fill3DRect(x, y, 55, 15, false); //left wheel
                g.fill3DRect(x, y+35, 55, 15, false);  //right wheel
                g.fill3DRect(x+10, y+15, 35, 20, false); //center body
                g.fillOval(x+17, y+15, 20, 20);
                g.drawLine(x+25, y+25, x, y+25);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + direction);
        }
    }

    //check if a certain bullet hit the enemy
    public void hitTank(Attack a, Tank e){
        switch(e.getDirection()){
            case 0://UP
            case 2://Down
                if((a.getX() >= e.getX()) && (a.getX() <= e.getX() + 50) &&
                        (a.getY() >= e.getY()) && (a.getY() <= e.getY() + 55)){
                    a.setMoving(false);
                    e.setLive(false);
                    if(e instanceof EnemyTank){ Score.incrementScore(); }
                    //explosion effection
                    Explosion explosion = new Explosion(e.getX(), e.getY());
                    explosions.add(explosion);
                } break;
            case 1://Right
            case 3://Left
                if((a.getX() >= e.getX()) && (a.getX() <= e.getX() + 55) &&
                        (a.getY() >= e.getY()) && (a.getY() <= e.getY() + 50)){
                    a.setMoving(false);
                    e.setLive(false);
                    if(e instanceof EnemyTank){ Score.incrementScore(); }
                    //explosion effection
                    Explosion explosion = new Explosion(e.getX(), e.getY());
                    explosions.add(explosion);
                } break;
            default:
                throw new IllegalStateException("Unexpected value: " + e.getDirection());
        }
    }

    //check if any of bullet hit enemy
    public void hitEnemyTank(){
        for (int j = 0; j < mytank.attacks.size(); j++) {
            Attack attack = mytank.attacks.get(j);
            if(attack != null && attack.isMoving()){
                for (int i = 0; i < enemyTank.size(); i++) {
                    EnemyTank enemy = enemyTank.get(i);
                    hitTank(attack, enemy);
                }
            }
        }
    }

    //check if enemy tank hit player's tank
    public void hitMyTank(){
        for (int i = 0; i < enemyTank.size(); i++) {
            EnemyTank enemy = enemyTank.get(i);
            for(int j = 0; j < enemy.attacks.size(); j++){
               Attack attack = enemy.attacks.get(j);
               if(mytank.isLive() && attack.isMoving()){
                   hitTank(attack, mytank);
               }
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        //change tank moving direction based on pressed key
        if(e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP){
            mytank.setDirection(0);
            if(mytank.getY() > 0){ mytank.moveUp(); }
        }else if(e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT){
            mytank.setDirection(1);
            if((mytank.getX() + 55) < 1000){mytank.moveRight();}
        }else if(e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_DOWN){
            mytank.setDirection(2);
            if((mytank.getY() + 55) < 750){mytank.moveDown();}
        }else if(e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT){
            mytank.setDirection(3);
            if(mytank.getX() > 0){mytank.moveLeft();}
        }

        if(e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_J){
//            if(mytank.attack == null || !mytank.attack.isMoving()){
//                mytank.attackEnemy();
//            }
            mytank.attackEnemy();
        }
        //repaint game area
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //check if player hit enemy tanks
            hitEnemyTank();
            //check if enemy tanks hit player tank
            hitMyTank();
            this.repaint();
        }
    }
}

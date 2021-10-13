package com.site.tankgame;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.*;
import java.util.Vector;

/**
 * @author : Site Yin
 * @mailto : site.yin@yahoo.com
 * @created : 10/12/2021, Tuesday
 * keep track of the game scores
 **/
public class Score {
    private static int destroyedEnemy = 0;
    private static BufferedWriter bufferedWriter = null;
    private static BufferedReader bufferedReader = null;
    private static String myScore = "D:\\CodingTools\\IdeaProjects\\TankWar\\myScore.txt";
    private static Vector<EnemyTank> enemyTank = null;
    private static Vector<Node> nodes = new Vector<>();

    public static String getMyScore() {
        return myScore;
    }

    public static void setEnemyTank(Vector<EnemyTank> enemy) {
        Score.enemyTank = enemy;
    }

    public static int getDestroyedEnemy() {
        return destroyedEnemy;
    }

    public static void setDestroyedEnemy(int destroyedEnemy) {
        Score.destroyedEnemy = destroyedEnemy;
    }

    public static void incrementScore(){
        Score.destroyedEnemy++;
    }

    //read last game score befor game start
    public static Vector<Node> getEnemyTankInfo(){
        try {
            bufferedReader = new BufferedReader(new FileReader(myScore));
            destroyedEnemy = Integer.parseInt(bufferedReader.readLine());
            String enemyInfo = "";
            while((enemyInfo = bufferedReader.readLine()) != null){
                String[] abc = enemyInfo.split(" ");
                Node node = new Node(Integer.parseInt(abc[0]),
                        Integer.parseInt(abc[1]),
                        Integer.parseInt(abc[2]));
                nodes.add(node);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(bufferedReader != null){
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return nodes;
    }

    //save scores when game over
    public static void saveScore(){
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(myScore));
//            bufferedWriter.write("Total destroyed enemy tank: ");
//            bufferedWriter.newLine();
            bufferedWriter.write(destroyedEnemy+ "\r\n");

            for (int i = 0; i < enemyTank.size(); i++) {
                EnemyTank enemy = enemyTank.get(i);
                if(enemy.isLive()){
                    String info = enemy.getX() + " " + enemy.getY() + " " + enemy.getDirection();
                    bufferedWriter.write(info + "\r\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(bufferedWriter != null){
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

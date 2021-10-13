package com.site.tankgame;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Scanner;

/**
 * @author : Site Yin
 * @mailto : site.yin@yahoo.com
 * @created : 10/8/2021, Friday
 **/
public class TankGame extends JFrame {

    MyPanel myPanel = null;
    static Scanner scanner =new Scanner(System.in);
    public TankGame(){
        System.out.println("Please select New Game (1) or Resume (2): ");
        int key = scanner.nextInt();
        myPanel = new MyPanel(key);
        Thread thread   = new Thread(myPanel);
        thread.start();

        this.add(myPanel);
        this.setSize(1500, 750);
        this.addKeyListener(myPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Score.saveScore();
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        TankGame tankGame = new TankGame();
    }

}

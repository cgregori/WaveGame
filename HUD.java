package com.tutorial.main;

import java.awt.*;

public class HUD {

    public int bounds = 0;
    public static float hp = 100f;
    private float greenValue = 255f;

    private int score = 0;
    private int trueScore = 0;
    private int level = 1;

    public void tick(){
        hp = Game.clamp(hp, 0, 100+(bounds/2));
        greenValue = hp*2;
        greenValue = Game.clamp(greenValue, 0, 255);
        score++;
        trueScore++;
    }

    public void render(Graphics g){
        g.setColor(Color.gray);//lost health
        g.fillRect(15,15,200+bounds,32);

        g.setColor(new Color(75, (int)greenValue, 0));//health
        g.fillRect(15,15,(int)hp*2,32);

        g.setColor(Color.white);//box
        g.drawRect(15,15,200+bounds,32);

        g.drawString("Score: " + score, 15, 64);
        g.drawString("Level: " + level, 15, 80);
        g.drawString("Hit Space for Shop", 15, 94);
    }

    public void setScore(int score){
        this.score = score;
    }
    public int getScore(){
        return score;
    }
    public void setLevel(int level){
        this.level = level;
    }
    public int getLevel(){
        return level;
    }
    public int getTrueScore(){
        return trueScore;
    }
}

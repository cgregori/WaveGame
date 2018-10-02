package com.tutorial.main;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Shop extends MouseAdapter {

    private Handler handler;
    private HUD hud;
    private int B1 = 1000, B2 = 1000, B3 = 1000;

    public Shop(Handler handler, HUD hud){
        this.handler = handler;
        this.hud = hud;
    }

    public void render(Graphics g){
        g.setColor(Color.white);
        g.setFont(new Font("arial", 0, 48));
        g.drawString("SHOP", Game.WIDTH/2 - 100, 50);

        g.setFont(new Font("arial", 0, 12));

        //Box 1
        g.drawString("Upgrade Health", 110, 120);
        g.drawString("Cost: " + B1, 110, 140);
        g.drawRect(100, 100, 100, 80);

        //Box 3
        g.drawString("Upgrade Speed", 260, 120);
        g.drawString("Cost: " + B2, 260, 140);
        g.drawRect(250, 100, 100, 80);

        //Box 2
        g.drawString("Refill Health", 410, 120);
        g.drawString("Cost: " + B3, 410, 140);
        g.drawRect(400, 100, 100, 80);

        g.drawString("Score: " + hud.getScore(), Game.WIDTH/2-50, 300);
        g.drawString("Press Space to go back", Game.WIDTH/2-50, 330);

    }

    public void mousePressed(MouseEvent e){
        int mx = e.getX();
        int my = e.getY();

        //Health Upgrade
        if(mx>=100 && mx<= 200){
            if(my>=100 && my<= 180){
                if(hud.getScore() >= B1){
                    hud.setScore(hud.getScore()-B1);
                    B1+=1000;
                    hud.bounds+=20;
                    hud.hp = 100 + (hud.bounds/2);
                }
            }
        }

        //Speed Upgrade
        if(mx>=250 && mx<= 350){
            if(my>=100 && my<= 180){
                if(hud.getScore() >= B2){
                    hud.setScore(hud.getScore()-B2);
                    B2+=1000;
                    handler.speed++;
                }
            }
        }

        //Health Refill
        if(mx>=400 && mx<= 500){
            if(my>=100 && my<= 180){
                if(hud.getScore() >= B3){
                    hud.setScore(hud.getScore()-B3);
                    hud.hp = 100 + (hud.bounds/2);
                }
            }
        }

    }

}

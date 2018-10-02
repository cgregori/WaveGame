package com.tutorial.main;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Menu extends MouseAdapter {

    private Game game;
    private Handler handler;
    private Random r = new Random();
    private HUD hud;

    public Menu(Game game, Handler handler, HUD hud){
        this.game = game;
        this.handler = handler;
        this.hud = hud;
    }

    public void mousePressed(MouseEvent e){
        int mx = e.getX();
        int my = e.getY();

        if(game.gameSTATE == Game.STATE.Menu){
            if(mouseOver(mx, my, 210, 150, 200, 64)){//Play Button
                AudioPlayer.getSound("menu_sound").play();
                game.gameSTATE=Game.STATE.Select;
                return;
            }

            if(mouseOver(mx, my, 210, 250, 200, 64)) {//Help Button
                AudioPlayer.getSound("menu_sound").play();
                game.gameSTATE = Game.STATE.Help;
            }

            if(mouseOver(mx, my, 210, 350, 200, 64)) {//Quit Button
                AudioPlayer.getSound("menu_sound").play();
                System.exit(1);
            }
        }

        if(game.gameSTATE == Game.STATE.Select){
            if(mouseOver(mx, my, 210, 150, 200, 64)){//Normal Button
                AudioPlayer.getSound("menu_sound").play();
                game.gameSTATE = Game.STATE.Game;
                handler.clearEnemies();
                handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));//Player Spawn
                handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH-40), r.nextInt(Game.HEIGHT-40), ID.BasicEnemy, handler));//Enemy
                game.diff = 0;
            }

            if(mouseOver(mx, my, 210, 250, 200, 64)) {//Hard Button
                AudioPlayer.getSound("menu_sound").play();
                game.gameSTATE = Game.STATE.Game;
                handler.clearEnemies();
                handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));//Player Spawn
                handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH-40), r.nextInt(Game.HEIGHT-40), ID.BasicEnemy, handler));//Enemy
                game.diff=1;
            }

            if(mouseOver(mx, my, 210, 350, 200, 64)) {//Back Button | Select
                AudioPlayer.getSound("menu_sound").play();
                    game.gameSTATE = Game.STATE.Menu;
                    return;
            }
        }

        if(game.gameSTATE == Game.STATE.Help){//Back Button | Help
            AudioPlayer.getSound("menu_sound").play();
            if(mouseOver(mx, my, 210, 350, 200, 64)) {
                game.gameSTATE = Game.STATE.Menu;
                return;
            }
        }

        if(game.gameSTATE == Game.STATE.End){//Try Again
            if(mouseOver(mx, my, 210, 350, 200, 64)) {
                AudioPlayer.getSound("menu_sound").play();
                game.gameSTATE = Game.STATE.Menu;
                hud.setLevel(1);
                hud.setScore(0);
                hud.hp = 100;
                handler.speed = 5;
                hud.bounds = 0;
            }
        }

    }

    public void mouseReleased(MouseEvent e){

    }

    private boolean mouseOver(int mx, int my, int x, int y, int width, int height){
        return (mx>x && mx<x+width && my>y && my<y+height);
    }

    public void tick(){

    }

    public void render(Graphics g){
        Font font = new Font("arial", 1, 50);
        Font font2 = new Font("arial", 1, 30);
        Font font3 = new Font("arial", 1, 20);
        if(game.gameSTATE == Game.STATE.Menu) {

            g.setColor(Color.white);
            g.setFont(font);
            g.drawString("Wave", 240, 70);

            g.setFont(font2);
            g.drawRect(210, 150, 200, 64);//Play
            g.drawString("Play", 280, 190);

            g.drawRect(210, 250, 200, 64);//Help
            g.drawString("Help", 280, 290);

            g.drawRect(210, 350, 200, 64);//Quit
            g.drawString("Quit", 280, 390);
        }

        else if(game.gameSTATE == Game.STATE.Help){
            g.setColor(Color.white);
            g.setFont(font);
            g.drawString("Help", 240, 70);

            g.setFont(font3);
            g.drawString("Use the WASD keys to move player and dodge enemies", 50, 200);

            g.setFont(font2);
            g.drawRect(210, 350, 200, 64);//Back
            g.drawString("Back", 280, 390);
        }

        else if(game.gameSTATE == Game.STATE.End){
            g.setColor(Color.white);
            g.setFont(font);
            g.drawString("Game Over", 180, 70);

            g.setFont(font3);
            g.drawString("You lost with a score of " + hud.getTrueScore(), 175, 200);

            g.setFont(font2);
            g.drawRect(210, 350, 200, 64);//Back
            g.drawString("Try Again", 245, 390);
        }

        else if(game.gameSTATE == Game.STATE.Select) {

            g.setColor(Color.white);
            g.setFont(font);
            g.drawString("Select Difficulty", 140, 70);

            g.setFont(font2);
            g.drawRect(210, 150, 200, 64);//Play
            g.drawString("Normal", 260, 190);

            g.drawRect(210, 250, 200, 64);//Help
            g.drawString("Hard", 280, 290);

            g.drawRect(210, 350, 200, 64);//Quit
            g.drawString("Back", 280, 390);
        }

    }

}

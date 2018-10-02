package com.tutorial.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

    private Handler handler;
    private boolean[] keyDown = new boolean[4];
    private Game game;

    public KeyInput(Handler handler, Game game){
        this.handler = handler;
        this.game = game;
        for(int i=0;i<keyDown.length;i++)
            keyDown[i]=false; //0=W , 1 = S, 2 = D, 3 = A
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key =  e.getExtendedKeyCode();

        for(int i=0; i<handler.object.size();i++){
            GameObject tempObject =handler.object.get(i);

            if(tempObject.getID()==ID.Player){
                //key events for player 1
                if(key == KeyEvent.VK_W) {
                    tempObject.setVelY(-handler.speed);
                    keyDown[0] = true;
                }
                if(key == KeyEvent.VK_S){
                    tempObject.setVelY(handler.speed);
                    keyDown[1] = true;
                }
                if(key == KeyEvent.VK_D){
                    tempObject.setVelX(handler.speed);
                    keyDown[2] = true;
                }
                if(key == KeyEvent.VK_A){
                    tempObject.setVelX(-handler.speed);
                    keyDown[3] = true;
                }
            }
        }

        if(key == KeyEvent.VK_P) {
            if(game.gameSTATE == Game.STATE.Game){
                if(Game.paused)
                    Game.paused = false;
                else
                    Game.paused = true;

            }
        }

        if(key == KeyEvent.VK_ESCAPE) {
            System.exit(1);
        }

        if(key==KeyEvent.VK_SPACE){
            if(Game.gameSTATE == Game.STATE.Game)
                Game.gameSTATE = Game.STATE.Shop;
            else if(Game.gameSTATE == Game.STATE.Shop)
                Game.gameSTATE = Game.STATE.Game;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        for(int i=0; i<handler.object.size();i++){
            GameObject tempObject =handler.object.get(i);

            if(tempObject.getID()==ID.Player){
                //key events for player 1
                if(key == KeyEvent.VK_W)
                    keyDown[0]=false;
                if(key == KeyEvent.VK_S)
                    keyDown[1]=false;
                if(key == KeyEvent.VK_D)
                    keyDown[2]=false;
                if(key == KeyEvent.VK_A)
                    keyDown[3]=false;

                //Horizontal Movement
                if(!keyDown[0]&&!keyDown[1])
                    tempObject.setVelY(0);
                //Vertical Movement
                if(!keyDown[2]&&!keyDown[3])
                    tempObject.setVelX(0);
            }
            }
    }

}

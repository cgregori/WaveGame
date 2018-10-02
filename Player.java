package com.tutorial.main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

import static com.tutorial.main.Game.HEIGHT;
import static com.tutorial.main.Game.WIDTH;
import static com.tutorial.main.Game.sprite_Sheet;

public class Player extends GameObject{

    Random r = new Random();
    Handler handler;
    //private BufferedImage player_image;

    public Player(int x, int y, ID id, Handler handler){
        super(x,y,id);
        this.handler = handler;

        /*SpriteSheet ss = new SpriteSheet(Game.sprite_Sheet);
        player_image = ss.grabImage(1, 1, 32, 32);*/
    }

    public Rectangle getBounds(){
        return new Rectangle((int)x,(int)y,32,32);
    }

    @Override
    public void tick() {
        x+=velX;
        y+=velY;

        x=Game.clamp(x,0,Game.WIDTH-46);
        y=Game.clamp(y,0,Game.HEIGHT-69);

        collision();
        //handler.addObject(new Trail(x, y, ID.Trail, Color.white, 32,32,.2f, handler));
    }

    private void collision(){
        for(int i = 0;i<handler.object.size();i++){
            GameObject tempObject = handler.object.get(i);
            if(tempObject.getID()==ID.BasicEnemy || tempObject.getID()==ID.FastEnemy || tempObject.getID()==ID.SmartEnemy){
                //tempObject is now always Enemy if true
                if(getBounds().intersects(tempObject.getBounds())) {
                    //collision code
                    HUD.hp-=2;
                }
            }
            else if(tempObject.getID()==ID.BossEnemy){
                if(getBounds().intersects(tempObject.getBounds())) {
                    //collision code
                    HUD.hp=0;
                }
            }
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillRect((int)x,(int)y,32,32);
        //g.drawImage(player_image, (int)x, (int)y, null);
    }


}

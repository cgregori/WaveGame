package com.tutorial.main;

import java.awt.*;
import java.awt.image.BufferedImage;

public class FastEnemy extends GameObject{

    private Handler handler;
    //private BufferedImage enemy_image;

    public FastEnemy(int x, int y, ID id, Handler handler){
        super(x,y,id);
        velX=2;
        velY=9;
        this.handler = handler;

        //SpriteSheet ss= new SpriteSheet(Game.sprite_Sheet);
        //enemy_image = ss.grabImage(3, 1, 16, 16);
    }

    public Rectangle getBounds(){
        return new Rectangle((int)x,(int)y,16,16);
    }

    @Override
    public void tick() {
        x+=velX;
        y+=velY;
        if(y<=0 | y>= Game.HEIGHT - 48)
            velY*=-1;
        if(x<=0 | x>= Game.WIDTH - 32)
            velX*=-1;
        handler.addObject(new Trail(x, y, ID.Trail, Color.cyan, 16,16,.05f, handler));
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.cyan);
        g.fillRect((int)x, (int)y, 16, 16);
        //g.drawImage(enemy_image, (int)x, (int)y, null);
    }
}

package com.tutorial.main;

import java.awt.*;
import java.util.Random;

public class MenuParticle extends GameObject{

    private Handler handler;
    Random r = new Random();
    private Color color;

    public MenuParticle(int x, int y, ID id, Handler handler){
        super(x,y,id);

        velX = (r.nextInt(14) -7);
        velY = (r.nextInt(14) -7);
        if(velX==0)
            velX=1;
        if(velY==0)
            velY = 1;

        this.handler = handler;

        color = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));

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
        handler.addObject(new Trail(x, y, ID.Trail, color, 16,16,.05f, handler));
    }

    @Override
    public void render(Graphics g) {
        g.setColor(color);
        g.fillRect((int)x, (int)y, 16, 16);
    }
}

package com.tutorial.main;

import java.awt.*;
import java.util.Random;

public class BossEnemyBullet extends GameObject{

    private Handler handler;
    private Game game;
    private Random r = new Random();

    public BossEnemyBullet(float x, float y, ID id, Handler handler, Game game){
        super(x,y,id);
        this.handler = handler;
        this.game = game;
        velX=(r.nextInt(5 - -5) + -5);
        velY=5;
    }

    public Rectangle getBounds(){
        return new Rectangle((int)x,(int)y,16,16);
    }

    @Override
    public void tick() {
        x+=velX;
        y+=velY;
        if(y>= Game.HEIGHT)
            handler.removeObject(this);

        // handler.addObject(new Trail(x, y, ID.Trail, Color.pink, 16,16,.05f, handler));
    }

    @Override
    public void render(Graphics g) {
        if(game.diff==0)
            g.setColor(Color.yellow);
        else
            g.setColor(Color.orange);
        g.fillRect((int)x, (int)y, 16, 16);
    }
}

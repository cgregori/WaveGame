package com.tutorial.main;

import java.awt.*;
import java.util.Random;

public class BossEnemy extends GameObject{

    private Handler handler;
    private int timer = 80;
    private int timer2=50;
    private Random r = new Random();
    private Game game;

    public BossEnemy(float x, float y, ID id, Handler handler, Game game){
        super(x,y,id);
        velX=0;
        velY=2;
        this.game = game;
        this.handler = handler;
    }

    public Rectangle getBounds(){
        return new Rectangle((int)x,(int)y,96,96);
    }

    @Override
    public void tick() {
        x+=velX;
        y+=velY;

        if(timer<=0)
            velY=0;
        else
            timer--;

        if(timer<=0)
            timer2--;
        if(timer2<=0){//already on screen
            if(velX==0)
                velX=3;
            if(velX>0)
                velX+=.01f;
            else if(velX<0)
                velX-=.01f;
            velX = Game.clamp(velX, -50, 50);
            int spawn = r.nextInt(10);
            if(game.diff==0){
                if(spawn<=1)//20% chance each tick
                    handler.addObject(new BossEnemyBullet((int) x+48, (int) y+48, ID.BasicEnemy, handler, game));
            }
            else {
                if (spawn <= 2)//30% chance each tick
                    handler.addObject(new BossEnemyBullet((int) x + 48, (int) y + 48, ID.BasicEnemy, handler, game));
            }
        }

        if(x<=0 | x>= Game.WIDTH - 108)
            velX*=-1;

        handler.addObject(new Trail(x, y, ID.Trail, Color.red, 96,96,.1f, handler));
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect((int)x, (int)y, 96, 96);
    }
}

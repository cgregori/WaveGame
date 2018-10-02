package com.tutorial.main;

import org.newdawn.slick.openal.Audio;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Game extends Canvas implements Runnable{

    protected static final int WIDTH = 640, HEIGHT = WIDTH/12*9;

    private Thread thread;
    private boolean running = false;
    public static boolean paused = false;
    public int diff = 0;//0 = Normal | 1 = Hard

    public static BufferedImage sprite_Sheet;

    private Random r;
    private Handler handler;
    private HUD hud;
    private Spawn spawner;
    private Menu menu;
    private Shop shop;

    public enum STATE{
        Menu,
        Help,
        Game,
        Shop,
        Select,
        End
    };

    public static STATE gameSTATE = STATE.Menu;

    public Game(){

       /* BufferedImageLoader loader = new BufferedImageLoader();
        try{
            sprite_Sheet = loader.loadImage("/sprite_sheet.png");
            System.out.println("loaded");
        }
        catch(Exception e){
            e.printStackTrace();
        }*/

        handler = new Handler();
        hud = new HUD();
        shop = new Shop(handler, hud);
        menu = new Menu(this, handler, hud);
        this.addKeyListener(new KeyInput(handler, this));
        this.addMouseListener(menu);
        this.addMouseListener(shop);

        AudioPlayer.load();
        AudioPlayer.getMusic("music").loop();

        new Window(WIDTH, HEIGHT, "Wave", this);

        spawner = new Spawn(handler, hud, this);
        r = new Random();

        for(int i =0;i<15;i++){
            handler.addObject(new MenuParticle(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.MenuParticle, handler));
        }


    }

    public synchronized void start(){
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop(){
        try{
            thread.join();
            running = false;
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void run(){ //Variable Timestep (I think)
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime)/ns;
            lastTime = now;
            while(delta>= 1){
                tick();
                delta--;
            }
            if(running)
                render();
            frames++;

            if(System.currentTimeMillis()-timer >1000){
                timer+=1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }

    private void tick(){
        if(gameSTATE == STATE.Game){
            if(!paused){
                handler.tick();
                hud.tick();
                spawner.tick();

                if(HUD.hp<=0){//Game Over
                    HUD.hp=100;
                    gameSTATE = STATE.End;
                    handler.clearEnemies();
                    for(int i =0;i<15;i++){
                        handler.addObject(new MenuParticle(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.MenuParticle, handler));
                    }
                }
            }

        }

        else if(gameSTATE==STATE.Menu || gameSTATE==STATE.End || gameSTATE == STATE.Select){
            menu.tick();
            handler.tick();
        }
    }

    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.BLACK);
        g.fillRect(0,0,WIDTH, HEIGHT);

        if(gameSTATE == STATE.Game){
            hud.render(g);
            handler.render(g);
        }

        if(paused){
            g.setColor(Color.white);
            g.setFont(new Font("arial", 1, 50));
            g.drawString("PAUSED", 240, 170);
        }

        else if(gameSTATE == STATE.Shop){
            shop.render(g);
        }

        else if(gameSTATE == STATE.Menu || gameSTATE == STATE.Help || gameSTATE == STATE.End || gameSTATE == STATE.Select){
            menu.render(g);
            handler.render(g);
        }

        g.dispose();
        bs.show();
    }

    public static float clamp(float var, float min, float max){
        if(var>=max)
            return var = max;
        else if(var<=min)
            return var=min;
        else
            return var;
    }

    public static void main(String args[]){
        new Game();
    }
}

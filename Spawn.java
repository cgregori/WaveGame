package com.tutorial.main;

import java.util.Random;

public class Spawn {
    private Handler handler;
    private HUD hud;
    private Random r = new Random();
    private Game game;

    public Spawn(Handler handler,HUD hud, Game game){
        this.handler = handler;
        this.hud = hud;
        this.game = game;
    }

    public void tick(){
        if(game.diff == 0){
            if(hud.getScore()%100==0) {
                hud.setLevel(hud.getLevel()+1);

                if(hud.getLevel()==2){//Basic
                    handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH-48), r.nextInt(Game.HEIGHT-32), ID.BasicEnemy, handler));
                }
                else if(hud.getLevel()==3){//Basic
                    handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH-48), r.nextInt(Game.HEIGHT-32), ID.BasicEnemy, handler));
                }
                else if(hud.getLevel()==4){//Fast
                    handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH-48), r.nextInt(Game.HEIGHT-32), ID.FastEnemy, handler));
                }
                else if(hud.getLevel()==5){//Smart
                    handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH-48), r.nextInt(Game.HEIGHT-32), ID.SmartEnemy, handler));
                }
                else if(hud.getLevel()==6){//Basic
                    handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH-48), r.nextInt(Game.HEIGHT-32), ID.BasicEnemy, handler));
                }
                else if(hud.getLevel()==7){//Fast
                    handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH-48), r.nextInt(Game.HEIGHT-32), ID.FastEnemy, handler));
                }
                else if(hud.getLevel()==8){//Smart
                    handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH-48), r.nextInt(Game.HEIGHT-32), ID.SmartEnemy, handler));
                }
                else if(hud.getLevel()==9){//Fast
                    handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH-48), r.nextInt(Game.HEIGHT-32), ID.FastEnemy, handler));
                }
                else if(hud.getLevel()==10){
                    handler.clearEnemies();
                    handler.addObject(new BossEnemy((Game.WIDTH/2)-48, -140, ID.BossEnemy, handler, game));
                }
            }
        }

        if(game.diff == 1){
            if(hud.getScore()%200==0) {
                hud.setLevel(hud.getLevel()+1);

                if(hud.getLevel()==2){//Basic
                    handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH-48), r.nextInt(Game.HEIGHT-32), ID.BasicEnemy, handler));
                }
                else if(hud.getLevel()==3){//Basic
                    handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH-48), r.nextInt(Game.HEIGHT-32), ID.BasicEnemy, handler));
                }
                else if(hud.getLevel()==4){//Fast
                    handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH-48), r.nextInt(Game.HEIGHT-32), ID.FastEnemy, handler));
                }
                else if(hud.getLevel()==5){//Smart
                    handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH-48), r.nextInt(Game.HEIGHT-32), ID.SmartEnemy, handler));
                }
                else if(hud.getLevel()==6){//Basic
                    handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH-48), r.nextInt(Game.HEIGHT-32), ID.BasicEnemy, handler));
                }
                else if(hud.getLevel()==7){//Fast
                    handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH-48), r.nextInt(Game.HEIGHT-32), ID.FastEnemy, handler));
                }
                else if(hud.getLevel()==8){//Smart
                    handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH-48), r.nextInt(Game.HEIGHT-32), ID.SmartEnemy, handler));
                }
                else if(hud.getLevel()==9){//Fast
                    handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH-48), r.nextInt(Game.HEIGHT-32), ID.FastEnemy, handler));
                }
                else if(hud.getLevel()==10){
                    handler.clearEnemies();
                    handler.addObject(new BossEnemy((Game.WIDTH/2)-48, -140, ID.BossEnemy, handler, game));
                }
            }
        }

    }
}

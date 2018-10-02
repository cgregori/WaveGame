package com.tutorial.main;

import java.awt.*;
import java.util.LinkedList;

public class Handler {
    protected LinkedList<GameObject> object = new LinkedList<GameObject>();
    public int speed = 5;

    public void tick(){
        for(int i=0;i<object.size();i++){
            GameObject tempObject = object.get(i);
            tempObject.tick();
        }
    }

    public void render(Graphics g){
        try
        {
            for(int i=0;i<object.size();i++){
                GameObject tempObject = object.get(i);
                tempObject.render(g);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void clearEnemies(){
        for(int i=0;i<object.size();i++){
            GameObject tempObject = object.get(i);
            if(object.get(i).getID()!=ID.Player){
                object.remove(tempObject);
                i--;
            }
        }
        if(Game.gameSTATE == Game.STATE.End)
            object.remove();
    }

    public void addObject(GameObject object){
        this.object.add(object);
    }
    public void removeObject(GameObject object){
        this.object.remove(object);
    }

}

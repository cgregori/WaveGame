package com.tutorial.main;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class BufferedImageLoader {

    BufferedImage image;

    public BufferedImage loadImage(String path){
        try{
            image = ImageIO.read(getClass().getResource(path));
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return image;
    }

}

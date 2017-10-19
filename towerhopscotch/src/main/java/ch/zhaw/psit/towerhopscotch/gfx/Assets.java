package ch.zhaw.psit.towerhopscotch.gfx;

import java.awt.image.BufferedImage;

public class Assets {

    private static final int width = 32, height = 32;

    public static BufferedImage fortress;

    public static void init(){
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/fortress.png"));
        fortress = sheet.crop(0,0, width, height);
    }

}
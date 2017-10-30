package ch.zhaw.psit.towerhopscotch.GUI;

import ch.zhaw.psit.towerhopscotch.util.ImageUtil;

import java.awt.image.BufferedImage;
import java.util.HashMap;

public class Assets {

    private static final int height = 16, width = 16;
    public static HashMap<String, BufferedImage> tiles = new HashMap<String, BufferedImage>();
    public static BufferedImage enemy;

    public static void initialize() {
        BufferedImage spriteSheet = ImageUtil.loadImage("/textures/sprite_sheet2.png");

        // Get tile images
        tiles.put("grass", spriteSheet.getSubimage(2 * width, 5 * height, width, height));
        tiles.put("long_grass", spriteSheet.getSubimage(3 * width, 5 * height, width, height));
        tiles.put("dirt", spriteSheet.getSubimage(width, 5 * height, width, height));
        tiles.put("sand", spriteSheet.getSubimage(4 * width, 5 * height, width, height));
        tiles.put("leaves", spriteSheet.getSubimage(5 * width, 5 * height, width, height));
        tiles.put("stone", spriteSheet.getSubimage(6 * width, 5 * height, width, height));
        tiles.put("tree", spriteSheet.getSubimage(7 * width, 5 * height, width, height));
        tiles.put("dead_tree", spriteSheet.getSubimage(8 * width, 5 * height, width, height));
        tiles.put("unknown", spriteSheet.getSubimage(8 * width, 24 * height, width, height));
        tiles.put("start", spriteSheet.getSubimage(0, 24 * height, width, height));
        tiles.put("end", spriteSheet.getSubimage(9 * width, 24 * height, width, height));

        // Enemy
        enemy = spriteSheet.getSubimage(4 * width, 22 * height, width, height);
    }
}
package ch.zhaw.psit.towerhopscotch.GUI;

import ch.zhaw.psit.towerhopscotch.util.ImageUtil;

import java.awt.image.BufferedImage;
import java.util.HashMap;

public class Assets {

    private static final int height = 16, width = 16;
    public static HashMap<String, BufferedImage> tiles = new HashMap<String, BufferedImage>();
    public static HashMap<String, BufferedImage> enemies = new HashMap<String, BufferedImage>();

    public static void initialize() {
        BufferedImage spriteSheet = ImageUtil.loadImage("/textures/sprite_sheet3.png");

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
        tiles.put("fortress", spriteSheet.getSubimage(8 * width, 7 * height, width, height));

        // Get enemy sprites
        enemies.put("rat", spriteSheet.getSubimage(0, 21 * height, width, height));
        enemies.put("bat", spriteSheet.getSubimage(width, 21 * height, width, height));
        enemies.put("spider", spriteSheet.getSubimage(4 * width, 21 * height, width, height));
        enemies.put("slime", spriteSheet.getSubimage(0, 22 * height, width, height));
        enemies.put("goblin", spriteSheet.getSubimage(4 * width, 22 * height, width, height));
        enemies.put("imp", spriteSheet.getSubimage(width, 22 * height, width, height));
        enemies.put("skeleton", spriteSheet.getSubimage(2 * width, 22 * height, width, height));
    }
}
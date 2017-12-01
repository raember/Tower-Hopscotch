package ch.zhaw.psit.towerhopscotch.GUI;

import ch.zhaw.psit.towerhopscotch.util.FontLoader;
import ch.zhaw.psit.towerhopscotch.util.ImageUtil;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public class Assets {

    private static final int height = 16, width = 16;
    public static HashMap<String, BufferedImage> earthTiles = new HashMap<String, BufferedImage>();
    public static HashMap<String, BufferedImage> hellTiles = new HashMap<String, BufferedImage>();
    public static HashMap<String, BufferedImage> heavenTiles = new HashMap<String, BufferedImage>();
    public static HashMap<String, BufferedImage> enemies = new HashMap<String, BufferedImage>();
    public static HashMap<String, BufferedImage> towers = new HashMap<String, BufferedImage>();
    public static BufferedImage heart;
    public static BufferedImage treasure;

    public static Font font16;
    public static Font font32;
    public static Font font128;

    public static void initialize() {
        BufferedImage spriteSheet = ImageUtil.loadImage("/textures/earth.png");

        // Get tile images
        earthTiles.put("grass", spriteSheet.getSubimage(2 * width, 5 * height, width, height));
        earthTiles.put("long_grass", spriteSheet.getSubimage(3 * width, 5 * height, width, height));
        earthTiles.put("dirt", spriteSheet.getSubimage(width, 5 * height, width, height));
        earthTiles.put("sand", spriteSheet.getSubimage(4 * width, 5 * height, width, height));
        earthTiles.put("leaves", spriteSheet.getSubimage(5 * width, 5 * height, width, height));
        earthTiles.put("stone", spriteSheet.getSubimage(6 * width, 6 * height, width, height));
        earthTiles.put("tree", spriteSheet.getSubimage(7 * width, 5 * height, width, height));
        earthTiles.put("dead_tree", spriteSheet.getSubimage(8 * width, 5 * height, width, height));
        earthTiles.put("unknown", spriteSheet.getSubimage(8 * width, 24 * height, width, height));
        earthTiles.put("fortress", spriteSheet.getSubimage(8 * width, 7 * height, width, height));
        earthTiles.put("temple", spriteSheet.getSubimage(7 * width, 6 * height, width, height));

        // Get enemy sprites
        enemies.put("rat", spriteSheet.getSubimage(0, 21 * height, width, height));
        enemies.put("bat", spriteSheet.getSubimage(width, 21 * height, width, height));
        enemies.put("spider", spriteSheet.getSubimage(4 * width, 21 * height, width, height));
        enemies.put("slime", spriteSheet.getSubimage(0, 22 * height, width, height));
        enemies.put("goblin", spriteSheet.getSubimage(4 * width, 22 * height, width, height));
        enemies.put("imp", spriteSheet.getSubimage(width, 22 * height, width, height));
        enemies.put("skeleton", spriteSheet.getSubimage(2 * width, 22 * height, width, height));

        // Get tower sprites
        towers.put("tripleTower", spriteSheet.getSubimage(6 * width, 7 * height, width, height));
        towers.put("doubleTower", spriteSheet.getSubimage(5 * width, 7 * height, width, height));
        towers.put("simpleTower", spriteSheet.getSubimage(4 * width, 7 * height, width, height));

        // Various other sprites
        heart = spriteSheet.getSubimage(0, 25 * height, width, height);
        treasure = spriteSheet.getSubimage(4 * width, 8 * height, width, height);

        // Load fonts
        font16 = FontLoader.loadFont("/fonts/slkscr.ttf", 16);
        font32 = FontLoader.loadFont("/fonts/slkscr.ttf", 32);
        font128 = FontLoader.loadFont("/fonts/slkscr.ttf", 128);


        spriteSheet = ImageUtil.loadImage("/textures/hell.png");

        // Get tile images
        hellTiles.put("grass", spriteSheet.getSubimage(2 * width, 5 * height, width, height));
        hellTiles.put("long_grass", spriteSheet.getSubimage(3 * width, 5 * height, width, height));
        hellTiles.put("dirt", spriteSheet.getSubimage(width, 5 * height, width, height));
        hellTiles.put("sand", spriteSheet.getSubimage(4 * width, 5 * height, width, height));
        hellTiles.put("leaves", spriteSheet.getSubimage(5 * width, 5 * height, width, height));
        hellTiles.put("stone", spriteSheet.getSubimage(6 * width, 6 * height, width, height));
        hellTiles.put("tree", spriteSheet.getSubimage(7 * width, 5 * height, width, height));
        hellTiles.put("dead_tree", spriteSheet.getSubimage(8 * width, 5 * height, width, height));
        hellTiles.put("unknown", spriteSheet.getSubimage(8 * width, 24 * height, width, height));
        hellTiles.put("fortress", spriteSheet.getSubimage(8 * width, 7 * height, width, height));
        hellTiles.put("temple", spriteSheet.getSubimage(7 * width, 6 * height, width, height));


        spriteSheet = ImageUtil.loadImage("/textures/heaven.png");

        // Get tile images
        heavenTiles.put("grass", spriteSheet.getSubimage(2 * width, 5 * height, width, height));
        heavenTiles.put("long_grass", spriteSheet.getSubimage(3 * width, 5 * height, width, height));
        heavenTiles.put("dirt", spriteSheet.getSubimage(width, 5 * height, width, height));
        heavenTiles.put("sand", spriteSheet.getSubimage(4 * width, 5 * height, width, height));
        heavenTiles.put("leaves", spriteSheet.getSubimage(5 * width, 5 * height, width, height));
        heavenTiles.put("stone", spriteSheet.getSubimage(6 * width, 6 * height, width, height));
        heavenTiles.put("tree", spriteSheet.getSubimage(7 * width, 5 * height, width, height));
        heavenTiles.put("dead_tree", spriteSheet.getSubimage(8 * width, 5 * height, width, height));
        heavenTiles.put("unknown", spriteSheet.getSubimage(8 * width, 24 * height, width, height));
        heavenTiles.put("fortress", spriteSheet.getSubimage(8 * width, 7 * height, width, height));
        heavenTiles.put("temple", spriteSheet.getSubimage(7 * width, 6 * height, width, height));
    }
}
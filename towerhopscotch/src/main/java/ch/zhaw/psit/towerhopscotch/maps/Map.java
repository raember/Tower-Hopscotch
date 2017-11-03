package ch.zhaw.psit.towerhopscotch.maps;

import ch.zhaw.psit.towerhopscotch.Game;
import ch.zhaw.psit.towerhopscotch.models.entities.enemies.*;
import ch.zhaw.psit.towerhopscotch.models.tiles.Tile;
import ch.zhaw.psit.towerhopscotch.models.tiles.TileList;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Map {

    private Game game;

    private int width, height;
    private int[][] tiles;

    private int startX;
    private int startY;

    private ArrayList<Enemy> enemies = new ArrayList<Enemy>();

    public Map(Game game, String filePath) {
        initializeMap(filePath);

        this.game = game;

        generateEnemies(10);
    }

    private void generateEnemies(int count) {
        // Enemies are generated below the screen all with the same x position as the starting tile
        // but varying y positions.
        Random random = new Random();
        for (int i = 1; i <= count; i++) {
            int enemyType = random.nextInt(7 - 1 + 1) + 1;
            int startingHeight = startY + (i * 64) + (2 * (random.nextInt(10 + 10 + 1) - 10));
            switch (enemyType) {
                case 1:
                    enemies.add(new Rat(game, startX, startingHeight));
                    break;
                case 2:
                    enemies.add(new Bat(game, startX, startingHeight));
                    break;
                case 3:
                    enemies.add(new Skeleton(game, startX, startingHeight));
                    break;
                case 4:
                    enemies.add(new Spider(game, startX, startingHeight));
                    break;
                case 5:
                    enemies.add(new Goblin(game, startX, startingHeight));
                    break;
                case 6:
                    enemies.add(new Slime(game, startX, startingHeight));
                    break;
                case 7:
                    enemies.add(new Imp(game, startX, startingHeight));
                    break;
            }

        }
    }

    public void update() {
        // Update enemies
        Iterator<Enemy> iterator = enemies.iterator();
        while (iterator.hasNext()) {
            Enemy enemy = iterator.next();
            enemy.update();

            // Remove enemy if it has reached the players fortress
            if (enemy.reachedDestination())
                iterator.remove();
        }
    }

    public void render(Graphics g) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                TileList.getTile(tiles[x][y]).render(g, x * Tile.TILE_WIDTH, y * Tile.TILE_HEIGHT);
            }
        }

        for (Enemy enemy : enemies)
            enemy.render(g);
    }

    public boolean isPath(float x, float y) {
        if (!isOnMap(x, y))
            return false;

        Tile tile = TileList.getTile(tiles[(int) x / Tile.TILE_WIDTH][(int) y / Tile.TILE_HEIGHT]);
        return tile.isPath();
    }

    public boolean isFortress(float x, float y) {
        if (!isOnMap(x, y))
            return false;

        Tile tile = TileList.getTile(tiles[(int) x / Tile.TILE_WIDTH][(int) y / Tile.TILE_HEIGHT]);
        return tile.isFortress();
    }

    public boolean isBeneathMap(float x, float y) {
        return (y > Tile.TILE_HEIGHT * height) && (x == startX);
    }

    private boolean isOnMap(float x, float y) {
        return !((x < 0) || (x >= Tile.TILE_WIDTH * width) || (y < 0) || (y >= Tile.TILE_HEIGHT * height));
    }

    private void initializeMap(String filePath) {
        StringBuilder fileContents = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = br.readLine()) != null) {
                line = line + "\n";
                fileContents.append(line);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] tokens = fileContents.toString().split("\\s+");
        width = Integer.parseInt(tokens[0]);
        height = Integer.parseInt(tokens[1]);

        tiles = new int[width][height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int tileId = Integer.parseInt(tokens[(x + y * width) + 2]);
                tiles[x][y] = tileId;
                if (tileId == 7) {
                    startX = x * Tile.TILE_WIDTH;
                    startY = y * Tile.TILE_HEIGHT;
                }
            }
        }
    }
}

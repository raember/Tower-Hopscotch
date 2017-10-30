package ch.zhaw.psit.towerhopscotch.maps;

import ch.zhaw.psit.towerhopscotch.Game;
import ch.zhaw.psit.towerhopscotch.models.entities.enemies.Enemy;
import ch.zhaw.psit.towerhopscotch.models.entities.enemies.Goblin;
import ch.zhaw.psit.towerhopscotch.models.tiles.Tile;
import ch.zhaw.psit.towerhopscotch.models.tiles.TileList;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Map {

    private Game game;

    private int width, height;
    private int[][] tiles;

    private int startX;
    private int startY;
    private int endX;
    private int endY;

    private ArrayList<Enemy> enemies = new ArrayList<Enemy>();

    public Map(Game game, String filePath) {
        initializeMap(filePath);

        this.game = game;
        enemies.add(new Goblin(game, startX, startY));
    }

    public void update() {
        if(game.getMouseManager().lastClickX != 0 && game.getMouseManager().lastClickY != 0) {
            int x = game.getMouseManager().lastClickX;
            int y = game.getMouseManager().lastClickY;

            int xIndex = (int) x / Tile.TILE_WIDTH;
            int yIndex = (int) y / Tile.TILE_HEIGHT;

            System.out.println("PIXELS: " + x + "/" + y);
            System.out.println("MAP GRID: " + xIndex + "/" + yIndex);
            System.out.println(isPath(x, y));
            System.out.println("------------------------------------");
            game.getMouseManager().resetClick();
        }

        for (Enemy enemy : enemies) {
            enemy.update();
        }
    }

    public void render(Graphics g) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                TileList.getTile(tiles[x][y]).render(g, x * Tile.TILE_WIDTH, y * Tile.TILE_HEIGHT);
            }
        }

        for (Enemy enemy : enemies) {
            enemy.render(g);
        }
    }

    public boolean isPath(float x, float y) {
        if ((x < 0) || (x >= Tile.TILE_WIDTH * width) || (y < 0) || (y >= Tile.TILE_HEIGHT * height))
            return false;

        Tile tile = TileList.getTile(tiles[(int) x / Tile.TILE_WIDTH][(int) y / Tile.TILE_HEIGHT]);
        return tile.isPath();
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
                } else if (tileId == 8) {
                    endX = x * Tile.TILE_WIDTH;
                    endY = y * Tile.TILE_HEIGHT;
                }
            }
        }
    }
}

package ch.zhaw.psit.towerhopscotch.maps;


import ch.zhaw.psit.towerhopscotch.Game;
import ch.zhaw.psit.towerhopscotch.models.Player;
import ch.zhaw.psit.towerhopscotch.models.entities.enemies.*;
import ch.zhaw.psit.towerhopscotch.models.tiles.Tile;
import ch.zhaw.psit.towerhopscotch.models.tiles.TileList;
import ch.zhaw.psit.towerhopscotch.states.GameState;
import ch.zhaw.psit.towerhopscotch.states.State;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Layer {

    private int startX, startY;
    private int offset;

    private int width, height, order;
    private int[][] tiles;

    private ArrayList<Enemy> enemies = new ArrayList<Enemy>();

    public Layer (int order, int width, int height, String layerContents, int count){
        this.order = order;
        this.width = width;
        this.height = height;
        offset = (order- 1) * width * Tile.TILE_WIDTH + 10 * (order - 1);
        initializeLayer(layerContents);
        generateEnemies(count);
    }


    private void generateEnemies(int count) {
        // Enemies are generated below the screen all with the same x position as the starting tile
        // but varying y positions.
        Random random = new Random();
        for (int i = 1; i <= count; i++) {
            int enemyType = random.nextInt(7 - 1 + 1) + 1;
            int startingHeight = startY + (i * Tile.TILE_HEIGHT) + (2 * (random.nextInt(10 + 10 + 1) - 10));
            switch (enemyType) {
                case 1:
                    enemies.add(new Rat(this, startX, startingHeight));
                    break;
                case 2:
                    enemies.add(new Bat(this, startX, startingHeight));
                    break;
                case 3:
                    enemies.add(new Skeleton(this, startX, startingHeight));
                    break;
                case 4:
                    enemies.add(new Spider(this, startX, startingHeight));
                    break;
                case 5:
                    enemies.add(new Goblin(this, startX, startingHeight));
                    break;
                case 6:
                    enemies.add(new Slime(this, startX, startingHeight));
                    break;
                case 7:
                    enemies.add(new Imp(this, startX, startingHeight));
                    break;
            }
        }
    }

    public void update() {
        Player player = getPlayer();

        // Update enemies
        Iterator<Enemy> iterator = enemies.iterator();
        while (iterator.hasNext()) {
            Enemy enemy = iterator.next();
            enemy.update();

            // Remove enemy if it has reached the players fortress
            if (enemy.reachedDestination()) {
                player.decreaseHealth(enemy.getDamage());
                iterator.remove();
            }
        }
    }

    public void render(Graphics g) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                TileList.getTile(tiles[x][y]).render(g,x * Tile.TILE_WIDTH + offset, y * Tile.TILE_HEIGHT);
            }
        }

        for (Enemy enemy : enemies)
            enemy.render(g);
    }

    public boolean isOnLayer(float x, float y) {
        return !((x < 0) || (x >= Tile.TILE_WIDTH * width + offset) || (y < 0) || (y >= Tile.TILE_HEIGHT * height));
    }

    public boolean isBeneathMap(float x, float y) {
        return (y > Tile.TILE_HEIGHT * height) && (x == startX);
    }

    public boolean isFortress(float x, float y) {
        if (!isOnLayer(x, y))
            return false;

        Tile tile = TileList.getTile(tiles[(int) (x-offset) / Tile.TILE_WIDTH][(int) y / Tile.TILE_HEIGHT]);
        return tile.isFortress();
    }

    public boolean isPath(float x, float y) {
        if (!isOnLayer(x, y))
            return false;

        Tile tile = TileList.getTile(tiles[(int) (x-offset) / Tile.TILE_WIDTH][(int) y / Tile.TILE_HEIGHT]);
        return tile.isPath();
    }

    private void initializeLayer(String layerContents){
        String[] tokens = layerContents.toString().split("\\s+");

        tiles = new int[width][height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int tileId = Integer.parseInt(tokens[(x + y * width)+1]);
                tiles[x][y] = tileId;
                if (tileId == 7) {
                    startX = x * Tile.TILE_WIDTH + offset;
                    startY = y * Tile.TILE_HEIGHT;
                }
            }
        }
    }

    public int getOffset(){
        return offset;
    }

    private Player getPlayer() {
        return ((GameState) State.getState()).getPlayer();
    }
}

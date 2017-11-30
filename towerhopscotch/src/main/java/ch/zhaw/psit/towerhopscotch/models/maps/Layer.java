package ch.zhaw.psit.towerhopscotch.models.maps;


import ch.zhaw.psit.towerhopscotch.models.Player;
import ch.zhaw.psit.towerhopscotch.models.entities.enemies.*;
import ch.zhaw.psit.towerhopscotch.models.enums.LayerType;
import ch.zhaw.psit.towerhopscotch.models.tiles.Tile;
import ch.zhaw.psit.towerhopscotch.models.tiles.TileList;
import ch.zhaw.psit.towerhopscotch.controllers.states.GameState;
import ch.zhaw.psit.towerhopscotch.controllers.states.State;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Layer {
    public final static int LAYER_WIDTH = 448;
    public final static int LAYER_HEIGHT = 640;

    private int startX, startY;
    private int offset;

    private LayerType layerType;
    private int width, height;
    private int[][] tiles;

    public Layer (LayerType layerType, int width, int height, String layerContents, int count){
        this.layerType = layerType;
        this.width = width;
        this.height = height;
        offset = calculateOffset();
        initializeLayer(layerContents);
    }

    public void update() {
    }

    public void render(Graphics g) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                TileList.getTile(tiles[x][y]).render(g,layerType,x * Tile.TILE_WIDTH + offset, y * Tile.TILE_HEIGHT);
            }
        }
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

    public Tile getTile(float x, float y){
        return TileList.getTile(tiles[(int) (x-offset) / Tile.TILE_WIDTH][(int) y / Tile.TILE_HEIGHT]);
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

    public int calculateOffset() {
        int multiplier = 0;
        switch(layerType) {
            case HELL: multiplier = 0; break;
            case EARTH: multiplier = 1; break;
            case HEAVEN: multiplier = 2; break;
        }

        return multiplier * width * Tile.TILE_WIDTH + 10 * multiplier;
    }

    public LayerType getLayerType(){
        return layerType;
    }

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }
}

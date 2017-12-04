package ch.zhaw.psit.towerhopscotch.models.maps;

import ch.zhaw.psit.towerhopscotch.controllers.states.GameState;
import ch.zhaw.psit.towerhopscotch.controllers.states.State;
import ch.zhaw.psit.towerhopscotch.models.entities.enemies.Enemy;
import ch.zhaw.psit.towerhopscotch.models.enums.LayerType;
import ch.zhaw.psit.towerhopscotch.models.tiles.Tile;
import ch.zhaw.psit.towerhopscotch.models.tiles.TileList;
import ch.zhaw.psit.towerhopscotch.models.tower.Tower;
import ch.zhaw.psit.towerhopscotch.models.tower.TowerPosition;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Layer {
    public final static int LAYER_WIDTH = 448;
    public final static int LAYER_HEIGHT = 640;

    private int startX, startY;
    private int offset;

    private LayerType layerType;
    private int width, height;
    private int[][] tiles;
    private List<TowerPosition> towers;
    private List<Enemy> enemies;

    public Layer (LayerType layerType, int width, int height, String layerContents){
        this.layerType = layerType;
        this.width = width;
        this.height = height;
        offset = calculateOffset();
        towers = new ArrayList<>();
        enemies = new ArrayList<>();
        initializeLayer(layerContents);
    }

    public void update(long absNanoTime) {
        for (TowerPosition tower : towers){
            tower.update(absNanoTime, enemies);
        }
        towers.removeIf(tower -> tower.getTower().isRemoved());
    }

    public void render(Graphics g) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                TileList.getTile(tiles[x][y]).render(g,layerType,x * Tile.TILE_WIDTH + offset, y * Tile.TILE_HEIGHT);
            }
        }

        for (Enemy enemy : enemies){
            enemy.render(g);
        }

        for (TowerPosition tower : towers){
            tower.render(g);
        }
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public void setEnemies(List<Enemy> enemies) {
        this.enemies = enemies;
    }

    public void addTower(TowerPosition tower){
        towers.add(tower);
    }

    public void removeTower(Tower tower){
        towers.remove(tower);
        tower.remove();
    }

    public void addEnemy(Enemy enemy){
        enemies.add(enemy);
    }

    public void removeEnemy(Enemy enemy){
        enemies.remove(enemy);
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
        Tile tile = null;
        try {
            tile = TileList.getTile(tiles[(int) (x-offset) / Tile.TILE_WIDTH][(int) y / Tile.TILE_HEIGHT]);
        } catch (IndexOutOfBoundsException e){}
        return tile;
    }

    public ArrayList<Layer> getTeleportableLayers(float topLeftX, float topLeftY, float bottomRightX, float bottomRightY) {
        ArrayList<Layer> teleportableLayers = new ArrayList<Layer>();
        Layer hell = getMap().getHell();
        Layer earth = getMap().getEarth();
        Layer heaven = getMap().getHeaven();

        if(!isPath(topLeftX, topLeftY) || !isPath(bottomRightX, bottomRightY))
            return teleportableLayers;

        switch(layerType) {
            case HELL:
                if(earth.isPath(topLeftX + Layer.LAYER_WIDTH + 10, topLeftY) &&
                        earth.isPath(bottomRightX + Layer.LAYER_WIDTH + 10, bottomRightY)) {
                    teleportableLayers.add(earth);
                }
                if(heaven.isPath(topLeftX + 2*Layer.LAYER_WIDTH + 20, topLeftY) &&
                        heaven.isPath(bottomRightX + 2*Layer.LAYER_WIDTH + 20, bottomRightY)) {
                    teleportableLayers.add(heaven);
                }
                break;
            case EARTH:
                if(hell.isPath(topLeftX - Layer.LAYER_WIDTH - 10, topLeftY) &&
                        hell.isPath(bottomRightX - (Layer.LAYER_WIDTH + 10), bottomRightY)) {
                    teleportableLayers.add(hell);
                }
                if(heaven.isPath(topLeftX + Layer.LAYER_WIDTH + 10, topLeftY) &&
                        heaven.isPath(bottomRightX + Layer.LAYER_WIDTH + 10, bottomRightY)) {
                    teleportableLayers.add(heaven);
                }
                break;
            case HEAVEN:
                if(hell.isPath(topLeftX - (2*Layer.LAYER_WIDTH + 20), topLeftY) &&
                        hell.isPath(bottomRightX - (2*Layer.LAYER_WIDTH + 20), bottomRightY)) {
                    teleportableLayers.add(hell);
                }
                if(earth.isPath(topLeftX - (Layer.LAYER_WIDTH + 10), topLeftY) &&
                        earth.isPath(bottomRightX - (Layer.LAYER_WIDTH + 10), bottomRightY)) {
                    teleportableLayers.add(earth);
                }
                break;
        }

        return teleportableLayers;
    }

    private void initializeLayer(String layerContents){
        String[] tokens = layerContents.split("\\s+");

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
        int multiplier = getLayerLevel();
        return multiplier * width * Tile.TILE_WIDTH + 10 * multiplier;
    }

    public int getLayerLevel(){
        int level = 0;
        switch(layerType) {
            case HELL: level = 0; break;
            case EARTH: level = 1; break;
            case HEAVEN: level = 2; break;
        }
        return level;
    }

    public LayerType getLayerType(){
        return layerType;
    }

    public Tower getTowerAtPosition(Point point){
        TowerPosition towerResult = null;
        for (TowerPosition tower: towers){
            if (tower.getPosition().equals(point)){
                towerResult = tower;
            }
        }
        if (towerResult != null){
            return towerResult.getTower();
        }
            return null;
    }

    private Map getMap() {
        return ((GameState) State.getState()).getMap();
    }

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }
}

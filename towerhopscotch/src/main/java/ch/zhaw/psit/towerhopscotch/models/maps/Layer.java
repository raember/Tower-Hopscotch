package ch.zhaw.psit.towerhopscotch.models.maps;

import ch.zhaw.psit.towerhopscotch.controllers.states.GameState;
import ch.zhaw.psit.towerhopscotch.controllers.states.State;
import ch.zhaw.psit.towerhopscotch.models.entities.enemies.Enemy;
import ch.zhaw.psit.towerhopscotch.models.enums.LayerType;
import ch.zhaw.psit.towerhopscotch.models.maps.heatmap.HeatMap;
import ch.zhaw.psit.towerhopscotch.models.tiles.Tile;
import ch.zhaw.psit.towerhopscotch.models.tiles.TileList;
import ch.zhaw.psit.towerhopscotch.models.tower.Tower;
import ch.zhaw.psit.towerhopscotch.models.tower.TowerPosition;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a layer on the map
 * @author Stefan Bösch, Nicolas Eckhart
 */
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
    private HeatMap heatMap;

    public Layer(LayerType layerType, int width, int height, String layerContents) {
        this.layerType = layerType;
        this.width = width;
        this.height = height;
        offset = calculateOffset();
        towers = new ArrayList<>();
        enemies = new ArrayList<>();
        initializeLayer(layerContents);
        heatMap = new HeatMap(this);
    }

    /**
     * Update the layer with its towers and check if a tower was removed
     * @param absNanoTime absNanoTime
     */
    public void update(long absNanoTime) {
        for (TowerPosition tower : towers) {
            tower.update(absNanoTime, enemies);
        }
        towers.removeIf(tower -> tower.getTower().isRemoved());
    }

    /**
     * Render the tiles, enemies and the towers on this layer
     * @param g Graphics
     */
    public void render(Graphics g) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                TileList.getTile(tiles[x][y]).render(g, layerType, x * Tile.TILE_WIDTH + offset, y * Tile.TILE_HEIGHT);
            }
        }

        // activate for debugging heat map only
        // heatMap.render(g);

        for (Enemy enemy : enemies) {
            enemy.render(g);
        }

        for (TowerPosition tower : towers) {
            tower.render(g);
        }
    }

    /**
     * Get all enemies on this layer
     * @return Enemies on this layer
     */
    public List<Enemy> getEnemies() {
        return enemies;
    }

    public void setEnemies(List<Enemy> enemies) {
        this.enemies = enemies;
    }

    public void addTower(TowerPosition tower) {
        towers.add(tower);
    }

    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
    }

    public void removeEnemy(Enemy enemy) {
        enemies.remove(enemy);
    }

    /**
     * Check if the point is on the layer
     * @param point Point
     * @return True if on Layer
     */
    public boolean isOnLayer(Point point) {
        return getTile(point) != null;
    }

    public boolean isBeneathMap(float x, float y) {
        return isBeneathMap(new Point((int)x,(int)y));
    }

    /**
     * Check if the point is beneath map
     * @param point Point
     * @return True if beneath map
     */
    public boolean isBeneathMap(Point point) {
        return (point.getY() > Tile.TILE_HEIGHT * height) && (point.getX() == startX);
    }

    public boolean isFortress(float x, float y){
        return isFortress(new Point((int)x,(int)y));
    }

    /**
     * Check if the tile at the point is the fortress
     * @param point Point
     * @return True if point is fortress
     */
    public boolean isFortress(Point point) {
        if (!isOnLayer(point))
            return false;

        Tile tile = TileList.getTile(tiles[(int) (point.getX() - offset) / Tile.TILE_WIDTH][(int) point.getY() / Tile.TILE_HEIGHT]);
        return tile.isFortress();
    }

    public boolean isPath(float x, float y){
        return isPath(new Point((int)x,(int)y));
    }

    /**
     * Check if the tile at the point is a path
     * @param point Point
     * @return True if point is a path
     */
    public boolean isPath(Point point) {
        if (!isOnLayer(point))
            return false;

        Tile tile = TileList.getTile(tiles[(int) (point.getX() - offset) / Tile.TILE_WIDTH][(int) point.getY() / Tile.TILE_HEIGHT]);
        return tile.isPath();
    }

    /**
     * Get the tile at the point
     * @param point Point
     * @return Tile at point
     */
    public Tile getTile(Point point) {
        Tile tile = null;
        try {
            tile = TileList.getTile(tiles[(int) (point.getX() - offset) / Tile.TILE_WIDTH][(int) point.getY() / Tile.TILE_HEIGHT]);
        } catch (IndexOutOfBoundsException e) {

        }
        return tile;
    }

    public ArrayList<Layer> getTeleportableLayers(float xTop, float yTop, float xBottom, float yBottom) {
        return getTeleportableLayers(new Point((int)xTop,(int)yTop), new Point((int)xBottom,(int)yBottom));
    }

    /**
     * Check to which layers an enemy can teleport
     * @param top top point
     * @param bottom bottom point
     * @return teleportable layers
     */
    public ArrayList<Layer> getTeleportableLayers(Point top, Point bottom) {
        ArrayList<Layer> teleportableLayers = new ArrayList<>();
        Layer hell = getMap().getHell();
        Layer earth = getMap().getEarth();
        Layer heaven = getMap().getHeaven();

        if (!isPath(top) || !isPath(bottom))
            return teleportableLayers;

        switch (layerType) {
            case HELL:
                if (earth.isPath(new Point(((int) top.getX()) + Layer.LAYER_WIDTH + 10, ((int) top.getY()))) &&
                        earth.isPath(new Point(((int) bottom.getX()) + Layer.LAYER_WIDTH + 10, ((int) bottom.getY())))) {
                    teleportableLayers.add(earth);
                }
                if (heaven.isPath(new Point(((int) top.getX()) + 2 * Layer.LAYER_WIDTH + 20, ((int) top.getY()))) &&
                        heaven.isPath(new Point(((int) bottom.getX()) + 2 * Layer.LAYER_WIDTH + 20, ((int) bottom.getY())))) {
                    teleportableLayers.add(heaven);
                }
                break;
            case EARTH:
                if (hell.isPath(new Point(((int) top.getX()) - Layer.LAYER_WIDTH - 10, ((int) top.getY()))) &&
                        hell.isPath(new Point(((int) bottom.getX()) - (Layer.LAYER_WIDTH + 10), ((int) bottom.getY())))) {
                    teleportableLayers.add(hell);
                }
                if (heaven.isPath(new Point(((int) top.getX()) + Layer.LAYER_WIDTH + 10, ((int) top.getY()))) &&
                        heaven.isPath(new Point(((int) bottom.getX()) + Layer.LAYER_WIDTH + 10, ((int) bottom.getY())))) {
                    teleportableLayers.add(heaven);
                }
                break;
            case HEAVEN:
                if (hell.isPath(new Point(((int) top.getX()) - (2 * Layer.LAYER_WIDTH + 20), ((int) top.getY()))) &&
                        hell.isPath(new Point(((int) bottom.getX()) - (2 * Layer.LAYER_WIDTH + 20), ((int) bottom.getY())))) {
                    teleportableLayers.add(hell);
                }
                if (earth.isPath(new Point(((int) top.getX()) - (Layer.LAYER_WIDTH + 10), ((int) top.getY()))) &&
                        earth.isPath(new Point(((int) bottom.getX()) - (Layer.LAYER_WIDTH + 10), ((int) bottom.getY())))) {
                    teleportableLayers.add(earth);
                }
                break;
        }

        return teleportableLayers;
    }

    /**
     * Initialize the layer
     * @param layerContents the map file content for the layer
     */
    private void initializeLayer(String layerContents) {
        String[] tokens = layerContents.split("\\s+");

        tiles = new int[width][height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int tileId = Integer.parseInt(tokens[(x + y * width) + 1]);
                tiles[x][y] = tileId;
                if (tileId == 7) {
                    startX = x * Tile.TILE_WIDTH + offset;
                    startY = y * Tile.TILE_HEIGHT;
                }
            }
        }
    }

    /**
     * Calculate the offset from the spacing
     * @return Offset
     */
    private int calculateOffset() {
        int multiplier = getLayerLevel();
        return multiplier * width * Tile.TILE_WIDTH + 10 * multiplier;
    }

    /**
     * Get the tower at the specified position
     * @param point Point
     * @return Tower at point
     */
    public Tower getTowerAtPosition(Point point) {
        TowerPosition towerResult = null;
        for (TowerPosition tower : towers) {
            if (tower.getPosition().equals(point)) {
                towerResult = tower;
            }
        }
        if (towerResult != null) {
            return towerResult.getTower();
        }
        return null;
    }

    private Map getMap() {
        return ((GameState) State.getState()).getMap();
    }

    public LayerType getLayerType() {
        return layerType;
    }

    public int getHeatValue(float x, float y) {
        return heatMap.getHeatValue((int) (x - offset) / Tile.TILE_WIDTH, (int) y / Tile.TILE_HEIGHT);
    }

    public int getLayerLevel() {
        int level = 0;
        switch (layerType) {
            case HELL:
                level = 0;
                break;
            case EARTH:
                level = 1;
                break;
            case HEAVEN:
                level = 2;
                break;
        }
        return level;
    }

    public int getStartX() {
        return startX;
    }

    public Point getStartPointTile() {
        return new Point((startX - offset) / Tile.TILE_WIDTH, startY / Tile.TILE_HEIGHT);
    }

    public int getStartY() {
        return startY;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getOffset() {
        return offset;
    }

    public int[][] getTiles() {
        return tiles;
    }
}

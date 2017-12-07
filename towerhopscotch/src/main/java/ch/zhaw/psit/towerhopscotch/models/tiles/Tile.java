package ch.zhaw.psit.towerhopscotch.models.tiles;

import ch.zhaw.psit.towerhopscotch.GUI.Assets;
import ch.zhaw.psit.towerhopscotch.models.enums.LayerType;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Represents a field ont the map
 * @author Nicolas Eckhart, Stefan Bösch
 */
public class Tile {
    public static final int TILE_WIDTH = 32, TILE_HEIGHT = 32;

    private BufferedImage hellTexture;
    private BufferedImage earthTexture;
    private BufferedImage heavenTexture;
    private final int id;

    public Tile(String title, int id) {
        hellTexture = Assets.hellTiles.get(title);
        earthTexture = Assets.earthTiles.get(title);
        heavenTexture = Assets.heavenTiles.get(title);
        this.id = id;

        TileList.instanceList[id] = this;
    }

    /**
     * Render the Tile with the correct layer theme at the specified position
     * @param g Graphics
     * @param layerType The layer on which the tile is located
     * @param x Coordinate X
     * @param y Coordinate Y
     */
    public void render(Graphics g, LayerType layerType, int x, int y) {
        BufferedImage texture = null;
        if (layerType != null) {
            switch (layerType) {
                case HELL:
                    texture = hellTexture;
                    break;
                case EARTH:
                    texture = earthTexture;
                    break;
                case HEAVEN:
                    texture = heavenTexture;
                    break;
            }
        } else {
            texture = earthTexture;
        }

        g.drawImage(texture, x, y, TILE_WIDTH, TILE_HEIGHT, null);
    }

    public boolean isPath() {
        return false;
    }

    public boolean isFortress() {
        return false;
    }

    /**
     * Is the player allowed to place a tower on this type of tile
     * @return Tower is placeable on this Tile
     */
    public boolean isTowerPlaceable() {
        return false;
    }

    public int getId() {
        return id;
    }
}

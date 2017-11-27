package ch.zhaw.psit.towerhopscotch.models.tiles;

import ch.zhaw.psit.towerhopscotch.GUI.Assets;
import ch.zhaw.psit.towerhopscotch.maps.Layer;

import java.awt.*;
import java.awt.image.BufferedImage;

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

    public void render(Graphics g, Layer layer, int x, int y) {
        BufferedImage texture;
        if (layer != null){
            switch (layer.getOrder()){
                case 1: texture = hellTexture; break;
                case 2: texture = earthTexture; break;
                case 3: texture = heavenTexture; break;
                default: texture = earthTexture; break;
            }
        } else {
            texture = earthTexture;
        }

        g.drawImage(texture, x, y, TILE_WIDTH, TILE_HEIGHT, null);
    }

    public boolean isPath() {
        return false;
    }

    public boolean isFortress() { return false; }

    public int getId() {
        return id;
    }
}

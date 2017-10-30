package ch.zhaw.psit.towerhopscotch.models.tiles;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {
    public static final int TILE_WIDTH = 32, TILE_HEIGHT = 32;

    private BufferedImage texture;
    private final int id;

    public Tile(BufferedImage texture, int id) {
        this.texture = texture;
        this.id = id;

        TileList.instanceList[id] = this;
    }

    public void render(Graphics g, int x, int y) {
        g.drawImage(texture, x, y, TILE_WIDTH, TILE_HEIGHT, null);
    }

    public boolean isSolid() {
        return false;
    }

    public boolean isPath() {
        return false;
    }

    public int getId() {
        return id;
    }
}

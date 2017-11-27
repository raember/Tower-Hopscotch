package ch.zhaw.psit.towerhopscotch.GUI;

import ch.zhaw.psit.towerhopscotch.GUI.Assets;
import ch.zhaw.psit.towerhopscotch.models.tiles.Tile;
import ch.zhaw.psit.towerhopscotch.models.tiles.TileList;

import java.awt.*;

public class BuildMenu {
    public final static int top = 640;
    public final static int width = 3*448;
    public final static int height = 128;

    public void update() {

    }

    public void render(Graphics g) {
        drawMenuBackground(g);
    }

    private void drawMenuBackground(Graphics g) {
        for(int y = 0; y < height; y += Tile.TILE_HEIGHT) {
            for(int x = 0; x < width; x += Tile.TILE_WIDTH) {
                TileList.getTile(6).render(g, x, top + y);
            }
        }
    }
}

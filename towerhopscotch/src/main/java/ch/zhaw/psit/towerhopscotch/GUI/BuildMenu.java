package ch.zhaw.psit.towerhopscotch.GUI;

import ch.zhaw.psit.towerhopscotch.models.tiles.Tile;
import ch.zhaw.psit.towerhopscotch.models.tiles.TileList;

import java.awt.*;

public class BuildMenu {
    public final static int TOP = 640;
    public final static int WIDTH = 3*448 + 20;
    public final static int HEIGHT = 96;

    public void update() {

    }

    public void render(Graphics g) {
        drawMenuBackground(g);
        drawTowerButtons(g);
        drawPlayerHealth(g);
        drawPlayerGold(g);
        drawUpgradeButton(g);
        drawNextWaveButton(g);
    }

    private void drawMenuBackground(Graphics g) {
        for(int y = 0; y < HEIGHT; y += Tile.TILE_HEIGHT) {
            for(int x = 0; x < WIDTH; x += Tile.TILE_WIDTH) {
                TileList.getTile(6).render(g, x, TOP + y);
            }
        }
    }

    private void drawTowerButtons(Graphics g) {
        TileList.getTile(255).render(g, xOffset(1), yOffset(1));
        TileList.getTile(255).render(g, xOffset(3), yOffset(1));
        TileList.getTile(255).render(g, xOffset(5), yOffset(1));
    }

    private void drawPlayerHealth(Graphics g) {
        g.drawImage(Assets.heart, xOffset(9), yOffset(1), Tile.TILE_WIDTH, Tile.TILE_HEIGHT, null);
        Text.drawString(g, "20 / 20", xOffset(11), yOffset(1), Color.BLACK, Assets.font32);
    }

    private void drawPlayerGold(Graphics g) {
        g.drawImage(Assets.treasure, xOffset(17), yOffset(1), Tile.TILE_WIDTH, Tile.TILE_HEIGHT, null);
        Text.drawString(g, "200", xOffset(19), yOffset(1), Color.BLACK, Assets.font32);
    }

    private void drawUpgradeButton(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRoundRect(xOffset(25) - 10, yOffset(1), 180, 32, 5, 5);
        Text.drawString(g, "Upgrade", xOffset(25), yOffset(1), Color.WHITE, Assets.font32);
    }

    private void drawNextWaveButton(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRoundRect(xOffset(33) - 10, yOffset(1), 220, 32, 5, 5);
        Text.drawString(g, "Next Wave", xOffset(33), yOffset(1), Color.WHITE, Assets.font32);
    }

    private int yOffset(int offset) {
        return TOP + (Tile.TILE_HEIGHT * offset);
    }

    private int xOffset(int offset) {
        return Tile.TILE_WIDTH * offset;
    }
}

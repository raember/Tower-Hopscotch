package ch.zhaw.psit.towerhopscotch.GUI.menus;

import ch.zhaw.psit.towerhopscotch.GUI.Assets;
import ch.zhaw.psit.towerhopscotch.GUI.Text;
import ch.zhaw.psit.towerhopscotch.models.Player;
import ch.zhaw.psit.towerhopscotch.models.tiles.Tile;
import ch.zhaw.psit.towerhopscotch.models.tiles.TileList;
import ch.zhaw.psit.towerhopscotch.controllers.states.GameState;
import ch.zhaw.psit.towerhopscotch.controllers.states.State;

import java.awt.*;

public class BuildMenu {
    public final static int TOP = 640;
    public final static int WIDTH = 3 * 448 + 20;
    public final static int HEIGHT = 96;

    private Button towerUpgradeButton;
    private Button callNextWaveButton;

    public BuildMenu() {
        towerUpgradeButton = new Button("Upgrade", xOffset(25), yOffset(1));
        callNextWaveButton = new Button("Next Wave", xOffset(33), yOffset(1));
    }

    public void update() {
        towerUpgradeButton.update();
        callNextWaveButton.update();
    }

    public void render(Graphics g) {
        drawMenuBackground(g);
        drawTowerButtons(g);
        drawPlayerHealth(g);
        drawPlayerGold(g);

        towerUpgradeButton.render(g);
        callNextWaveButton.render(g);
    }

    public boolean callNextWaveClicked() {
        return callNextWaveButton.isClicked();
    }

    private void drawMenuBackground(Graphics g) {
        for(int y = 0; y < HEIGHT; y += Tile.TILE_HEIGHT) {
            for(int x = 0; x < WIDTH; x += Tile.TILE_WIDTH) {
                TileList.getTile(6).render(g,null, x, TOP + y);
            }
        }
    }

    private void drawTowerButtons(Graphics g) {
        TileList.getTile(255).render(g,null, xOffset(1), yOffset(1));
        TileList.getTile(255).render(g,null, xOffset(3), yOffset(1));
        TileList.getTile(255).render(g,null, xOffset(5), yOffset(1));
    }

    private void drawPlayerHealth(Graphics g) {
        g.drawImage(Assets.heart, xOffset(9), yOffset(1), Tile.TILE_WIDTH, Tile.TILE_HEIGHT, null);
        Text.drawString(g, getPlayer().getHealth() + " / " + Player.MAX_HEALTH , xOffset(11), yOffset(1), false, Color.BLACK, Assets.font32);
    }

    private void drawPlayerGold(Graphics g) {
        g.drawImage(Assets.treasure, xOffset(17), yOffset(1), Tile.TILE_WIDTH, Tile.TILE_HEIGHT, null);
        Text.drawString(g, getPlayer().getGold() + " Gold", xOffset(19), yOffset(1), false, Color.BLACK, Assets.font32);
    }

    private int yOffset(int offset) {
        return TOP + (Tile.TILE_HEIGHT * offset);
    }

    private int xOffset(int offset) {
        return Tile.TILE_WIDTH * offset;
    }

    private Player getPlayer() {
        return ((GameState) State.getState()).getPlayer();
    }
}

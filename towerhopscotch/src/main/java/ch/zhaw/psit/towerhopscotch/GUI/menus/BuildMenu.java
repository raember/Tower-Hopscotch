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
    private Button towerDestroyButton;
    private Button callNextWaveButton;
    private Button placeSimpleTower;
    private Button placeDoubleTower;
    private Button placeTripleTower;

    public BuildMenu() {
        towerUpgradeButton = new Button("Upgrade", xOffset(23), yOffset(1));
        towerDestroyButton = new Button("Destroy", xOffset(29), yOffset(1));
        callNextWaveButton = new Button("Next Wave", xOffset(35), yOffset(1));

        placeSimpleTower = new Button("1", xOffset(1), yOffset(1));
        placeDoubleTower = new Button("2", xOffset(3), yOffset(1));
        placeTripleTower = new Button("3", xOffset(5), yOffset(1));
    }

    public void update() {
        towerUpgradeButton.update();
        towerDestroyButton.update();
        callNextWaveButton.update();
        placeSimpleTower.update();
        placeDoubleTower.update();
        placeTripleTower.update();
    }

    public void render(Graphics g) {
        drawMenuBackground(g);
        drawPlayerHealth(g);
        drawPlayerGold(g);

        towerUpgradeButton.render(g);
        towerDestroyButton.render(g);
        callNextWaveButton.render(g);
        placeSimpleTower.render(g);
        placeDoubleTower.render(g);
        placeTripleTower.render(g);
    }

    public boolean callNextWaveClicked() {
        return callNextWaveButton.isClicked();
    }

    public boolean towerUpgradeButtonClicked(){
        return towerUpgradeButton.isClicked();
    }

    public boolean towerDestroyButtonClicked(){
        return towerDestroyButton.isClicked();
    }

    public boolean placeSimpleTowerClicked(){
        return placeSimpleTower.isClicked();
    }

    public boolean placeDoubleTowerClicked(){
        return placeDoubleTower.isClicked();
    }

    public boolean placeTripleTowerClicked(){
        return placeTripleTower.isClicked();
    }

    private void drawMenuBackground(Graphics g) {
        for(int y = 0; y < HEIGHT; y += Tile.TILE_HEIGHT) {
            for(int x = 0; x < WIDTH; x += Tile.TILE_WIDTH) {
                TileList.getTile(6).render(g,null, x, TOP + y);
            }
        }
    }

    private void drawPlayerHealth(Graphics g) {
        g.drawImage(Assets.heart, xOffset(7), yOffset(1), Tile.TILE_WIDTH, Tile.TILE_HEIGHT, null);
        Text.drawString(g, getPlayer().getHealth() + " / " + Player.MAX_HEALTH , xOffset(9), yOffset(1), false, Color.BLACK, Assets.font32);
    }

    private void drawPlayerGold(Graphics g) {
        g.drawImage(Assets.treasure, xOffset(15), yOffset(1), Tile.TILE_WIDTH, Tile.TILE_HEIGHT, null);
        Text.drawString(g, getPlayer().getGold() + " G", xOffset(17), yOffset(1), false, Color.BLACK, Assets.font32);
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

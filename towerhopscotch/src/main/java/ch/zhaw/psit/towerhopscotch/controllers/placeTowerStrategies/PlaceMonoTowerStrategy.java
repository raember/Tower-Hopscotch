package ch.zhaw.psit.towerhopscotch.controllers.placeTowerStrategies;

import ch.zhaw.psit.towerhopscotch.GUI.input.MouseManager;
import ch.zhaw.psit.towerhopscotch.controllers.states.GameState;
import ch.zhaw.psit.towerhopscotch.models.Player;
import ch.zhaw.psit.towerhopscotch.models.maps.Layer;
import ch.zhaw.psit.towerhopscotch.models.tiles.Tile;
import ch.zhaw.psit.towerhopscotch.models.tower.MonoTower;
import ch.zhaw.psit.towerhopscotch.models.tower.Tower;
import ch.zhaw.psit.towerhopscotch.models.tower.TowerPosition;

import java.awt.*;

public class PlaceMonoTowerStrategy implements TowerStrategy {

    @Override
    public void activeAction(GameState gameState, Graphics g) {

        int mouseX = gameState.getMouseManager().getMouseX();
        int mouseY = gameState.getMouseManager().getMouseY();

        Layer layer = gameState.getMap().getLayer(mouseX, mouseY);

        if (layer != null){

            int offset = layer.getLayerLevel() * 10;
            mouseX -= ((mouseX-offset)%32);
            mouseY = mouseY - (mouseY%32);

            Tile tile = layer.getTile(mouseX,mouseY);
            if (tile != null){
                if (tile.isTowerPlaceable() && layer.getTowerAtPosition(new Point(mouseX,mouseY)) == null){
                    g.setColor(placeable);
                } else {
                    g.setColor(notPlaceable);
                }
                g.fillRect(mouseX, mouseY, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);
            }
        }
    }

    @Override
    public boolean doTowerOperation(GameState gameState, Point point) {
        Layer layer = gameState.getMap().getLayer((float) point.getX(),(float) point.getY());
        if (layer.getTowerAtPosition(point) != null) return false;

        Player player = gameState.getPlayer();
        Tower tower = new MonoTower();
        int price = tower.getPrice();

        if (player.getGold().getAmount() >= price) {
            player.addGold(-price);
            TowerPosition towerPosition = new TowerPosition(point,tower);
            layer.addTower(towerPosition);
            return true;
        }
        return false;
    }
}

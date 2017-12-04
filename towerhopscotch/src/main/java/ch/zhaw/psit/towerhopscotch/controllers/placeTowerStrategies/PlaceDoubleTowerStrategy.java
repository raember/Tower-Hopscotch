package ch.zhaw.psit.towerhopscotch.controllers.placeTowerStrategies;

import ch.zhaw.psit.towerhopscotch.controllers.states.GameState;
import ch.zhaw.psit.towerhopscotch.models.Player;
import ch.zhaw.psit.towerhopscotch.models.maps.Layer;
import ch.zhaw.psit.towerhopscotch.models.maps.Map;
import ch.zhaw.psit.towerhopscotch.models.tiles.Tile;
import ch.zhaw.psit.towerhopscotch.models.tower.DoubleTower;
import ch.zhaw.psit.towerhopscotch.models.tower.TowerPosition;

import java.awt.*;

public class PlaceDoubleTowerStrategy implements TowerStrategy {

    @Override
    public void activeAction(GameState gameState, Graphics g) {

        int mouseX = gameState.getMouseManager().getMouseX();
        int mouseY = gameState.getMouseManager().getMouseY();

        Layer layer1 = gameState.getMap().getLayer(mouseX, mouseY);
        int mouseX2 = mouseX + 14 * 32 +10;
        Layer layer2 = gameState.getMap().getLayer(mouseX2, mouseY);

        if (layer1 != null && layer2 != null){

            int offset1 = layer1.getLayerLevel() * 10;
            mouseX -= ((mouseX-offset1)%32);
            mouseY -= (mouseY%32);

            int offset2 = layer2.getLayerLevel() * 10;
            mouseX2 -= ((mouseX2-offset2)%32);

            Tile tile1 = layer1.getTile(mouseX,mouseY);
            Tile tile2 = layer2.getTile(mouseX2,mouseY);
            if (tile1 != null && tile2 != null){
                if (tile1.isTowerPlaceable() && layer1.getTowerAtPosition(new Point(mouseX,mouseY)) == null && tile2.isTowerPlaceable() && layer2.getTowerAtPosition(new Point(mouseX2,mouseY)) == null){
                    g.setColor(placeable);
                } else {
                    g.setColor(notPlaceable);
                }
                g.fillRect(mouseX, mouseY, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);
                g.fillRect(mouseX2, mouseY, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);
            }
        }
    }



    private boolean checkIfPlaceableAtPosition(GameState gameState,Point point){
        int x = (int)point.getX();
        int y = (int)point.getY();
        Layer layer = gameState.getMap().getLayer((float)point.getX(),(float) point.getY());
        if (layer != null){
            int offset1 = layer.getLayerLevel() * 10;
            x -= ((x-offset1)%32);
            y -= (y%32);
            Tile tile = layer.getTile(x,y);
            if (tile.isTowerPlaceable() && layer.getTowerAtPosition(new Point(x,y)) == null){
                return true;
            }
        }
        return false;
    }


    @Override
    public boolean doTowerOperation(GameState gameState, Point point) {

        Player player = gameState.getPlayer();
        DoubleTower tower = new DoubleTower();

        int price = tower.getPrice();
        if (player.getGold().getAmount() >= price) {


            int mouseX = gameState.getMouseManager().getMouseX();
            int mouseY = gameState.getMouseManager().getMouseY();
            int mouseX2 = mouseX + 14 * 32 +10;


            if (checkIfPlaceableAtPosition(gameState, new Point(mouseX,mouseY)) && checkIfPlaceableAtPosition(gameState, new Point(mouseX2,mouseY))){

                player.addGold(-price);

                TowerPosition towerPosition1 = new TowerPosition(point, tower);
                TowerPosition towerPosition2 = new TowerPosition(new Point((int)point.getX() + 14 * 32 +10, (int)point.getY()),tower);

                Layer layer1 = gameState.getMap().getLayer(mouseX, mouseY);
                Layer layer2 = gameState.getMap().getLayer(mouseX2, mouseY);

                layer1.addTower(towerPosition1);
                layer2.addTower(towerPosition2);
                return true;
            }
        }
        return false;
    }
}
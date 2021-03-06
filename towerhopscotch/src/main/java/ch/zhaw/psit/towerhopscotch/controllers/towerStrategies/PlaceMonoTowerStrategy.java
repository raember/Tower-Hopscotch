package ch.zhaw.psit.towerhopscotch.controllers.towerStrategies;

import ch.zhaw.psit.towerhopscotch.controllers.states.GameState;
import ch.zhaw.psit.towerhopscotch.models.Player;
import ch.zhaw.psit.towerhopscotch.models.maps.Layer;
import ch.zhaw.psit.towerhopscotch.models.tiles.Tile;
import ch.zhaw.psit.towerhopscotch.models.tower.MonoTower;
import ch.zhaw.psit.towerhopscotch.models.tower.Tower;
import ch.zhaw.psit.towerhopscotch.models.tower.TowerPosition;

import java.awt.*;

/**
 * Strategy for placing MonoTower
 * @author Stefan Bösch
 */
public class PlaceMonoTowerStrategy extends PlaceTowerStrategy {

    /**
     * Draw placeable Gizmos for MonoTower placement
     * @param gameState Gamestate
     * @param g Graphics
     */
    @Override
    public void activeAction(GameState gameState, Graphics g) {

        gameState.drawText(g,"SELECT POITION FOR MONO TOWER");

        Point point1 = gameState.getMouseManager().getPosition();

        Layer layer1 = getLayer(gameState, point1);

        if (layer1 != null) {

            point1 = calculateCorrectCoordinates(layer1, point1);
            Point[] points = {point1};

            Tile tile1 = layer1.getTile(point1);

            Color colorTile;
            Color colorRange;

            if (tile1 != null) {
                if (checkIfPlaceable(point1, layer1, tile1)) {
                    colorTile = placeable;
                    colorRange = Color.GREEN;
                } else {
                    colorTile = notPlaceable;
                    colorRange = Color.RED;
                }
                drawSquares(g, points, colorTile);
                drawRange(g, points, colorRange, new MonoTower().getFireRange());
                drawText(g,point1,colorRange,new MonoTower());
            }
        }
    }

    /**
     * Place MonoTower at BasePosition
     * @param gameState Gamestate
     * @param point BasePosition
     * @return sucessfullyCreated
     */
    @Override
    public boolean doTowerOperation(GameState gameState, Point point) {

        Player player = gameState.getPlayer();
        MonoTower tower = new MonoTower();

        int price = tower.getPrice();
        if (player.getGold().getAmount() >= price) {

            Point point1 = point;
            Point[] points = {point1};

            if (checkIfPlaceableAtPositionForPoints(gameState, points)) {

                player.addGold(-price);

                TowerPosition towerPosition1 = new TowerPosition(point, tower);

                Layer layer1 = getLayer(gameState, point1);

                layer1.addTower(towerPosition1);
                return true;
            }
        }
        return false;
    }
}

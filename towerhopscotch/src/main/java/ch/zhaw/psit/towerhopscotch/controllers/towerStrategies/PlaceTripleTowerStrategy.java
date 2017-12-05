package ch.zhaw.psit.towerhopscotch.controllers.towerStrategies;

import ch.zhaw.psit.towerhopscotch.controllers.states.GameState;
import ch.zhaw.psit.towerhopscotch.models.Player;
import ch.zhaw.psit.towerhopscotch.models.maps.Layer;
import ch.zhaw.psit.towerhopscotch.models.tiles.Tile;
import ch.zhaw.psit.towerhopscotch.models.tower.TowerPosition;
import ch.zhaw.psit.towerhopscotch.models.tower.TripleTower;

import java.awt.*;

/**
 * Strategy for placing TripleTower
 * @author Stefan Bösch
 */
public class PlaceTripleTowerStrategy extends PlaceTowerStrategy {

    /**
     * Draw placeable Gizmos for TripleTower placement
     * @param gameState Gamestate
     * @param g Graphics
     */
    @Override
    public void activeAction(GameState gameState, Graphics g) {

        Point point1 = gameState.getMouseManager().getPosition();
        Point point2 = new Point(((int) point1.getX()) + 14 * 32 + 10, ((int) point1.getY()));
        Point point3 = new Point(((int) point1.getX()) + 2 * (14 * 32 + 10), ((int) point1.getY()));

        Layer layer1 = getLayer(gameState, point1);
        Layer layer2 = getLayer(gameState, point2);
        Layer layer3 = getLayer(gameState, point3);

        if (layer1 != null && layer2 != null && layer3 != null) {

            point1 = calculateCorrectCoordinates(layer1,point1);
            point2 = calculateCorrectCoordinates(layer2,point2);
            point3 = calculateCorrectCoordinates(layer3,point3);
            Point[] points = {point1,point2,point3};

            Tile tile1 = layer1.getTile(point1);
            Tile tile2 = layer2.getTile(point2);
            Tile tile3 = layer3.getTile(point3);

            Color color;

            if (tile1 != null && tile2 != null && tile3 != null) {
                if (checkIfPlaceable(point1, layer1, tile1)
                        && checkIfPlaceable(point2, layer2, tile2)
                        && checkIfPlaceable(point3, layer3, tile3)) {
                    color = placeable;
                } else {
                    color = notPlaceable;
                }
                drawSquares(g,points,color);
            }
        }
    }

    /**
     * Place TripleTower at BasePosition
     * @param gameState Gamestate
     * @param point BasePosition
     * @return sucessfullyCreated
     */
    @Override
    public boolean doTowerOperation(GameState gameState, Point point) {

        Player player = gameState.getPlayer();
        TripleTower tower = new TripleTower();

        int price = tower.getPrice();
        if (player.getGold().getAmount() >= price) {

            Point point1 = point;
            Point point2 = new Point(((int) point1.getX()) + 14 * 32 + 10, ((int) point1.getY()));
            Point point3 = new Point(((int) point1.getX()) + 2 * (14 * 32 + 10), ((int) point1.getY()));
            Point[] points = {point1,point2,point3};

            if (checkIfPlaceableAtPositionForPoints(gameState, points)) {

                player.addGold(-price);

                TowerPosition towerPosition1 = new TowerPosition(point, tower);
                TowerPosition towerPosition2 = new TowerPosition(point2, tower);
                TowerPosition towerPosition3 = new TowerPosition(point3, tower);

                Layer layer1 = getLayer(gameState, point1);
                Layer layer2 = getLayer(gameState, point2);
                Layer layer3 = getLayer(gameState, point3);

                layer1.addTower(towerPosition1);
                layer2.addTower(towerPosition2);
                layer3.addTower(towerPosition3);
                return true;
            }
        }
        return false;
    }
}
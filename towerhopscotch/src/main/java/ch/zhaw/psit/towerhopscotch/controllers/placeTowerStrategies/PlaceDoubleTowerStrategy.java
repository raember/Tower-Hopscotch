package ch.zhaw.psit.towerhopscotch.controllers.placeTowerStrategies;

import ch.zhaw.psit.towerhopscotch.controllers.states.GameState;
import ch.zhaw.psit.towerhopscotch.models.Player;
import ch.zhaw.psit.towerhopscotch.models.maps.Layer;
import ch.zhaw.psit.towerhopscotch.models.tiles.Tile;
import ch.zhaw.psit.towerhopscotch.models.tower.DoubleTower;
import ch.zhaw.psit.towerhopscotch.models.tower.TowerPosition;

import java.awt.*;

public class PlaceDoubleTowerStrategy extends PlaceTowerStrategy {

    @Override
    public void activeAction(GameState gameState, Graphics g) {

        Point point1 = gameState.getMouseManager().getPosition();
        Point point2 = new Point(((int) point1.getX()) + 14 * 32 + 10, ((int) point1.getY()));

        Layer layer1 = getLayer(gameState, point1);
        Layer layer2 = getLayer(gameState, point2);

        if (layer1 != null && layer2 != null) {

            point1 = calculateCorrectCoordinates(layer1,point1);
            point2 = calculateCorrectCoordinates(layer2,point2);
            Point[] points = {point1,point2};

            Tile tile1 = layer1.getTile(point1);
            Tile tile2 = layer2.getTile(point2);

            Color color;

            if (tile1 != null && tile2 != null) {
                if (checkIfPlaceable(point1, layer1, tile1)
                        && checkIfPlaceable(point2, layer2, tile2)) {
                    color = placeable;
                } else {
                    color = notPlaceable;
                }
                drawSquares(g,points,color);
                drawRange(g, points, color, new DoubleTower().getFireRange());
            }
        }
    }

    @Override
    public boolean doTowerOperation(GameState gameState, Point point) {

        Player player = gameState.getPlayer();
        DoubleTower tower = new DoubleTower();

        int price = tower.getPrice();
        if (player.getGold().getAmount() >= price) {

            Point point1 = point;
            Point point2 = new Point(((int) point1.getX()) + 14 * 32 + 10, ((int) point1.getY()));
            Point[] points = {point1,point2};

            if (checkIfPlaceableAtPositionForPoints(gameState, points)) {

                player.addGold(-price);

                TowerPosition towerPosition1 = new TowerPosition(point, tower);
                TowerPosition towerPosition2 = new TowerPosition(point2, tower);

                Layer layer1 = getLayer(gameState, point1);
                Layer layer2 = getLayer(gameState, point2);

                layer1.addTower(towerPosition1);
                layer2.addTower(towerPosition2);
                return true;
            }
        }
        return false;
    }
}
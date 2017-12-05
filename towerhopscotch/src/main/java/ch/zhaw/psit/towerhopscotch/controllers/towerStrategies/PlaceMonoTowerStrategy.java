package ch.zhaw.psit.towerhopscotch.controllers.towerStrategies;

import ch.zhaw.psit.towerhopscotch.controllers.states.GameState;
import ch.zhaw.psit.towerhopscotch.models.Player;
import ch.zhaw.psit.towerhopscotch.models.maps.Layer;
import ch.zhaw.psit.towerhopscotch.models.tiles.Tile;
import ch.zhaw.psit.towerhopscotch.models.tower.MonoTower;
import ch.zhaw.psit.towerhopscotch.models.tower.TowerPosition;

import java.awt.*;

public class PlaceMonoTowerStrategy extends PlaceTowerStrategy {

    /**
     * Draw placeable Gizmos for MonoTower placement
     * @param gameState Gamestate
     * @param g Graphics
     */
    @Override
    public void activeAction(GameState gameState, Graphics g) {

        Point point1 = gameState.getMouseManager().getPosition();

        Layer layer1 = getLayer(gameState, point1);

        if (layer1 != null) {

            point1 = calculateCorrectCoordinates(layer1,point1);
            Point[] points = {point1};

            Tile tile1 = layer1.getTile(point1);

            Color color;

            if (tile1 != null) {
                if (checkIfPlaceable(point1, layer1, tile1)) {
                    color = placeable;
                } else {
                    color = notPlaceable;
                }
                drawSquares(g,points,color);
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

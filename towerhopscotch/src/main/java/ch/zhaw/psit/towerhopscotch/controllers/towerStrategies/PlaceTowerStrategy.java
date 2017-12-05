package ch.zhaw.psit.towerhopscotch.controllers.towerStrategies;

import ch.zhaw.psit.towerhopscotch.controllers.states.GameState;
import ch.zhaw.psit.towerhopscotch.models.maps.Layer;
import ch.zhaw.psit.towerhopscotch.models.tiles.Tile;

import java.awt.*;

/**
 * Base class for the PlaceTower strategies
 * @author Stefan Bösch
 */
public abstract class PlaceTowerStrategy implements TowerStrategy {

    void drawSquares(Graphics g, Point[] points, Color color){
        g.setColor(color);
        for (Point point : points){
            g.fillRect(((int) point.getX()), ((int) point.getY()), Tile.TILE_WIDTH, Tile.TILE_HEIGHT);
        }
    }

    boolean checkIfPlaceable(Point point, Layer layer, Tile tile1) {
        return tile1.isTowerPlaceable() && layer.getTowerAtPosition(point) == null;
    }

    Layer getLayer(GameState gameState, Point point) {
        return gameState.getMap().getLayer(point);
    }

    Point calculateCorrectCoordinates(Layer layer, Point point){
        int offset = layer.getLayerLevel() * 10;
        return new Point(((int) point.getX()) - ((((int) point.getX()) - offset)%32), (((int) point.getY()) - (((int) point.getY())%32)));
    }


    boolean checkIfPlaceableAtPosition(GameState gameState, Point point) {
        int x = (int) point.getX();
        int y = (int) point.getY();
        Layer layer = gameState.getMap().getLayer(point);
        if (layer != null) {
            point = calculateCorrectCoordinates(layer,point);
            Tile tile = layer.getTile(point);
            if (tile.isTowerPlaceable() && layer.getTowerAtPosition(new Point(x, y)) == null) {
                return true;
            }
        }
        return false;
    }

    boolean checkIfPlaceableAtPositionForPoints(GameState gameState, Point[] points) {
        boolean result = true;
        for (Point point : points) {
            if (!checkIfPlaceableAtPosition(gameState, point)) {
                result = false;
            }
        }
        return result;
    }

    @Override
    public abstract void activeAction(GameState gameState, Graphics g);

    @Override
    public abstract boolean doTowerOperation(GameState gameState, Point point);
}

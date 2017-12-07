package ch.zhaw.psit.towerhopscotch.controllers.towerStrategies;

import ch.zhaw.psit.towerhopscotch.controllers.states.GameState;
import ch.zhaw.psit.towerhopscotch.models.maps.Layer;
import ch.zhaw.psit.towerhopscotch.models.tiles.Tile;
import ch.zhaw.psit.towerhopscotch.models.tower.Tower;

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

    static void drawRange(Graphics g, Point[] points, Color color, float range) {
        g.setColor(color);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(3));
        int rangeDiff = (int) range * Tile.TILE_WIDTH;
        for (Point point : points) {
            point.translate(Tile.TILE_WIDTH / 2 - rangeDiff, Tile.TILE_HEIGHT / 2 - rangeDiff);
            g2.drawOval(point.x, point.y, 2 * rangeDiff, 2 * rangeDiff);
        }
    }

    void drawText(Graphics g, Point point, Color color, Tower tower){
        g.setColor(color);
        g.drawString("Costs: " + tower.getPrice(), point.x, point.y);
    }

    boolean checkIfPlaceable(Point point, Layer layer, Tile tile1) {
        return tile1.isTowerPlaceable() && layer.getTowerAtPosition(point) == null;
    }

    Layer getLayer(GameState gameState, Point point) {
        return gameState.getMap().getLayer(point);
    }

    Point calculateCorrectCoordinates(Layer layer, Point point){
        int offset = layer.getLayerLevel() * 10;
        return new Point(((int) point.getX()) - ((((int) point.getX()) - offset) % Tile.TILE_WIDTH),
                (((int) point.getY()) - (((int) point.getY()) % Tile.TILE_HEIGHT)));
    }


    boolean checkIfPlaceableAtPosition(GameState gameState, Point point) {
        int x = (int) point.getX();
        int y = (int) point.getY();
        Layer layer = gameState.getMap().getLayer(point);
        if (layer != null) {
            point = calculateCorrectCoordinates(layer,point);
            Tile tile = layer.getTile(point);
            return tile.isTowerPlaceable() && layer.getTowerAtPosition(new Point(x, y)) == null;
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

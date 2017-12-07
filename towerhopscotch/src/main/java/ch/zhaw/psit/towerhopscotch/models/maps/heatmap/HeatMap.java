package ch.zhaw.psit.towerhopscotch.models.maps.heatmap;

import ch.zhaw.psit.towerhopscotch.GUI.Assets;
import ch.zhaw.psit.towerhopscotch.GUI.Text;
import ch.zhaw.psit.towerhopscotch.models.maps.Layer;
import ch.zhaw.psit.towerhopscotch.models.tiles.Tile;

import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;

/**
 * This class represents the path heat map for a layer.
 * @author Nicolas Eckhart
 */
public class HeatMap {
    private Layer layer;
    private int width, height;
    private int[][] heatMap;
    private Queue<HeatMapPoint> updatingPoints;

    public HeatMap(Layer layer) {
        this.layer = layer;
        width = layer.getWidth();
        height = layer.getHeight();
        heatMap = new int[height][width];
        initialize();
    }

    /**
     * Render the heat map values on each tile for debugging.
     * @param g Graphics
     */
    public void render(Graphics g) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Text.drawString(g, Integer.toString(heatMap[y][x]), x * Tile.TILE_WIDTH + layer.getOffset() + 16, y * Tile.TILE_HEIGHT + 16, true, Color.RED, Assets.font16);
            }
        }
    }

    private void initialize() {
        updatingPoints = new LinkedList<HeatMapPoint>();
        updateNeighbours(new HeatMapPoint(layer.getStartPointTile(), 0));
    }

    private void updateNeighbours(HeatMapPoint point) {
        int newValue = point.getPreviousHeatValue() + 1;
        if(heatMap[point.y()][point.x()] == 0)
            heatMap[point.y()][point.x()] = newValue;

        HeatMapPoint above = new HeatMapPoint(point.x(), point.y() - 1, newValue);
        if(point.y() - 1 >= 0 && isPath(above) && isUnset(above))
            updatingPoints.add(above);

        HeatMapPoint below = new HeatMapPoint(point.x(), point.y() + 1, newValue);
        if(point.y() + 1 < heatMap.length && isPath(below) && isUnset(below))
            updatingPoints.add(below);

        HeatMapPoint left = new HeatMapPoint(point.x() - 1, point.y(), newValue);
        if(point.x() - 1 >= 0 && isPath(left) && isUnset(left))
            updatingPoints.add(left);

        HeatMapPoint right = new HeatMapPoint(point.x() + 1, point.y(), newValue);
        if(point.x() + 1 < heatMap[0].length && isPath(right) && isUnset(right))
            updatingPoints.add(right);

        HeatMapPoint nextPoint = updatingPoints.poll();
        if(nextPoint != null)
            updateNeighbours(nextPoint);
    }

    private boolean isPath(HeatMapPoint p) {
        int tileId = layer.getTiles()[p.x()][p.y()];
        return ((tileId == 1) || (tileId == 8));
    }

    private boolean isUnset(HeatMapPoint p) {
        return heatMap[p.y()][p.x()] == 0;
    }

    public int getHeatValue(int x, int y) {
        return heatMap[y][x];
    }
}

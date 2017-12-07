package ch.zhaw.psit.towerhopscotch.models.maps.heatmap;

import java.awt.*;

public class HeatMapPoint {
    private Point point;
    private int previousHeatValue;

    public HeatMapPoint(int x, int y, int previousHeatValue) {
        this.point = new Point(x, y);
        this.previousHeatValue = previousHeatValue;
    }

    public HeatMapPoint(Point point, int previousHeatValue) {
        this.point = point;
        this.previousHeatValue = previousHeatValue;
    }

    public int getPreviousHeatValue() {
        return previousHeatValue;
    }

    public void setPreviousHeatValue(int previousHeatValue) {
        this.previousHeatValue = previousHeatValue;
    }

    public Point getPoint() {
        return point;
    }

    public int x() {
        return point.x;
    }

    public int y() {
        return point.y;
    }
}

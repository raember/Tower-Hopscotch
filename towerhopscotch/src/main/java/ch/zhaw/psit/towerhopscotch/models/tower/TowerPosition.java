package ch.zhaw.psit.towerhopscotch.models.tower;

import ch.zhaw.psit.towerhopscotch.models.entities.enemies.Enemy;

import java.awt.*;

public class TowerPosition {
    private Point position;
    private Tower tower;

    public TowerPosition(Point position, Tower tower) {
        this.position = position;
        this.tower = tower;
    }

    public TowerPosition() {
        this(new Point(0, 0), null);
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public Tower getTower() {
        return tower;
    }

    public void setTower(Tower tower) {
        this.tower = tower;
    }

    public void render(Graphics g) {
        tower.render(g, position);
    }

    public void update(long absNanoTime, java.util.List<Enemy> enemiesOnMap) {
        tower.update(absNanoTime, position, enemiesOnMap);
    }

}

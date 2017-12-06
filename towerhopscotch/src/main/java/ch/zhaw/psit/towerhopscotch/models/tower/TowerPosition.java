package ch.zhaw.psit.towerhopscotch.models.tower;

import ch.zhaw.psit.towerhopscotch.models.entities.enemies.Enemy;

import java.awt.*;

/**
 * The position for a tower on a layer
 * @author Stefan Bösch
 */
public class TowerPosition {
    private Point position;
    private Tower tower;
    private Canon canon;

    public TowerPosition(Point position, Tower tower) {
        this.position = position;
        this.tower = tower;
        this.canon = new Canon(tower);
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

    /**
     * Render the tower and the cannon
     * @param g Graphics
     */
    public void render(Graphics g) {
        canon.render(g, position);
        tower.render(g, position);
    }

    /**
     * Update the Tower and the canon
     * @param absNanoTime
     * @param enemiesOnMap
     */
    public void update(long absNanoTime, java.util.List<Enemy> enemiesOnMap) {
        canon.update(absNanoTime, position, enemiesOnMap);
        tower.update();
    }

}

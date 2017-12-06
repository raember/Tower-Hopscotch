package ch.zhaw.psit.towerhopscotch.models.tower;

import ch.zhaw.psit.towerhopscotch.models.entities.enemies.Enemy;
import ch.zhaw.psit.towerhopscotch.models.tiles.Tile;

import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents the canon for the tower on a layer
 * @author Stefan Bösch
 */
public class Canon {
    private Tower tower;

    private long currentTime = System.nanoTime();
    private long nextAttack;
    private Enemy shotEnemy;

    public Canon(Tower tower){
        this.tower = tower;
    }

    public void update(){}

    /**
     * Update the tower. This means that the enemies in reach get shot.
     * @param absNanoTime absNanoTime
     * @param towerPosition Position of the tower
     * @param enemiesOnLayer The enemies from the layer
     */
    public void update(long absNanoTime, Point towerPosition, java.util.List<Enemy> enemiesOnLayer){
        long deltaNanoTime = absNanoTime - currentTime;
        nextAttack = Math.max(nextAttack - deltaNanoTime, 0);
        currentTime = absNanoTime;
        shoot(nextAttack == 0, filterEnemies(enemiesOnLayer, towerPosition));
    }

    /**
     * Filter the enemies which are relevant for the tower at this frame
     * @param enemies All enemies
     * @param towerPosition Position of the tower
     * @return The enemies which are in reach of the tower
     */
    private List<Enemy> filterEnemies(List<Enemy> enemies, Point towerPosition) {
        return enemies.stream().filter(e -> canReach(new Point((int) e.getX(), (int) e.getY()), towerPosition)).collect(Collectors.toList());
    }

    /**
     * Check if the position of the enemy is in reach of the tower's position
     * @param positionEnemy Position Enemy
     * @param towerPosition Position Tower
     * @return True If in Reach
     */
    private boolean canReach(Point positionEnemy, Point towerPosition) {
        return towerPosition.distance(positionEnemy) / Tile.TILE_WIDTH <= tower.getFireRange();
    }

    /**
     * Shoot an enemy which deals damage
     * @param enemy Enemy to shootAt
     * @return If the enemy got shot
     */
    private boolean shootAt(Enemy enemy) {
        if (nextAttack == 0) {
            enemy.setHealth(enemy.getHealth() - tower.getDamage());
            shotEnemy = enemy;
            return true;
        }
        return false;
    }

    /**
     * Reset the Cooldown so that the tower can shoot again
     */
    private void resetCooldown() {
        nextAttack = tower.getFireFrequency();
    }

    /**
     * shoot at the enemies in reach
     * @param canShoot Can the tower shoot again
     * @param enemiesInVicinity Enemies to shoot
     */
    private void shoot(boolean canShoot, java.util.List<Enemy> enemiesInVicinity) {
        if (canShoot) {
            shotEnemy = null;
            if (!enemiesInVicinity.isEmpty()){
                boolean shotEnamies = shootAt(enemyWithLowestHealth(enemiesInVicinity));
                if (shotEnamies) resetCooldown();
            }
        }
    }

    /**
     * Check which enemy has the lowest health
     * @param enemies Enemies
     * @return The enemy with the lowest health
     */
    private Enemy enemyWithLowestHealth(java.util.List<Enemy> enemies){
        Enemy enemyWithLowestHealth = null;

        for (Enemy enemy : enemies){
            if (enemyWithLowestHealth == null || enemyWithLowestHealth.getHealth() > enemy.getHealth()){
                enemyWithLowestHealth = enemy;
            }
        }

        return enemyWithLowestHealth;
    }


    /**
     * Render the shooting
     * @param g Graphics
     * @param position Position of the tower
     */
    public void render(Graphics g, Point position){
        float[] dashingPattern2 = {10f, 4f};
        Stroke stroke1 = new BasicStroke(4f, BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_MITER, 1.0f, dashingPattern2, 0.0f);


        if (shotEnemy != null){
            int offset = Tile.TILE_WIDTH / 2;
            Tower tower = shotEnemy.getOnLayer().getTowerAtPosition(position);
            if (tower != null && tower.equals(tower)){
                Graphics2D g2 = (Graphics2D) g;
                g2.setColor(Color.RED);
                g2.setStroke(stroke1);
                g2.drawLine(position.x + offset, position.y + offset,
                        (int) shotEnemy.getX() + offset, (int) shotEnemy.getY() + offset);
                g2.setStroke(new BasicStroke(1));
            }
        }

    }

}

package ch.zhaw.psit.towerhopscotch.controllers.towerStrategies;

import ch.zhaw.psit.towerhopscotch.controllers.states.GameState;
import ch.zhaw.psit.towerhopscotch.models.maps.Layer;
import ch.zhaw.psit.towerhopscotch.models.tiles.Tile;
import ch.zhaw.psit.towerhopscotch.models.tower.FloatUpgrade;
import ch.zhaw.psit.towerhopscotch.models.tower.IntUpgrade;
import ch.zhaw.psit.towerhopscotch.models.tower.LongUpgrade;
import ch.zhaw.psit.towerhopscotch.models.tower.Tower;

import java.awt.*;
import java.util.ArrayList;

/**
 * Strategy for upgrading Tower
 *
 * @author Stefan Bösch
 */
public class UpgradeTowerStrategy implements TowerStrategy {

    /**
     * Draw action text and show new range.
     *
     * @param gameState Gamestate
     * @param g         Graphics
     */
    @Override
    public void activeAction(GameState gameState, Graphics g) {

        gameState.drawText(g,"SELECT TOWER TO UPGRADE");

        Point point1 = gameState.getMouseManager().getPosition();

        Layer layer = gameState.getMap().getLayer(point1);

        if (layer != null) {

            int offset = layer.getLayerLevel() * 10;
            point1 = new Point(((int) point1.getX()) - ((((int) point1.getX()) - offset) % Tile.TILE_WIDTH),
                    (((int) point1.getY()) - (((int) point1.getY()) % Tile.TILE_HEIGHT)));
            Tower tower = layer.getTowerAtPosition(point1);
            if (tower != null) {
                Color color = Color.RED;
                FloatUpgrade rangeUpgrade = tower.getFireRangeUpgrade();
                if (rangeUpgrade == null) return;
                IntUpgrade damageUpgrade = tower.getDamageUpgrade();
                if (rangeUpgrade == null) return;
                LongUpgrade frequencyUpgrade = tower.getFireFrequencyUpgrade();
                if (frequencyUpgrade == null) return;
                int price = rangeUpgrade.getPrice() + damageUpgrade.getPrice() + frequencyUpgrade.getPrice();
                if (gameState.getPlayer().getGold().getAmount() >= price) {
                    color = Color.GREEN;
                }
                g.setColor(color);
                g.drawString("Costs: " + price, point1.x, point1.y);

                ArrayList<Point> points = new ArrayList<>();
                points.add(point1);

                Point point2 = new Point((point1.x + 14 * 32 + 10) % 1374, point1.y);
                Layer layer2 = gameState.getMap().getLayer(point2);
                if (layer2 != null && tower.equals(layer2.getTowerAtPosition(point2))){
                    points.add(point2);
                }
                Point point3 = new Point((point1.x + 2 * (14 * 32 + 10)) % 1374, point1.y);
                Layer layer3 = gameState.getMap().getLayer(point3);
                if (layer3 != null && tower.equals(layer3.getTowerAtPosition(point3))){
                    points.add(point3);
                }

                PlaceTowerStrategy.drawRange(g, points.toArray(new Point[points.size()]), color, rangeUpgrade.getValue());
            }
        }
    }

    /**
     * Upgrade Tower at Point
     *
     * @param gameState Gamestate
     * @param point     Point
     * @return UpgradeSuccessfull
     */
    @Override
    public boolean doTowerOperation(GameState gameState, Point point) {
        Layer layer = gameState.getMap().getLayer(point);
        Tower tower = layer.getTowerAtPosition(point);

        if (tower != null) {
            boolean success1 = tower.tryPurchaseDamageUpgrade(gameState.getPlayer().getGold());
            boolean success2 = tower.tryPurchaseFireRangeUpgrade(gameState.getPlayer().getGold());
            boolean success3 = tower.tryPurchaseFireFrequencyUpgrade(gameState.getPlayer().getGold());

            if (success1 && success2 && success3) {
                tower.levelUp();
            }
            return true;
        }
        return false;
    }
}

package ch.zhaw.psit.towerhopscotch.controllers.towerStrategies;

import ch.zhaw.psit.towerhopscotch.controllers.states.GameState;
import ch.zhaw.psit.towerhopscotch.models.maps.Layer;
import ch.zhaw.psit.towerhopscotch.models.tiles.Tile;
import ch.zhaw.psit.towerhopscotch.models.tower.FloatUpgrade;
import ch.zhaw.psit.towerhopscotch.models.tower.IntUpgrade;
import ch.zhaw.psit.towerhopscotch.models.tower.LongUpgrade;
import ch.zhaw.psit.towerhopscotch.models.tower.Tower;

import java.awt.*;

/**
 * Strategy for upgrading Tower
 *
 * @author Stefan Bösch
 */
public class UpgradeTowerStrategy implements TowerStrategy {

    /**
     * Not implemented
     *
     * @param gameState Gamestate
     * @param g         Graphics
     */
    @Override
    public void activeAction(GameState gameState, Graphics g) {

        Point point = gameState.getMouseManager().getPosition();

        Layer layer = gameState.getMap().getLayer(point);

        if (layer != null) {

            int offset = layer.getLayerLevel() * 10;
            point = new Point(((int) point.getX()) - ((((int) point.getX()) - offset) % Tile.TILE_WIDTH),
                    (((int) point.getY()) - (((int) point.getY()) % Tile.TILE_HEIGHT)));
            Tower tower = layer.getTowerAtPosition(point);
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
                g.drawString("Costs: " + price, point.x, point.y);
                PlaceTowerStrategy.drawRange(g, new Point[]{point}, color, rangeUpgrade.getValue());
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

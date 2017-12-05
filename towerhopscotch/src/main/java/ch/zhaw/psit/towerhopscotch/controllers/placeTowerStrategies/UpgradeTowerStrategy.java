package ch.zhaw.psit.towerhopscotch.controllers.placeTowerStrategies;

import ch.zhaw.psit.towerhopscotch.controllers.states.GameState;
import ch.zhaw.psit.towerhopscotch.models.maps.Layer;
import ch.zhaw.psit.towerhopscotch.models.tower.Tower;

import java.awt.*;

public class UpgradeTowerStrategy implements TowerStrategy {
    @Override
    public void activeAction(GameState gameState, Graphics g) {

    }

    public boolean doTowerOperation(GameState gameState, Point point) {
        Layer layer = gameState.getMap().getLayer(point);
        Tower tower = layer.getTowerAtPosition(point);

        if (tower != null) {
            boolean success1 = tower.tryPurchaseDamageUpgrade(gameState.getPlayer().getGold());
            boolean success2 = tower.tryPurchaseFireRangeUpgrade(gameState.getPlayer().getGold());
            boolean success3 = tower.tryPurchaseFireFrequencyUpgrade(gameState.getPlayer().getGold());

            if (success1 && success2 && success3){
                tower.levelUp();
            }
            return true;
        }
        return false;
    }
}

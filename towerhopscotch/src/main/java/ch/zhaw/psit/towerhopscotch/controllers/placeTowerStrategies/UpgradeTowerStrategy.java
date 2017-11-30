package ch.zhaw.psit.towerhopscotch.controllers.placeTowerStrategies;

import ch.zhaw.psit.towerhopscotch.controllers.states.GameState;
import ch.zhaw.psit.towerhopscotch.models.maps.Layer;
import ch.zhaw.psit.towerhopscotch.models.tower.Tower;

import java.awt.*;

public class UpgradeTowerStrategy implements TowerStrategy {
    public boolean doTowerOperation(GameState gameState, Point point) {
        Layer layer = gameState.getMap().getLayer((float) point.getX(),(float) point.getY());
        Tower tower = layer.getTowerAtPosition(point);

        if (tower != null){

            System.out.println(tower.tryPurchaseDamageUpgrade(gameState.getPlayer()));
            System.out.println(tower.tryPurchaseFireRangeUpgrade(gameState.getPlayer()));
            System.out.println(tower.tryPurchaseFireFrequencyUpgrade(gameState.getPlayer()));

            return true;
        }


        return false;
    }
}

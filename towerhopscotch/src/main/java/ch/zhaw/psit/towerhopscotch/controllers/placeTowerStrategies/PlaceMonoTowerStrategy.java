package ch.zhaw.psit.towerhopscotch.controllers.placeTowerStrategies;

import ch.zhaw.psit.towerhopscotch.controllers.states.GameState;
import ch.zhaw.psit.towerhopscotch.models.Player;
import ch.zhaw.psit.towerhopscotch.models.maps.Layer;
import ch.zhaw.psit.towerhopscotch.models.tower.MonoTower;
import ch.zhaw.psit.towerhopscotch.models.tower.Tower;

import java.awt.*;

public class PlaceMonoTowerStrategy implements TowerStrategy {

    public boolean doTowerOperation(GameState gameState, Point point) {
        Layer layer = gameState.getMap().getLayer((float) point.getX(),(float) point.getY());
        if (layer.getTowerAtPosition(point) != null) return false;

        Player player = gameState.getPlayer();
        Tower tower = new MonoTower();
        int price = tower.getPrice();

        if (player.getGold().getAmount() >= price) {
            player.addGold(-price);
            tower.setPosition(point);
            layer.addTower(tower);
            return true;
        }
        return false;
    }
}

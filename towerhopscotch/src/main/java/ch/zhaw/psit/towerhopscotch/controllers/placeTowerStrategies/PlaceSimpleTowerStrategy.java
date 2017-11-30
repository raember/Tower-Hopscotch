package ch.zhaw.psit.towerhopscotch.controllers.placeTowerStrategies;

import ch.zhaw.psit.towerhopscotch.controllers.states.GameState;
import ch.zhaw.psit.towerhopscotch.models.maps.Layer;
import ch.zhaw.psit.towerhopscotch.models.tower.MonoTower;
import ch.zhaw.psit.towerhopscotch.models.tower.Tower;

import java.awt.*;

public class PlaceMonoTowerStrategy implements TowerStrategy {

    public boolean doTowerOperation(GameState gameState, Point point) {

        Layer layer = gameState.getMap().getLayer((float) point.getX(),(float) point.getY());
        Tower towerAtPosition = layer.getTowerAtPosition(point);

        Tower tower = new MonoTower();
        int price = tower.getPrice();

        if (gameState.getPlayer().getGold() - price >= 0 && towerAtPosition == null){
            gameState.getPlayer().addGold(-price);

            tower.setPosition(point);

            Layer layer = gameState.getMap().getLayer((float) point.getX(),(float) point.getY());
            layer.addTower(tower);


        } else {
            return false;
        }

        return false;
    }
}

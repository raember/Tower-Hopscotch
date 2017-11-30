package ch.zhaw.psit.towerhopscotch.controllers.placeTowerStrategies;

import ch.zhaw.psit.towerhopscotch.controllers.states.GameState;
import ch.zhaw.psit.towerhopscotch.models.maps.Layer;
import ch.zhaw.psit.towerhopscotch.models.tower.MonoTower;
import ch.zhaw.psit.towerhopscotch.models.tower.Tower;

import java.awt.*;

public class PlaceMonoTowerStrategy implements TowerStrategy {

    private int price = 500;

    public boolean doTowerOperation(GameState gameState, Point point) {

        if (gameState.getPlayer().getGold() - price >= 0){
            gameState.getPlayer().addGold(-price);

            Tower tower = new MonoTower(500, null);
            tower.setPosition(point);

            Layer layer = gameState.getMap().getLayer((float) point.getX(),(float) point.getY());
            layer.addTower(tower);


        } else {
            return false;
        }

        return false;
    }
}

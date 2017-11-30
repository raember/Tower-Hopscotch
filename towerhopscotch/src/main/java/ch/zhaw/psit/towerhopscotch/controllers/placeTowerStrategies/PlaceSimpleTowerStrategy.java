package ch.zhaw.psit.towerhopscotch.controllers.placeTowerStrategies;

import ch.zhaw.psit.towerhopscotch.controllers.states.GameState;
import ch.zhaw.psit.towerhopscotch.models.maps.Layer;
import ch.zhaw.psit.towerhopscotch.models.tower.MonoTower;
import ch.zhaw.psit.towerhopscotch.models.tower.SimpleTower;
import ch.zhaw.psit.towerhopscotch.models.tower.Tower;

import java.awt.*;

public class PlaceSimpleTowerStrategy implements TowerStrategy {

    public boolean doTowerOperation(GameState gameState, Point point) {

        Tower tower = new SimpleTower();
        int price = tower.getPrice();

        if (gameState.getPlayer().getGold() - price >= 0){
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

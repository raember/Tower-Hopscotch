package ch.zhaw.psit.towerhopscotch.controllers.placeTowerStrategies;

import ch.zhaw.psit.towerhopscotch.controllers.states.GameState;
import ch.zhaw.psit.towerhopscotch.models.maps.Layer;
import ch.zhaw.psit.towerhopscotch.models.tower.Tower;

import java.awt.*;

public class TearDownTowerStrategy implements TowerStrategy {
    @Override
    public void activeAction(GameState gameState, Graphics g) {

    }

    public boolean doTowerOperation(GameState gameState, Point point) {

        Layer layer = gameState.getMap().getLayer((float) point.getX(),(float) point.getY());
        Tower tower = layer.getTowerAtPosition(point);

        if (tower != null){
            layer.removeTower(tower);

            gameState.getPlayer().addGold(tower.getPrice()/2);

            return true;
        }

        return false;
    }
}

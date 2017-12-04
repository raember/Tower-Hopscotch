package ch.zhaw.psit.towerhopscotch.controllers.placeTowerStrategies;

import ch.zhaw.psit.towerhopscotch.controllers.states.GameState;
import ch.zhaw.psit.towerhopscotch.models.Player;
import ch.zhaw.psit.towerhopscotch.models.maps.Layer;
import ch.zhaw.psit.towerhopscotch.models.maps.Map;
import ch.zhaw.psit.towerhopscotch.models.tower.DoubleTower;

import java.awt.*;

public class PlaceDoubleTowerStrategy implements TowerStrategy {

    public boolean doTowerOperation(GameState gameState, Point point) {
        Map map = gameState.getMap();
        Point lowerPoint = point;
        Layer lowerLayer = map.getLayer((float) lowerPoint.x, (float) lowerPoint.y);
        if (lowerLayer == map.getHeaven() ||
                lowerLayer.getTowerAtPosition(lowerPoint) != null) return false;

        Point upperPoint = new Point(lowerPoint.x + Layer.LAYER_WIDTH + 10, lowerPoint.y);
        Layer upperLayer = map.getLayer((float) upperPoint.x, (float) upperPoint.y);
        if (upperLayer.getTowerAtPosition(upperPoint) != null) return false;

        Player player = gameState.getPlayer();
        DoubleTower lowerTower = new DoubleTower();
        DoubleTower upperTower = new DoubleTower();
        lowerTower.setChild(upperTower);

        int price = lowerTower.getPrice();
        if (player.getGold().getAmount() >= price) {
            player.addGold(-price);
            lowerTower.setPosition(lowerPoint);
            lowerLayer.addTower(lowerTower);
            upperTower.setPosition(upperPoint);
            upperLayer.addTower(upperTower);
            return true;
        }
        return false;
    }
}
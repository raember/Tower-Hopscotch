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
        Layer lowerLayer = map.getLayer((float) point.getX(), (float) point.getY());
        if (lowerLayer == map.getHeaven() || lowerLayer.getTowerAtPosition(point) != null) return false;
        Layer upperLayer = map.getLayer((float) point.getX() - Layer.LAYER_WIDTH - 10, (float) point.getY());

        Point upperPoint = new Point(point.x - Layer.LAYER_WIDTH - 10, point.y);
        if (!upperLayer.getTile(upperPoint.x, upperPoint.y).isFortress() || upperLayer.getTowerAtPosition(upperPoint) != null)
            return false;

        Player player = gameState.getPlayer();
        DoubleTower lowerTower = new DoubleTower();
        DoubleTower upperTower = new DoubleTower();
        lowerTower.setChild(upperTower);

        int price = lowerTower.getPrice();
        if (player.getGold().getAmount() >= price) {
            player.addGold(-price);
            lowerTower.setPosition(point);
            lowerLayer.addTower(lowerTower);
            upperTower.setPosition(upperPoint);
            upperLayer.addTower(upperTower);
            return true;
        }
        return false;
    }
}
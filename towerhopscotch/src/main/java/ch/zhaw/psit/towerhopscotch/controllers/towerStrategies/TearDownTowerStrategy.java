package ch.zhaw.psit.towerhopscotch.controllers.towerStrategies;

import ch.zhaw.psit.towerhopscotch.controllers.states.GameState;
import ch.zhaw.psit.towerhopscotch.models.maps.Layer;
import ch.zhaw.psit.towerhopscotch.models.tower.Tower;

import java.awt.*;

/**
 * Strategy for destroying Tower
 * @author Stefan Bösch
 */
public class TearDownTowerStrategy implements TowerStrategy {

    /**
     * Not implemented
     * @param gameState Gamestate
     * @param g Graphics
     */
    @Override
    public void activeAction(GameState gameState, Graphics g) {}

    /**
     * Destroy Tower at Position
     * @param gameState Gamestate
     * @param point Position
     * @return sucessfullyCreated
     */
    @Override
    public boolean doTowerOperation(GameState gameState, Point point) {

        Layer layer = gameState.getMap().getLayer(point);
        Tower tower = layer.getTowerAtPosition(point);

        if (tower != null) {
            tower.remove();

            gameState.getPlayer().addGold(tower.getPrice() / 2);

            return true;
        }

        return false;
    }
}

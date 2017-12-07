package ch.zhaw.psit.towerhopscotch.controllers.towerStrategies;

import ch.zhaw.psit.towerhopscotch.controllers.states.GameState;

import java.awt.*;

/**
 * Interface for the TowerStrategies.
 */
public interface TowerStrategy {

    Color placeable = new Color(0, 200, 0, 70);
    Color notPlaceable = new Color(200, 0, 0, 70);

    /**
     * Draw placeable Gizmos
     * @param gameState Gamestate
     * @param g Graphics
     */
    void activeAction(GameState gameState, Graphics g);

    /**
     * Do Toweroperation at Position
     * @param gameState Gamestate
     * @param point Point
     * @return OperationSuccessful
     */
    boolean doTowerOperation(GameState gameState, Point point);
}

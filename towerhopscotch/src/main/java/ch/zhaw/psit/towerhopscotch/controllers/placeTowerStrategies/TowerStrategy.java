package ch.zhaw.psit.towerhopscotch.controllers.placeTowerStrategies;

import ch.zhaw.psit.towerhopscotch.controllers.states.GameState;

import java.awt.*;

public interface TowerStrategy {
        boolean doTowerOperation(GameState gameState, Point point);
}

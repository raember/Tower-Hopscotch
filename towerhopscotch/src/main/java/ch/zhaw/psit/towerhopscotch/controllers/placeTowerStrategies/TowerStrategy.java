package ch.zhaw.psit.towerhopscotch.controllers.placeTowerStrategies;

import ch.zhaw.psit.towerhopscotch.controllers.states.GameState;

import java.awt.*;

public interface TowerStrategy {

    Color placeable = new Color(0,200,0,50);
    Color notPlaceable = new Color(200,0,0,50);

    void activeAction(GameState gameState, Graphics g);

    boolean doTowerOperation(GameState gameState, Point point);
}

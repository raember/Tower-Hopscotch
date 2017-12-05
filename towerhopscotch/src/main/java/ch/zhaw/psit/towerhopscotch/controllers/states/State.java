package ch.zhaw.psit.towerhopscotch.controllers.states;

import ch.zhaw.psit.towerhopscotch.GUI.input.MouseManager;

import java.awt.*;

/**
 * Base class for the states
 * @author Nicolas Eckhart
 */
public abstract class State {
    private static State currentState = null;
    protected MouseManager mouseManager;

    public State(MouseManager mouseManager){
        this.mouseManager = mouseManager;
    }

    public static void setState(State state) {
        currentState = state;
    }

    public static State getState() {
        return currentState;
    }

    /**
     * Update state
     */
    public abstract void update();

    /**
     * Render state
     * @param g Graphics
     */
    public abstract void render(Graphics g);

    public MouseManager getMouseManager() {
        return mouseManager;
    }
}
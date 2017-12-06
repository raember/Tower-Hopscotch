package ch.zhaw.psit.towerhopscotch.models.entities;

import ch.zhaw.psit.towerhopscotch.models.maps.Layer;
import ch.zhaw.psit.towerhopscotch.models.maps.Map;
import ch.zhaw.psit.towerhopscotch.controllers.states.GameState;
import ch.zhaw.psit.towerhopscotch.controllers.states.State;

import java.awt.*;

/**
 * This class represents an entity
 * @author Nicolas Eckhart
 */
public abstract class Entity {

    protected float x, y;
    protected int width, height;
    protected Layer onLayer;

    public Entity(Layer onLayer, float x, float y, int width, int height) {
        this.onLayer = onLayer;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * Update the object
     */
    public abstract void update();

    /**
     * Render the object
     * @param g Graphics
     */
    public abstract void render(Graphics g);

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    protected Map getMap() {
        return ((GameState) State.getState()).getMap();
    }

    public Layer getOnLayer(){
        return onLayer;
    }

    public void setOnLayer(Layer onLayer) {
        this.onLayer = onLayer;
    }
}

package ch.zhaw.psit.towerhopscotch.models.entities;

import ch.zhaw.psit.towerhopscotch.Game;
import ch.zhaw.psit.towerhopscotch.maps.Layer;
import ch.zhaw.psit.towerhopscotch.maps.Map;
import ch.zhaw.psit.towerhopscotch.states.GameState;
import ch.zhaw.psit.towerhopscotch.states.State;

import java.awt.*;

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

    public abstract void update();

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
}

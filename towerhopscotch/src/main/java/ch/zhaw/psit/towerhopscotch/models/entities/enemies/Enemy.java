package ch.zhaw.psit.towerhopscotch.models.entities.enemies;

import ch.zhaw.psit.towerhopscotch.models.Player;
import ch.zhaw.psit.towerhopscotch.models.entities.Entity;
import ch.zhaw.psit.towerhopscotch.models.enums.Direction;
import ch.zhaw.psit.towerhopscotch.models.enums.LayerType;
import ch.zhaw.psit.towerhopscotch.models.maps.Layer;
import ch.zhaw.psit.towerhopscotch.models.tiles.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;

/**
 * Represents an enemy which tries to attack the base from the player
 * @author Nicolas Eckhart
 */
public abstract class Enemy extends Entity {
    public static final int DEFAULT_WIDTH = 32, DEFAULT_HEIGHT = 32;

    protected int health;
    protected int damage;
    protected float speed;
    protected float xMove, yMove;
    protected Direction moveDirection;
    protected Player player;
    protected int reward;
    protected int currentPositionHeat;

    public Enemy(Layer onLayer, float x, float y, int width, int height, int health, int damage, float speed, Player player, int reward) {
        super(onLayer, x, y, width, height);
        moveDirection = Direction.UP;
        this.health = health;
        this.damage = damage;
        this.speed = speed;
        xMove = 0;
        yMove = 0;
        this.player = player;
        this.reward = reward;
        currentPositionHeat = 1;
    }

    /**
     * Updates the enemies coordinates based on its current location on the layer..
     */
    public void move() {
        xMove = 0;
        yMove = 0;

        if (movementPossible(moveDirection)) {
            setMovementForDirection();
        } else {
            changeDirection();
            setMovementForDirection();
        }

        x += xMove;
        y += yMove;
    }

    /**
     * Teleports the enemy onto a different layer if there is a tile on his current x and y
     * coordinates on the target layer. Teleportation occurs randomly.
     *
     * @return boolean if teleported or not
     */
    public boolean teleport() {
        Random random = new Random();
        ArrayList<Layer> layers = onLayer.getTeleportableLayers(x, y, x + (width - 1), y + (height - 1));
        if(layers.size() == 0)
            return false;

        int randomNum = 1 + (int)(Math.random() * 10000);
        if(randomNum < 10) {
            Layer selectedLayer = layers.get(random.nextInt(layers.size()));
            setX(x + calculateTeleportOffset(onLayer.getLayerType(), selectedLayer.getLayerType()));

            selectedLayer.addEnemy(this);
            onLayer = selectedLayer;
            moveDirection = Direction.UP;
            return true;
        }
        return false;
    }

    private int calculateTeleportOffset(LayerType startLayer, LayerType endLayer) {
        int offset = 0;
        switch(startLayer) {
            case HELL:
                if(endLayer == LayerType.EARTH)
                    offset = (Layer.LAYER_WIDTH + 10);
                if(endLayer == LayerType.HEAVEN)
                    offset = (2*Layer.LAYER_WIDTH + 20);
                break;
            case EARTH:
                if(endLayer == LayerType.HELL)
                    offset = -(Layer.LAYER_WIDTH + 10);
                if(endLayer == LayerType.HEAVEN)
                    offset = (Layer.LAYER_WIDTH + 10);
                break;
            case HEAVEN:
                if(endLayer == LayerType.HELL)
                    offset = -(2*Layer.LAYER_WIDTH + 20);
                if(endLayer == LayerType.EARTH)
                    offset = -(Layer.LAYER_WIDTH + 10);
                break;
        }
        return offset;
    }

    private void changeDirection() {
        HashMap<Direction, Integer> possibleDirectionsWithHeat = new HashMap<Direction, Integer>();
        if(upwardsPossible())
            possibleDirectionsWithHeat.put(Direction.UP, getHeatForDirection(Direction.UP));
        if(leftwardsPossible())
            possibleDirectionsWithHeat.put(Direction.LEFT, getHeatForDirection(Direction.LEFT));
        if(rightwardsPossible())
            possibleDirectionsWithHeat.put(Direction.RIGHT, getHeatForDirection(Direction.RIGHT));
        if(downwardsPossible())
            possibleDirectionsWithHeat.put(Direction.DOWN, getHeatForDirection(Direction.DOWN));

        // Out of all possible directions, select the path with the highest value on the heat map.
        int maximumHeat = Collections.max(possibleDirectionsWithHeat.values());
        for(Direction direction : possibleDirectionsWithHeat.keySet()) {
            if(possibleDirectionsWithHeat.get(direction) == maximumHeat) {
                moveDirection = direction;
                break;
            }
        }
    }

    private void setMovementForDirection() {
        switch (moveDirection) {
            case UP:
                yMove = -speed;
                break;
            case LEFT:
                xMove = -speed;
                break;
            case RIGHT:
                xMove = speed;
                break;
            case DOWN:
                yMove = speed;
                break;
        }
    }

    private boolean movementPossible(Direction direction) {
        if(onLayer.isBeneathMap(x, y))
            return true;

        switch (direction) {
            case UP:
                return upwardsPossible();
            case LEFT:
                return leftwardsPossible();
            case RIGHT:
                return rightwardsPossible();
            case DOWN:
                return downwardsPossible();
        }

        return false;
    }

    private boolean upwardsPossible() {
        return currentPositionHeat <= onLayer.getHeatValue(x, y - speed);
    }

    private boolean leftwardsPossible() {
        return currentPositionHeat <= onLayer.getHeatValue(x - speed, y);
    }

    private boolean rightwardsPossible() {
        return currentPositionHeat <= onLayer.getHeatValue(x + (width - 1) + speed, y);
    }

    private boolean downwardsPossible() {
        return currentPositionHeat <= onLayer.getHeatValue(x, y + (height - 1) + speed);
    }

    private int getHeatForDirection(Direction direction) {
        switch (direction) {
            case UP:
                return onLayer.getHeatValue(x, y - speed);
            case LEFT:
                return onLayer.getHeatValue(x - speed, y);
            case RIGHT:
                return onLayer.getHeatValue(x + (width - 1) + speed, y);
            case DOWN:
                return onLayer.getHeatValue(x, y + (height - 1) + speed);
        }
        return 0;
    }

    /**
     * Has the enemy reached the player base
     * @return reached base
     */
    public boolean reachedDestination() {
        return onLayer.isFortress(x, y + (height - 1)) && onLayer.isFortress(x + (width - 1), y + (height - 1));
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = Math.max(health, 0);
        if (this.health == 0) {
            onLayer.removeEnemy(this);
            player.addGold(reward);
        }
    }

    public int getDamage() {
        return damage;
    }

    /**
     * Render the enemy with the given image
     * @param g Graphics
     * @param img The Image to render
     */
    protected void renderEnemy(Graphics g, BufferedImage img){
        g.drawImage(img, (int) x, (int) y, width, height, null);
        g.setColor(Color.GREEN);
        g.drawRect((int) getX(), (int) getY(), Tile.TILE_WIDTH, Tile.TILE_HEIGHT / 10);
        g.setColor(getColor());
        g.fillRect((int) getX(), (int) getY(), Tile.TILE_WIDTH * health / 100, Tile.TILE_HEIGHT / 10);
    }

    protected Color getColor() {
        if (health <= 25)
            return Color.RED;
        if (health <= 50)
            return Color.ORANGE;
        if (health <= 75)
            return Color.YELLOW;
        return Color.GREEN;
    }
}

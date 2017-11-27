package ch.zhaw.psit.towerhopscotch.models.entities.enemies;

import ch.zhaw.psit.towerhopscotch.models.enums.Direction;
import ch.zhaw.psit.towerhopscotch.models.maps.Layer;
import ch.zhaw.psit.towerhopscotch.models.entities.Entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Enemy extends Entity {
    public static final int DEFAULT_WIDTH = 32, DEFAULT_HEIGHT = 32;

    protected int health;
    protected int damage;
    protected float speed;
    protected float xMove, yMove;
    protected Direction moveDirection;

    public Enemy(Layer onLayer, float x, float y, int width, int height, int health, int damage, float speed) {
        super(onLayer, x, y, width, height);
        moveDirection = Direction.UP;
        this.health = health;
        this.damage = damage;
        this.speed = speed;
        xMove = 0;
        yMove = 0;
    }

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

    /* NOT NEEDED AT THE MOMENT...
    private ArrayList<Direction> getAllPossibleDirections() {
        ArrayList<Direction> possibleDirections = new ArrayList<Direction>();

        if (moveDirection == Direction.UP) {
            if (upwardsPossible())
                possibleDirections.add(Direction.UP);
            if (leftwardsPossible())
                possibleDirections.add(Direction.LEFT);
            if (rightwardsPossible())
                possibleDirections.add(Direction.RIGHT);
        } else if (moveDirection == Direction.DOWN) {
            if (downwardsPossible())
                possibleDirections.add(Direction.DOWN);
            if (leftwardsPossible())
                possibleDirections.add(Direction.LEFT);
            if (rightwardsPossible())
                possibleDirections.add(Direction.RIGHT);
        } else if (moveDirection == Direction.LEFT) {
            if (leftwardsPossible())
                possibleDirections.add(Direction.LEFT);
            if (upwardsPossible())
                possibleDirections.add(Direction.UP);
            if (downwardsPossible())
                possibleDirections.add(Direction.DOWN);
        } else if (moveDirection == Direction.RIGHT) {
            if (rightwardsPossible())
                possibleDirections.add(Direction.RIGHT);
            if (upwardsPossible())
                possibleDirections.add(Direction.UP);
            if (downwardsPossible())
                possibleDirections.add(Direction.DOWN);
        }

        return possibleDirections;
    }

    private void choseDirectionFromList(ArrayList<Direction> directions) {
        moveDirection = directions.get(new Random().nextInt(directions.size()));
    }*/

    private void changeDirection() {
        Direction currentDirection = moveDirection;

        if (currentDirection == Direction.UP) {
            if (leftwardsPossible()) {
                moveDirection = Direction.LEFT;
            } else if (rightwardsPossible()) {
                moveDirection = Direction.RIGHT;
            }
        } else if (currentDirection == Direction.DOWN) {
            if (leftwardsPossible()) {
                moveDirection = Direction.LEFT;
            } else if (rightwardsPossible()) {
                moveDirection = Direction.RIGHT;
            }
        } else if (currentDirection == Direction.LEFT) {
            if (upwardsPossible()) {
                moveDirection = Direction.UP;
            } else if (downwardsPossible()) {
                moveDirection = Direction.DOWN;
            }
        } else if (currentDirection == Direction.RIGHT) {
            if (upwardsPossible()) {
                moveDirection = Direction.UP;
            } else if (downwardsPossible()) {
                moveDirection = Direction.DOWN;
            }
        }
    }

    private boolean movementPossible(Direction direction) {
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

    private boolean upwardsPossible() {
        // Enemies spawn below the map so they come in one at a time
        return onLayer.isBeneathMap(x, y) || (onLayer.isPath(x, y - speed) && onLayer.isPath(x + (width - 1), y - speed));
    }

    private boolean leftwardsPossible() {
        return onLayer.isPath(x - speed, y) && onLayer.isPath(x - speed, y + (height - 1));
    }

    private boolean rightwardsPossible() {
        return onLayer.isPath(x + (width - 1) + speed, y) && onLayer.isPath(x + (width - 1) + speed, y + (height - 1));
    }

    private boolean downwardsPossible() {
        return onLayer.isPath(x, y + (height - 1) + speed) && onLayer.isPath(x + (width - 1), y + (height - 1) + speed);
    }

    public boolean reachedDestination() {
        return onLayer.isFortress(x, y + (height - 1)) && onLayer.isFortress(x + (width - 1), y + (height - 1));
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public float getSpeed() {
        return speed;
    }

    public int getDamage() {
        return damage;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    protected void renderEnemy(Graphics g, BufferedImage img){
        g.drawImage(img, (int) x, (int) y, width, height, null);
    }
}

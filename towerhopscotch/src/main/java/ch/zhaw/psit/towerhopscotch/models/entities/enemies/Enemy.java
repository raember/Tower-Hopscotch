package ch.zhaw.psit.towerhopscotch.models.entities.enemies;

import ch.zhaw.psit.towerhopscotch.Game;
import ch.zhaw.psit.towerhopscotch.maps.Map;
import ch.zhaw.psit.towerhopscotch.models.entities.Entity;

public abstract class Enemy extends Entity {
    public static final int DEFAULT_WIDTH = 32, DEFAULT_HEIGHT = 32;

    protected int health;
    protected int damage;
    protected float speed;
    protected float xMove, yMove;
    protected Direction moveDirection;

    public enum Direction {
        LEFT, RIGHT, UP, DOWN
    }

    public Enemy(Game game, float x, float y, int width, int height, int health, int damage, float speed) {
        super(game, x, y, width, height);
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

        // Move if current movement direction is possible, else change direction and move
        if (movementPossible(moveDirection)) {
            setMovementForDirection(moveDirection);
        } else {
            changeDirection();
            setMovementForDirection(moveDirection);
        }

        x += xMove;
        y += yMove;
    }

    private void changeDirection() {
        Direction currentDirection = moveDirection;

        if (currentDirection == Direction.UP) {
            if (leftwardsPossible()) {
                moveDirection = Direction.LEFT;
            } else if (rightwardsPossible()) {
                moveDirection = Direction.RIGHT;
            } else {
                moveDirection = Direction.DOWN;
            }
        } else if (currentDirection == Direction.DOWN) {
            if (leftwardsPossible()) {
                moveDirection = Direction.LEFT;
            } else if (rightwardsPossible()) {
                moveDirection = Direction.RIGHT;
            } else {
                moveDirection = Direction.UP;
            }
        } else if (currentDirection == Direction.LEFT) {
            if (upwardsPossible()) {
                moveDirection = Direction.UP;
            } else if (downwardsPossible()) {
                moveDirection = Direction.DOWN;
            } else {
                moveDirection = Direction.RIGHT;
            }
        } else if (currentDirection == Direction.RIGHT) {
            if (upwardsPossible()) {
                moveDirection = Direction.UP;
            } else if (downwardsPossible()) {
                moveDirection = Direction.DOWN;
            } else {
                moveDirection = Direction.LEFT;
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

    private void setMovementForDirection(Direction direction) {
        switch (direction) {
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
        Map map = game.getMap();
        // Enemies spawn below the map so they come in one at a time
        return map.isBeneathMap(x, y) || (map.isPath(x, y - speed) && map.isPath(x + (width - 1), y - speed));
    }

    private boolean leftwardsPossible() {
        return game.getMap().isPath(x - speed, y) && game.getMap().isPath(x - speed, y + (height - 1));
    }

    private boolean rightwardsPossible() {
        return game.getMap().isPath(x + (width - 1) + speed, y) && game.getMap().isPath(x + (width - 1) + speed, y + (height - 1));
    }

    private boolean downwardsPossible() {
        return game.getMap().isPath(x, y + (height - 1) + speed) && game.getMap().isPath(x + (width - 1), y + (height - 1) + speed);
    }

    public boolean reachedDestination() {
        return game.getMap().isFortress(x, y + (height - 1)) && game.getMap().isFortress(x + (width - 1), y + (height - 1));
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

    public void setSpeed(float speed) {
        this.speed = speed;
    }
}

package ch.zhaw.psit.towerhopscotch.models.entities.enemies;

import ch.zhaw.psit.towerhopscotch.Game;
import ch.zhaw.psit.towerhopscotch.models.entities.Entity;

public abstract class Enemy extends Entity {

    public static final int DEFAULT_HEALTH = 100;
    public static final float DEFAULT_SPEED = 1.0f;
    public static final int DEFAULT_WIDTH = 32, DEFAULT_HEIGHT = 32;

    protected int health;
    protected float speed;
    protected float xMove, yMove;

    protected Direction moveDirection;

    public enum Direction {
        LEFT, RIGHT, UP, DOWN
    }

    public Enemy(Game game, float x, float y, int width, int height) {
        super(game, x, y, width, height);
        moveDirection = Direction.UP;
        health = DEFAULT_HEALTH;
        speed = DEFAULT_SPEED;
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

        if(currentDirection == Direction.UP) {
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

    public boolean upwardsPossible() {
        return game.getMap().isPath(x, y - speed) && game.getMap().isPath(x + (width - 1), y - speed);
    }

    public boolean leftwardsPossible() {
        return game.getMap().isPath(x - speed, y) && game.getMap().isPath(x - speed, y + (height - 1));
    }

    public boolean rightwardsPossible() {
        return game.getMap().isPath(x + (width - 1) + speed, y) && game.getMap().isPath(x + (width - 1) + speed, y + (height - 1));
    }

    public boolean downwardsPossible() {
        return game.getMap().isPath(x, y + (height - 1) + speed) && game.getMap().isPath(x + (width - 1), y + (height - 1) + speed);
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

    public float getxMove() {
        return xMove;
    }

    public void setxMove(float xMove) {
        this.xMove = xMove;
    }

    public float getyMove() {
        return yMove;
    }

    public void setyMove(float yMove) {
        this.yMove = yMove;
    }
}

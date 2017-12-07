package ch.zhaw.psit.towerhopscotch.GUI.input;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * The mouse manager
 * @author Nicolas Eckhart
 */
public class MouseManager implements MouseListener, MouseMotionListener {

    private boolean leftPressed, rightPressed;
    private Point position = new Point(0, 0);
    private int lastClickX, lastClickY;

    public boolean isLeftPressed() {
        return leftPressed;
    }

    public boolean isRightPressed() {
        return rightPressed;
    }

    public int getMouseX() {
        return ((int) position.getX());
    }

    public int getMouseY() {
        return ((int) position.getY());
    }

    public Point getPosition() {
        return position;
    }

    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1)
            leftPressed = true;
        else if (e.getButton() == MouseEvent.BUTTON3)
            rightPressed = true;
    }

    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1)
            leftPressed = false;
        else if (e.getButton() == MouseEvent.BUTTON3)
            rightPressed = false;
    }

    public void mouseMoved(MouseEvent e) {
        position.setLocation(e.getX(), e.getY());
    }

    /**
     * Not implemented
     * @param e MouseEvent
     */
    public void mouseEntered(MouseEvent e) {}

    /**
     * Not implemented
     * @param e MouseEvent
     */
    public void mouseExited(MouseEvent e) {}

    /**
     * Not implemented
     * @param e MouseEvent
     */
    public void mouseDragged(MouseEvent e) {}

    public void mouseClicked(MouseEvent e) {
        lastClickX = e.getX();
        lastClickY = e.getY();
    }
}
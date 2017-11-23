package ch.zhaw.psit.towerhopscotch.tower;

import java.awt.*;

/**
 * Created by Raphael Emberger on 13.11.2017.
 */
public abstract class Tower {
    private int price;
    private Color color;

    protected Tower(int price, Color color) {
        this.price = price;
        this.color = color;
    }
}

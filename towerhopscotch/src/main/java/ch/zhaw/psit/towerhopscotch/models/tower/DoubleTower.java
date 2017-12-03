package ch.zhaw.psit.towerhopscotch.models.tower;

import ch.zhaw.psit.towerhopscotch.GUI.Assets;
import ch.zhaw.psit.towerhopscotch.controllers.states.GameState;
import ch.zhaw.psit.towerhopscotch.models.maps.Layer;
import ch.zhaw.psit.towerhopscotch.models.maps.Map;
import ch.zhaw.psit.towerhopscotch.models.tiles.Tile;

import java.awt.*;

/**
 * Created by Raphael Emberger on 03.12.2017.
 */
public class DoubleTower extends Tower {
    public DoubleTower() {
        super(20, Assets.towers.get("doubleTower"));
        setFireFrequency(2000000000);
        fireFrequencyUpgrades.add(new LongUpgrade(10, 1800000000));
        fireFrequencyUpgrades.add(new LongUpgrade(25, 1600000000));
        fireFrequencyUpgrades.add(new LongUpgrade(40, 1400000000));
        fireFrequencyUpgrades.add(new LongUpgrade(70, 1200000000));
        fireFrequencyUpgrades.add(new LongUpgrade(120, 1000000000));
        fireFrequencyUpgrades.add(new LongUpgrade(190, 800000000));
        fireFrequencyUpgrades.add(new LongUpgrade(310, 600000000));
        fireFrequencyUpgrades.add(new LongUpgrade(500, 400000000));

        setFireRange(2f);
        fireRangeUpgrades.add(new FloatUpgrade(10, 1.5f));
        fireRangeUpgrades.add(new FloatUpgrade(25, 2f));
        fireRangeUpgrades.add(new FloatUpgrade(40, 2.5f));
        fireRangeUpgrades.add(new FloatUpgrade(70, 3f));
        fireRangeUpgrades.add(new FloatUpgrade(120, 3.5f));
        fireRangeUpgrades.add(new FloatUpgrade(190, 4f));
        fireRangeUpgrades.add(new FloatUpgrade(310, 4.5f));
        fireRangeUpgrades.add(new FloatUpgrade(500, 5f));

        setDamage(10);
        damageUpgrades.add(new IntUpgrade(10, 15));
        damageUpgrades.add(new IntUpgrade(25, 20));
        damageUpgrades.add(new IntUpgrade(40, 25));
        damageUpgrades.add(new IntUpgrade(70, 30));
        damageUpgrades.add(new IntUpgrade(120, 40));
        damageUpgrades.add(new IntUpgrade(190, 50));
        damageUpgrades.add(new IntUpgrade(310, 70));
        damageUpgrades.add(new IntUpgrade(500, 90));
    }

    @Override
    public boolean canBePlaced(GameState gameState) {
        Point position = getPosition();
        Map map = gameState.getMap();
        Layer layer = map.getLayer((float) position.getX(), (float) position.getY());
        if (layer == map.getHell()) {
            Tile lowTile = layer.getTile((float) position.getX(), (float) position.getY());
            Tile highTile = map.getEarth().getTile((float) position.getX() - Layer.LAYER_WIDTH - 10, (float) position.getY());
            return lowTile.isFortress() && highTile.isFortress();
        } else if (layer == map.getEarth()) {
            Tile lowTile = layer.getTile((float) position.getX(), (float) position.getY());
            Tile highTile = map.getHeaven().getTile((float) position.getX() - Layer.LAYER_WIDTH - 10, (float) position.getY());
            return lowTile.isFortress() && highTile.isFortress();
        }
        return false;
    }
}
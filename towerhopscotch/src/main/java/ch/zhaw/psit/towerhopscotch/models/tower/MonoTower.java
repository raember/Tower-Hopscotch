package ch.zhaw.psit.towerhopscotch.models.tower;

import ch.zhaw.psit.towerhopscotch.GUI.Assets;
import ch.zhaw.psit.towerhopscotch.controllers.states.GameState;
import ch.zhaw.psit.towerhopscotch.models.maps.Layer;
import ch.zhaw.psit.towerhopscotch.models.maps.Map;
import ch.zhaw.psit.towerhopscotch.models.tiles.Tile;

import java.awt.*;

public class MonoTower extends Tower {
    public MonoTower() {
        super(20, Assets.enemies.get("wizard"));
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
        fireRangeUpgrades.add(new FloatUpgrade(10,2.5f));
        fireRangeUpgrades.add(new FloatUpgrade(25,3f));
        fireRangeUpgrades.add(new FloatUpgrade(40,3.5f));
        fireRangeUpgrades.add(new FloatUpgrade(70,4f));
        fireRangeUpgrades.add(new FloatUpgrade(120,4.5f));
        fireRangeUpgrades.add(new FloatUpgrade(190,5f));
        fireRangeUpgrades.add(new FloatUpgrade(310,5.5f));
        fireRangeUpgrades.add(new FloatUpgrade(500,6f));

        setDamage(1);
        damageUpgrades.add(new IntUpgrade(10, 2));
        damageUpgrades.add(new IntUpgrade(25, 3));
        damageUpgrades.add(new IntUpgrade(40, 4));
        damageUpgrades.add(new IntUpgrade(70, 5));
        damageUpgrades.add(new IntUpgrade(120, 6));
        damageUpgrades.add(new IntUpgrade(190, 7));
        damageUpgrades.add(new IntUpgrade(310, 8));
        damageUpgrades.add(new IntUpgrade(500, 9));
    }

    @Override
    public boolean canBePlaced(GameState gameState) {
        Point position = getPosition();
        Map map = gameState.getMap();
        Layer layer = map.getLayer((float) position.getX(), (float) position.getY());
        Tile tile = layer.getTile((float) position.getX(), (float) position.getY());
        return tile.isFortress();
    }
}

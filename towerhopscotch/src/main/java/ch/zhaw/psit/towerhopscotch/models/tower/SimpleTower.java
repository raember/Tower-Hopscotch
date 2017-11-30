package ch.zhaw.psit.towerhopscotch.models.tower;

import java.awt.*;

/**
 * Created by Raphael Emberger on 28.11.2017.
 */
public class SimpleTower extends Tower {
    public SimpleTower() {
        super(20, Color.BLUE);
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
}

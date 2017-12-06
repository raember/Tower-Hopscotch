package ch.zhaw.psit.towerhopscotch.models.tower;

import ch.zhaw.psit.towerhopscotch.GUI.Assets;

/**
 * Created by Raphael Emberger on 03.12.2017.
 */
public class DoubleTower extends Tower {

    public DoubleTower() {
        super(20, Assets.towers.get("doubleTower"));
        setFireFrequency(200000000);
        fireFrequencyUpgrades.add(new LongUpgrade(0, 200000000));
        fireFrequencyUpgrades.add(new LongUpgrade(0, 200000000));
        fireFrequencyUpgrades.add(new LongUpgrade(0, 200000000));
        fireFrequencyUpgrades.add(new LongUpgrade(0, 200000000));
        fireFrequencyUpgrades.add(new LongUpgrade(0, 200000000));
        fireFrequencyUpgrades.add(new LongUpgrade(0, 200000000));
        fireFrequencyUpgrades.add(new LongUpgrade(0, 200000000));
        fireFrequencyUpgrades.add(new LongUpgrade(0, 200000000));

        setFireRange(2f);
        fireRangeUpgrades.add(new FloatUpgrade(0, 2f));
        fireRangeUpgrades.add(new FloatUpgrade(0, 2f));
        fireRangeUpgrades.add(new FloatUpgrade(0, 2.5f));
        fireRangeUpgrades.add(new FloatUpgrade(0, 2.5f));
        fireRangeUpgrades.add(new FloatUpgrade(0, 3.5f));
        fireRangeUpgrades.add(new FloatUpgrade(0, 4f));
        fireRangeUpgrades.add(new FloatUpgrade(0, 4.5f));
        fireRangeUpgrades.add(new FloatUpgrade(0, 5f));

        setDamage(5);
        damageUpgrades.add(new IntUpgrade(80, 8));
        damageUpgrades.add(new IntUpgrade(160, 12));
        damageUpgrades.add(new IntUpgrade(240, 15));
        damageUpgrades.add(new IntUpgrade(280, 18));
        damageUpgrades.add(new IntUpgrade(320, 21));
        damageUpgrades.add(new IntUpgrade(360, 24));
        damageUpgrades.add(new IntUpgrade(400, 27));
        damageUpgrades.add(new IntUpgrade(440, 30));
    }

}
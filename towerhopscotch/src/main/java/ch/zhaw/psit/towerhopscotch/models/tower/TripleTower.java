package ch.zhaw.psit.towerhopscotch.models.tower;

import ch.zhaw.psit.towerhopscotch.GUI.Assets;

/**
 * Represents a TripleTower which derives from tower
 * @author Raphael Emberger
 */
public class TripleTower extends Tower {
    public TripleTower() {
        super(20, Assets.towers.get("tripleTower"));
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
        damageUpgrades.add(new IntUpgrade(120, 8));
        damageUpgrades.add(new IntUpgrade(240, 12));
        damageUpgrades.add(new IntUpgrade(360, 15));
        damageUpgrades.add(new IntUpgrade(420, 18));
        damageUpgrades.add(new IntUpgrade(480, 21));
        damageUpgrades.add(new IntUpgrade(540, 24));
        damageUpgrades.add(new IntUpgrade(600, 27));
        damageUpgrades.add(new IntUpgrade(660, 30));
    }

}

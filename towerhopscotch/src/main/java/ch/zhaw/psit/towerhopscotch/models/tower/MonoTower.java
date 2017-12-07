package ch.zhaw.psit.towerhopscotch.models.tower;

import ch.zhaw.psit.towerhopscotch.GUI.Assets;

/**
 * Represents a MonoTower which derives from tower
 * @author Raphael Emberger
 */
public class MonoTower extends Tower {
    public MonoTower() {
        super(20, Assets.towers.get("monoTower"));
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
        damageUpgrades.add(new IntUpgrade(40, 8));
        damageUpgrades.add(new IntUpgrade(80, 12));
        damageUpgrades.add(new IntUpgrade(120, 15));
        damageUpgrades.add(new IntUpgrade(140, 18));
        damageUpgrades.add(new IntUpgrade(160, 21));
        damageUpgrades.add(new IntUpgrade(180, 24));
        damageUpgrades.add(new IntUpgrade(200, 27));
        damageUpgrades.add(new IntUpgrade(220, 30));
    }


}

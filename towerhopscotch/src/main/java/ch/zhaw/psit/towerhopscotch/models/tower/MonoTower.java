package ch.zhaw.psit.towerhopscotch.models.tower;

import ch.zhaw.psit.towerhopscotch.GUI.Assets;

public class MonoTower extends Tower {
    public MonoTower() {
        super(20, Assets.towers.get("monoTower"));
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


}

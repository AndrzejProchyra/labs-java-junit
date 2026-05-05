package StarTrek.WeaponSystems;

import StarTrek.Galaxy;
import StarTrek.Klingon;

import java.util.Random;

public class Phasers extends WeaponSystem {
    private static final int PHASER_RANGE = 4000;

    private int energyRemaining = 10000;

    public Phasers(Random random) {
        super(random);
    }

    @Override
    public void fireAt(Klingon enemy1, Galaxy wg1) {
        int amount = Integer.parseInt(wg1.parameter("amount"));
        if (energyRemaining() >= amount) {
            int distance = enemy1.distance();
            if (distance > PHASER_RANGE) {
                wg1.writeLine("Klingon out of range of phasers at " + distance + " sectors...");
            } else {
                int damage = amount - (((amount / 20) * distance / 200) + rnd(200));
                if (damage < 1)
                    damage = 1;
                wg1.writeLine("Phasers hit Klingon at " + distance + " sectors with " + damage + " units");
                if (damage < enemy1.getEnergy()) {
                    enemy1.setEnergy(enemy1.getEnergy() - damage);
                    wg1.writeLine("Klingon has " + enemy1.getEnergy() + " remaining");
                } else {
                    wg1.writeLine("Klingon destroyed!");
                    enemy1.delete();
                }
            }
            decrementEnergyBy(amount);
        } else {
            wg1.writeLine("Insufficient energy to fire phasers!");
        }
    }

    public int energyRemaining() {
        return energyRemaining;
    }

    private void decrementEnergyBy(int amount) {
        energyRemaining -= amount;
    }
}

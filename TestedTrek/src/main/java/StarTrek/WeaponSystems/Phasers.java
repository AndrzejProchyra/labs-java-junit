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

    protected int getAmount(Galaxy wg1) {
        return Integer.parseInt(wg1.parameter("amount"));
    }

    protected boolean hasAmmo(int amount) {
        return energyRemaining() >= amount;
    }

    protected String outOfAmmoMessage() {
        return "Insufficient energy to fire phasers!";
    }

    protected String hitMessage(Klingon enemy1, int damage) {
        return "Phasers hit Klingon at " + enemy1.distance() + " sectors with " + damage + " units";
    }

    protected int calculateDamage(Klingon enemy1, int amount) {
        int damage = amount - (((amount / 20) * enemy1.distance() / 200) + rnd(200));
        if (damage < 1)
            damage = 1;
        return damage;
    }

    protected String missedMessage(Klingon enemy) {
        int distance = enemy.distance();
        return "Klingon out of range of phasers at " + distance + " sectors...";
    }

    protected boolean misses(Klingon enemy) {
        int distance = enemy.distance();
        return distance > PHASER_RANGE;
    }

    public int energyRemaining() {
        return energyRemaining;
    }

    protected void decrementAmmo(int amount) {
        energyRemaining -= amount;
    }
}

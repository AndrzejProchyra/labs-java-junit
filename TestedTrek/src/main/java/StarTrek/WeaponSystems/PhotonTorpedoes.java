package StarTrek.WeaponSystems;

import StarTrek.Galaxy;
import StarTrek.Klingon;

import java.util.Random;

public class PhotonTorpedoes extends WeaponSystem {
    private int torpedoesRemaining = 8;

    public PhotonTorpedoes(Random random) {
        super(random);
    }

    protected int getAmount(Galaxy wg1) {
        return 1;
    }

    protected boolean hasAmmo(int amount) {
        return getTorpedoes() >= amount;
    }

    protected String outOfAmmoMessage() {
        return "No more photon torpedoes!";
    }

    protected String hitMessage(Klingon enemy1, int damage) {
        return "Photons hit Klingon at " + enemy1.distance() + " sectors with " + damage + " units";
    }

    protected int calculateDamage(Klingon unusedEnemy, int unusedAmount) {
        int damage = 800 + rnd(50);
        return damage;
    }

    protected String missedMessage(Klingon enemy) {
        int distance = enemy.distance();
        return "Torpedo missed Klingon at " + distance + " sectors...";
    }

    protected boolean misses(Klingon enemy) {
        int distance = enemy.distance();
        return rnd(4) + ((distance / 500) + 1) > 7;
    }

    public int getTorpedoes() {
        return torpedoesRemaining;

    }

    protected void decrementAmmo(int amount) {
        torpedoesRemaining -= amount;
    }

    public void setTorpedoes(int value) {
        torpedoesRemaining = value;
    }
}

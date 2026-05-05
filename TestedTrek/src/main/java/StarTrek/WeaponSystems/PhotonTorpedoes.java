package StarTrek.WeaponSystems;

import StarTrek.Galaxy;
import StarTrek.Klingon;

import java.util.Random;

public class PhotonTorpedoes extends WeaponSystem {
    private int torpedoesRemaining = 8;

    public PhotonTorpedoes(Random random) {
        super(random);
    }

    @Override
    public void fireAt(Klingon enemy1, Galaxy wg1) {
        if (getTorpedoes() > 0) {
            int distance = enemy1.distance();
            if ((rnd(4) + ((distance / 500) + 1) > 7)) {
                wg1.writeLine("Torpedo missed Klingon at " + distance + " sectors...");
            } else {
                int damage = 800 + rnd(50);
                wg1.writeLine("Photons hit Klingon at " + distance + " sectors with " + damage + " units");
                if (damage < enemy1.getEnergy()) {
                    enemy1.setEnergy(enemy1.getEnergy() - damage);
                    wg1.writeLine("Klingon has " + enemy1.getEnergy() + " remaining");
                } else {
                    wg1.writeLine("Klingon destroyed!");
                    enemy1.delete();
                }
            }
            decrementTorpedoesByOne();
        } else {
            wg1.writeLine("No more photon torpedoes!");
        }
    }

    public int getTorpedoes() {
        return torpedoesRemaining;

    }

    private void decrementTorpedoesByOne() {
        torpedoesRemaining--;
    }

    public void setTorpedoes(int value) {
        torpedoesRemaining = value;
    }
}

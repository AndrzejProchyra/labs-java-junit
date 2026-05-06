package StarTrek.WeaponSystems;

import StarTrek.Galaxy;
import StarTrek.Klingon;

import java.util.Random;

public abstract class WeaponSystem {
    protected final Random random;

    public WeaponSystem(Random random) {
        this.random = random;
    }

    protected int rnd(int maximum) {
        return random.nextInt(maximum);
    }

    public void fireAt(Klingon enemy1, Galaxy wg1) {
        final int amount = getAmount(wg1);
        if (hasAmmo(amount)) {
            if (misses(enemy1)) {
                wg1.writeLine(missedMessage(enemy1));
            } else {
                final int damage = calculateDamage(enemy1, amount);
                wg1.writeLine(hitMessage(enemy1, damage));
                if (damage < enemy1.getEnergy()) {
                    enemy1.setEnergy(enemy1.getEnergy() - damage);
                    wg1.writeLine("Klingon has " + enemy1.getEnergy() + " remaining");
                } else {
                    wg1.writeLine("Klingon destroyed!");
                    enemy1.delete();
                }
            }
            decrementAmmo(amount);
        } else {
            wg1.writeLine(outOfAmmoMessage());
        }
    }

    protected abstract String outOfAmmoMessage();

    protected abstract void decrementAmmo(int amount);

    protected abstract String hitMessage(Klingon enemy1, int damage);

    protected abstract int calculateDamage(Klingon enemy1, int amount);

    protected abstract String missedMessage(Klingon enemy1);

    protected abstract boolean misses(Klingon enemy1);

    protected abstract boolean hasAmmo(int amount);

    protected abstract int getAmount(Galaxy wg1);

}

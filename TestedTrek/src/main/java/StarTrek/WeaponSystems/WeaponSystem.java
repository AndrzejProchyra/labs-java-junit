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

    public abstract void fireAt(Klingon enemy1, Galaxy wg1);

}

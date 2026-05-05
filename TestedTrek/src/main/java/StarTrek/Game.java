package StarTrek;

import StarTrek.WeaponSystems.Phasers;
import StarTrek.WeaponSystems.PhotonTorpedoes;
import Untouchables.WebGadget;

import java.util.Random;

public class Game {

    private final Phasers phasers;
    private final PhotonTorpedoes photonTorpedoes;

    public Game(Random random) {
        phasers = new Phasers(random);
        photonTorpedoes = new PhotonTorpedoes(random);
    }


    public int energyRemaining() {
        return phasers.energyRemaining();
    }

    public void setTorpedoes(int value) {
        photonTorpedoes.setTorpedoes(value);
    }

    public int getTorpedoes() {
        return photonTorpedoes.getTorpedoes();
    }

    public void fireWeapon(WebGadget wg) {
        fireWeapon(new Galaxy(wg));
    }

    public void fireWeapon(Galaxy wg) {
        String weapon = wg.parameter("command");
        Klingon enemy = (Klingon) wg.variable("target");
        if (weapon.equals("phaser")) {
            phasers.fireAt(enemy, wg);
        } else if (weapon.equals("photon")) {
            photonTorpedoes.fireAt(enemy, wg);
        }
    }

}

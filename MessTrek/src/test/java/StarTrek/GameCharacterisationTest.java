package StarTrek;

import Untouchables.WebGadget;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class GameCharacterisationTest {

    @BeforeEach
    void setUp() {
        Game.generator = new Random(0);
    }

    @Test
    void damageKlingonWithPhaser() {
        Game game = new Game();
        Enemy targetVariable = new Klingon(1000, 2000);

        final WebGadgetProxy spy = spy(getWebGadget("phaser", 1000, targetVariable));
        game.fireWeapon(spy);

        assertThat(targetVariable.getEnergy()).isEqualTo(1410);
        verify(spy).writeLine("Phasers hit Klingon at 1000 sectors with 590 units");
        verify(spy).writeLine("Klingon has 1410 remaining");
        verify(spy, times(2)).writeLine(anyString());
        assertThat(game.getPhaserEnergy()).isEqualTo(9000);
    }

    @Test
    void klingonOutOfRangeOfPhaser() {
        Game game = new Game();
        Enemy targetVariable = new Klingon(4001, 2000);

        final WebGadgetProxy spy = spy(getWebGadget("phaser", 1000, targetVariable));
        game.fireWeapon(spy);

        assertThat(targetVariable.getEnergy()).isEqualTo(2000);
        verify(spy).writeLine("Klingon out of range of phasers at 4001 sectors...");
        verify(spy, times(1)).writeLine(anyString());
        assertThat(game.getPhaserEnergy()).isEqualTo(9000);
    }

    private static WebGadgetProxy getWebGadget(String weapon, int amount, Enemy enemy) {
        return new WebGadgetProxy(new WebGadget(weapon, "" + amount, enemy));
    }

    @Test
    void klingonDestroyedByPhasers() {
        Game game = new Game();
        Enemy weakKlingon = new Klingon(100, 10);

        final WebGadgetProxy spy = spy(getWebGadget("phaser", 1000, weakKlingon));
        game.fireWeapon(spy);

        assertThat(weakKlingon.getEnergy()).isEqualTo(10);
        verify(spy).writeLine("Phasers hit Klingon at 100 sectors with 815 units");
        verify(spy).writeLine("Klingon destroyed!");
        verify(spy, times(2)).writeLine(anyString());
        assertThat(game.getPhaserEnergy()).isEqualTo(9000);
    }
}

package com.agileinstitute;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static com.agileinstitute.Orientation.HORIZONTAL;
import static com.agileinstitute.Orientation.VERTICAL;
import static com.agileinstitute.ShipType.CRUISER;
import static com.agileinstitute.ShipType.DESTROYER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ShipTests {

    @Test
    void horizontalCruiserAtA3ShouldOccupyCoordinatesA3ToC3() {
        // given
        final Ship ship = new Ship(CRUISER, "a3", HORIZONTAL);
        // when
        final Set<String> coordinates = ship.getCoordinates();
        // then
        assertThat(coordinates).containsOnly("a3", "b3", "c3");
    }

    @Test
    void horizontalCruiserAtB4ShouldOccupyCoordinatesB4ToD4() {
        // given
        final Ship ship = new Ship(CRUISER, "b4", HORIZONTAL);
        // when
        final Set<String> coordinates = ship.getCoordinates();
        // then
        assertThat(coordinates).containsOnly("b4", "c4", "d4");
    }

    @Test
    void verticalCruiserAtB4ShouldOccupyCoordinatesB4ToB6() {
        // given
        final Ship ship = new Ship(CRUISER, "b4", VERTICAL);
        // when
        final Set<String> coordinates = ship.getCoordinates();
        // then
        assertThat(coordinates).containsOnly("b4", "b5", "b6");
    }

    @Test
    void verticalDestroyerAtB4ShouldOccupyCoordinatesB4ToB6() {
        // given
        final Ship ship = new Ship(DESTROYER, "c5", VERTICAL);
        // when
        final Set<String> coordinates = ship.getCoordinates();
        // then
        assertThat(coordinates).containsOnly("c5", "c6");
    }

    @Test
    void bowCoordinateOutsideOfTheBoardThrowsIllegalArgumentException() {
        assertThatThrownBy(() -> new Ship(DESTROYER, "m1", VERTICAL))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new Ship(DESTROYER, "a10", VERTICAL))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new Ship(DESTROYER, "aa", VERTICAL))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new Ship(DESTROYER, "00", VERTICAL))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new Ship(DESTROYER, "A0", VERTICAL))
                .isInstanceOf(IllegalArgumentException.class);
    }
}

package com.agileinstitute;


import java.util.Set;

public class Ship {
    private final ShipType shipType;
    private final Coordinate bowCoordinate;
    private final Orientation orientation;

    public Ship(ShipType shipType, String bowCoordinate, Orientation orientation) {
        this.shipType = shipType;
        this.bowCoordinate = new Coordinate(bowCoordinate);
        this.orientation = orientation;
    }

    public Set<String> getCoordinates() {
        return orientation.getCoordinates(bowCoordinate, shipType.getSize());
    }
}

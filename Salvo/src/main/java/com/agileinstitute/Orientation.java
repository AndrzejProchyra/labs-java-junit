package com.agileinstitute;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public enum Orientation {
    HORIZONTAL(0, 1),
    VERTICAL(1, 0);

    private final int yOffset;
    private final int xOffset;

    Orientation(int yOffset, int xOffset) {
        this.yOffset = yOffset;
        this.xOffset = xOffset;
    }

    public Set<String> getCoordinates(Coordinate bowCoordinate, int size) {
        return IntStream.range(0, size)
                .mapToObj(i -> addToCoordinate(bowCoordinate, i))
                .collect(Collectors.toSet());
    }

    private String addToCoordinate(Coordinate bowCoordinate, int i) {
        final byte[] bytes = bowCoordinate.getBytes();
        return new String(new byte[]{
                (byte) (bytes[0] + (xOffset * i)),
                (byte) (bytes[1] + (yOffset * i))
        });
    }

}

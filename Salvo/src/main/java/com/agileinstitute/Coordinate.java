package com.agileinstitute;


public record Coordinate(String xy) {

    public Coordinate(String xy) {
        this.xy = isValidCoordinate(xy);
    }

    private String isValidCoordinate(String coordinates) {
        final char x = coordinates.charAt(0);
        final char y = coordinates.charAt(1);

        if (x > 'l' ||
                coordinates.length() != 2 ||
                !Character.isDigit(y) ||
                !Character.isLetter(x) ||
                !Character.isLowerCase(x)) {
            throw new IllegalArgumentException();
        }

        return coordinates;
    }
}

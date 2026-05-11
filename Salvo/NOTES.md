# Model

## Ship

Has a class (Carrier, Battleship, Cruiser, Destroyer, Submarine) - a class has a size
Knows if it is sunk
Knows where it has been hit
Knows its coordinates

## Game

Receives coordinates of a salvo
Places ships - enforces placement rules
Knows which ships are in play
Knows if the game is over

# Test list

Place ship and ask its coordinates
- new Ship(CRUISER, "a3", HORIZONTAL) -> ship.coordinates = "a3", "b3", "c3"
- new Ship(DESTROYER, "a3", VERTICAL) -> ship.coordinates = "a3", "a4"

Placement coordinates must be 0 - 9 and A - L
- throws IllegalArgumentException

Placed ships must not overlap edge of the board
- place ship -> ship.onTheBoard()
- place ship -> ship.touchesAny(:List<Ship>)
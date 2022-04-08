package utils;

import model.coordinates.Coordinates;
import model.map.Map;
import model.square.Square;
import model.square.SquareType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapUtilsTest {
    private Map map;

    @BeforeEach
    void setUp() {
        map = new Map(new Coordinates(4,4));
    }

    @Test
    void isCoordinatesInMapExpectOk() {
        assertTrue(MapUtils.isCoordinatesInMap(map, new Coordinates(2,2)));
    }

    @Test
    void isCoordinatesInMapExpectKo() {
        assertFalse(MapUtils.isCoordinatesInMap(map, new Coordinates(5,2)));
    }

    @Test
    void isCoordinatesInMapNegativeCoordExpectKo() {
        assertFalse(MapUtils.isCoordinatesInMap(map, new Coordinates(-1,-2)));
    }

    @Test
    void isSquareValidToMovePlainExpectOk() {
        map.addSquare(new Coordinates(2, 2), new Square(SquareType.PLAIN));
        assertTrue(MapUtils.isSquareValidToMove(map,new Coordinates(2,2)));
    }

    @Test
    void isSquareValidToMoveTreasureExpectOk() {
        map.addSquare(new Coordinates(1, 1), new Square(SquareType.TREASURE));
        assertTrue(MapUtils.isSquareValidToMove(map,new Coordinates(2,2)));
    }

    @Test
    void isTreasureSquare() {
    }

    @Test
    void removeTreasure() {
    }

    @Test
    void isAllAdventurersPlayed() {
    }
}
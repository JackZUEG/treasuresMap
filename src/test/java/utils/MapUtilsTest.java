package utils;

import exception.IncorrectDirectionException;
import exception.IncorrectMovementException;
import exception.SquareIsMountainOrTakenException;
import model.adventurer.Adventurer;
import model.coordinates.Coordinates;
import model.map.Map;
import model.square.Square;
import model.square.SquareTreasure;
import model.square.SquareType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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
        map.addSquare(new Coordinates(2, 2), new Square(SquareType.TREASURE));
        assertTrue(MapUtils.isSquareValidToMove(map,new Coordinates(2,2)));
    }

    @Test
    void isSquareValidToMoveMountainExpectKo() {
        map.addSquare(new Coordinates(2, 2), new Square(SquareType.MOUNTAIN));
        assertFalse(MapUtils.isSquareValidToMove(map,new Coordinates(2,2)));
    }

    @Test
    void isSquareValidToMoveTakenExpectKo() {
        Square square = new Square(SquareType.MOUNTAIN);
        square.setTaken(true);
        map.addSquare(new Coordinates(2, 2), square);
        assertFalse(MapUtils.isSquareValidToMove(map,new Coordinates(2,2)));
    }

    @Test
    void isTreasureSquareTreasureExpectOk() {
        SquareTreasure square = new SquareTreasure(3);
        map.addSquare(new Coordinates(2, 2), square);
        assertTrue(MapUtils.isTreasureSquare(map,new Coordinates(2,2)));
    }

    @Test
    void isTreasureSquarePlainExpectKo() {
        Square square = new Square(SquareType.PLAIN);
        map.addSquare(new Coordinates(2, 2), square);
        assertFalse(MapUtils.isTreasureSquare(map,new Coordinates(2,2)));
    }

    @Test
    void removeTreasureExpectOk() {
        SquareTreasure square = new SquareTreasure(3);
        map.addSquare(new Coordinates(2, 2), square);
        MapUtils.removeTreasure(map, new Coordinates(2,2));
        SquareTreasure findedSquare = (SquareTreasure) map.getSquare(new Coordinates(2,2));
        assertEquals(findedSquare.getCountTreasures(), 2);
    }

    @Test
    void removeTreasureNoTreasureExpectOk() {
        SquareTreasure square = new SquareTreasure(0);
        map.addSquare(new Coordinates(2, 2), square);
        MapUtils.removeTreasure(map, new Coordinates(2,2));
        SquareTreasure findedSquare = (SquareTreasure) map.getSquare(new Coordinates(2,2));
        assertEquals(findedSquare.getCountTreasures(), 0);
    }

    @Test
    void isAllAdventurersPlayedExpectOk() {
        try{
            Adventurer adventurer1 = new Adventurer("John", new Coordinates(1,1),"S", "");
            Adventurer adventurer2 = new Adventurer("Joe", new Coordinates(2,2),"S", "");
            map.addAdventurer(adventurer1);
            map.addAdventurer(adventurer2);
            assertTrue(MapUtils.isAllAdventurersPlayed(map));
        } catch(IncorrectDirectionException | IncorrectMovementException | SquareIsMountainOrTakenException e){
            fail(e.getCause());
        }
    }

    @Test
    void isAllAdventurersPlayedExpectKo() {
        try{
            Adventurer adventurer1 = new Adventurer("John", new Coordinates(1,1),"S", "AG");
            Adventurer adventurer2 = new Adventurer("Joe", new Coordinates(2,2),"S", "");
            map.addAdventurer(adventurer1);
            map.addAdventurer(adventurer2);
            assertFalse(MapUtils.isAllAdventurersPlayed(map));
        } catch(IncorrectDirectionException | IncorrectMovementException | SquareIsMountainOrTakenException e){
            fail(e.getCause());
        }
    }
}
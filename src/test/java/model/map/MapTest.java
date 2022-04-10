package model.map;

import exception.IncorrectDirectionException;
import exception.IncorrectMovementException;
import exception.MessagesException;
import exception.SquareIsMountainOrTakenException;
import model.adventurer.Adventurer;
import model.coordinates.Coordinates;
import model.square.Square;
import model.square.SquareType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapTest {
    private Map map;

    @BeforeEach
    void setUp() {
        this.map = new Map(new Coordinates(3,3));
    }

    @Test
    void addAdventurerValidSquareExpectOk() {
        try{
            Adventurer adventurer1 = new Adventurer("John", new Coordinates(1,1),"S", "");
            map.addAdventurer(adventurer1);
            assertTrue(map.getListAdventurers().contains(adventurer1));
            assertTrue(map.getSquare(new Coordinates(1,1)).isTaken());
        } catch (IncorrectDirectionException | IncorrectMovementException | SquareIsMountainOrTakenException e){
            fail(e.getCause());
        }
    }

    @Test
    void addAdventurerInvalidSquareExpectSquareIsMountainOrTakenException() {
        try{
            Adventurer adventurer1 = new Adventurer("John", new Coordinates(1,1),"S", "");
            Adventurer adventurer2 = new Adventurer("Louis", new Coordinates(1,1),"S", "");

            SquareIsMountainOrTakenException exception = assertThrows(SquareIsMountainOrTakenException.class, () -> {
                map.addAdventurer(adventurer1);
                map.addAdventurer(adventurer2);
            });
            String expectedMessage = MessagesException.SquareIsMountainOrTakenException.getMsg();
            String actualMessage = exception.getMessage();
            assertTrue(actualMessage.contains(expectedMessage));

        } catch (IncorrectMovementException | IncorrectDirectionException e){
            fail(e.getCause());
        }
    }

    @Test
    void setPlainMapExpectOk() {
        map.setPlainMap();
        for(int x = 0; x < map.getCoordinates().getCoordinateX(); x++){
            for(int y = 0; y < map.getCoordinates().getCoordinateY(); y++){
                assertEquals(map.getSquares()[x][y].getSquareType(), SquareType.PLAIN);
            }
        }
    }

    @Test
    void addSquareExpectOk() {
        Coordinates coordinates = new Coordinates(1,1);
        Square square = new Square(SquareType.MOUNTAIN);
        map.addSquare(coordinates, square);
        assertEquals(map.getSquare(coordinates), square);
    }

    @Test
    void getSquareValidCoordinatesExpectOK() {
        Coordinates coordinates = new Coordinates(1,1);
        Square square = new Square(SquareType.PLAIN);
        map.addSquare(coordinates, square);
        assertEquals(map.getSquare(coordinates), square);
    }

    @Test
    void getSquareInvalidCoordinatesExpectKo() {
        Coordinates coordinates = new Coordinates(-1,1);
        assertEquals(map.getSquare(coordinates), null);
    }
}
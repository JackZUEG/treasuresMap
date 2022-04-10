package commons;

import exception.InvalidInputFileException;
import exception.NoDimensionsMapFound;
import model.adventurer.Adventurer;
import model.adventurer.Direction;
import model.adventurer.Movement;
import model.coordinates.Coordinates;
import model.map.Map;
import model.square.Square;
import model.square.SquareTreasure;
import model.square.SquareType;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MapBuilderTest {

    @Test
    void readMapValidFileExpectOk() {
        try{
            String fileName = "src\\test\\resources\\input\\mapTestReadMapOk";
            Map map = MapBuilder.readMap(fileName);

            //Verification des dimensions de la carte
            assertEquals(map.getCoordinates(), new Coordinates(3,3));

            //Verification de la creation des cases correcte
            assertEquals(map.getSquare(new Coordinates(0,0)).getSquareType(), SquareType.PLAIN);
            assertTrue(map.getSquare(new Coordinates(0, 0)).isTaken());
            assertEquals(map.getSquare(new Coordinates(0,1)).getSquareType(), SquareType.PLAIN);
            assertFalse(map.getSquare(new Coordinates(0, 1)).isTaken());
            assertEquals(map.getSquare(new Coordinates(0,2)).getSquareType(), SquareType.TREASURE);
            assertFalse(map.getSquare(new Coordinates(0, 2)).isTaken());
            assertEquals(map.getSquare(new Coordinates(1,0)).getSquareType(), SquareType.MOUNTAIN);
            assertFalse(map.getSquare(new Coordinates(1, 0)).isTaken());
            assertEquals(map.getSquare(new Coordinates(1,1)).getSquareType(), SquareType.PLAIN);
            assertTrue(map.getSquare(new Coordinates(1, 1)).isTaken());
            assertEquals(map.getSquare(new Coordinates(1,2)).getSquareType(), SquareType.TREASURE);
            assertFalse(map.getSquare(new Coordinates(1, 2)).isTaken());
            assertEquals(map.getSquare(new Coordinates(2,0)).getSquareType(), SquareType.PLAIN);
            assertFalse(map.getSquare(new Coordinates(2, 0)).isTaken());
            assertEquals(map.getSquare(new Coordinates(2,1)).getSquareType(), SquareType.MOUNTAIN);
            assertFalse(map.getSquare(new Coordinates(2, 1)).isTaken());
            assertEquals(map.getSquare(new Coordinates(2,2)).getSquareType(), SquareType.PLAIN);
            assertFalse(map.getSquare(new Coordinates(2, 2)).isTaken());

            //Verification de la creation des aventuriers correcte
            Adventurer adventurer1 = map.getListAdventurers().get(0);
            Adventurer adventurer2 = map.getListAdventurers().get(1);

            assertEquals(map.getListAdventurers().size(), 2);
            assertEquals(adventurer1.getName(), "Lara");
            assertEquals(adventurer1.getDirection(), Direction.SOUTH);
            assertEquals(adventurer1.getCoordinates(), new Coordinates(1,1));
            assertEquals(adventurer1.getRecoveredTreasures(), 0);
            assertEquals(adventurer1.getNextMovement(), Movement.FORWARD);

            assertEquals(adventurer2.getName(), "Joe");
            assertEquals(adventurer2.getDirection(), Direction.SOUTH);
            assertEquals(adventurer2.getCoordinates(), new Coordinates(0,0));
            assertEquals(adventurer2.getRecoveredTreasures(), 0);
            assertEquals(adventurer2.getNextMovement(), Movement.FORWARD);

        } catch(NoDimensionsMapFound | InvalidInputFileException e){
            fail(e.getCause());
        }
    }

}
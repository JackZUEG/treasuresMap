package model.adventurer;

import commons.LineParser;
import exception.IncorrectDirectionException;
import exception.IncorrectMovementException;
import exception.MessagesException;
import exception.NoDimensionsMapFound;
import model.coordinates.Coordinates;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.AdventurerUtils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AdventurerTest {

    private Adventurer adventurer;

    @BeforeEach
    void setUp() throws IncorrectDirectionException, IncorrectMovementException {
        adventurer = new Adventurer("John", new Coordinates(1,1), "S", "ADGA");
    }

    @Test
    void createDirectionValidExpectOk() throws IncorrectDirectionException {
       String direction = "S";

       assertTrue(Direction.SOUTH.equals(adventurer.createDirection(direction)));
    }
    @Test
    void createDirectionInvalidExpectIncorrectDirectionException(){
        String direction = "V";
        IncorrectDirectionException exception = assertThrows(IncorrectDirectionException.class, () -> {
            adventurer.createDirection(direction);
        });
        String expectedMessage = MessagesException.IncorrectDirectionException.getMsg();
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void createMovementsOk() throws IncorrectMovementException {
        List<Movement> listMovements = new ArrayList<>();
        listMovements.add(Movement.FORWARD);
        listMovements.add(Movement.TURNLEFT);
        listMovements.add(Movement.TURNRIGHT);
        listMovements.add(Movement.FORWARD);

        List<Movement> listMovementsAdventurer = adventurer.createMovements("AGDA");
        assertEquals(listMovementsAdventurer.size(), listMovements.size());
        assertEquals(listMovementsAdventurer.get(0), listMovements.get(0));
        assertEquals(listMovementsAdventurer.get(1), listMovements.get(1));
        assertEquals(listMovementsAdventurer.get(2), listMovements.get(2));
        assertEquals(listMovementsAdventurer.get(3), listMovements.get(3));
    }

    @Test
    void createMovementsIncorrectMovementExpectOk() {
        List<Movement> listMovements = new ArrayList<>();
        listMovements.add(Movement.FORWARD);
        listMovements.add(Movement.TURNLEFT);
        listMovements.add(Movement.TURNRIGHT);
        listMovements.add(Movement.FORWARD);

        List<Movement> listMovementsAdventurer = adventurer.createMovements("AGDAVT");
        assertEquals(listMovementsAdventurer.size(), listMovements.size());
        assertEquals(listMovementsAdventurer.get(0), listMovements.get(0));
        assertEquals(listMovementsAdventurer.get(1), listMovements.get(1));
        assertEquals(listMovementsAdventurer.get(2), listMovements.get(2));
        assertEquals(listMovementsAdventurer.get(3), listMovements.get(3));
    }

    @Test
    void turnDirectionSouthToEastOk() {
        adventurer.turnDirection(Movement.TURNLEFT);
        assertTrue(adventurer.getDirection().equals(Direction.EAST));
    }

    @Test
    void turnDirectionNorthToWestOk() {
        adventurer.setDirection(Direction.NORTH);
        adventurer.turnDirection(Movement.TURNRIGHT);
        assertTrue(adventurer.getDirection().equals(Direction.EAST));
    }

    @Test
    void isOutOfMovementsEmptyListOk() {
        adventurer.setNextMovements(new ArrayList<Movement>());
        assertEquals(adventurer.isOutOfMovements(), true);
    }

    @Test
    void isOutOfMovementsOk() {
        assertEquals(adventurer.isOutOfMovements(), false);
    }


}
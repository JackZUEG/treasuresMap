package utils;

import exception.IncorrectDirectionException;
import exception.IncorrectMovementException;
import exception.MessagesException;
import model.adventurer.Adventurer;
import model.adventurer.Direction;
import model.adventurer.Movement;
import model.coordinates.Coordinates;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdventurerUtilsTest {

    @Test
    void getMovementExpectOk() {
        try{
            assertEquals(AdventurerUtils.getMovement('A'), Movement.FORWARD);
            assertEquals(AdventurerUtils.getMovement('G'), Movement.TURNLEFT);
            assertEquals(AdventurerUtils.getMovement('D'), Movement.TURNRIGHT);
        } catch (IncorrectMovementException e){
            fail(e.getCause());
        }
    }

    @Test
    void getMovementExpectIncorrectMovementException() {
        IncorrectMovementException exception = assertThrows(IncorrectMovementException.class, () -> {
            AdventurerUtils.getMovement('V');
        });
        String expectedMessage = MessagesException.IncorrectMovementException.getMsg();
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void getDirectionExpectOk(){
        try{
            assertEquals(AdventurerUtils.getDirection("N"), Direction.NORTH);
            assertEquals(AdventurerUtils.getDirection("S"), Direction.SOUTH);
            assertEquals(AdventurerUtils.getDirection("O"), Direction.WEST);
            assertEquals(AdventurerUtils.getDirection("E"), Direction.EAST);
        } catch (IncorrectDirectionException e){
            fail(e.getCause());
        }
    }

    @Test
    void getDirectionExpectIncorrectDirectionException() {
        IncorrectDirectionException exception = assertThrows(IncorrectDirectionException.class, () -> {
            AdventurerUtils.getDirection("");
        });
        String expectedMessage = MessagesException.IncorrectDirectionException.getMsg();
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void getCoordinatesAfterMovementExpectOk() {
        try{
            Adventurer adventurer1 = new Adventurer("John", new Coordinates(1,1),"S", "A");
            Coordinates coordinates = AdventurerUtils.getCoordinatesAfterMovement(adventurer1);
            assertEquals(coordinates, new Coordinates(1,2));
        } catch (IncorrectDirectionException | IncorrectMovementException e) {
            fail(e.getCause());
        }
    }
}
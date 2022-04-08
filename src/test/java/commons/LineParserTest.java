package commons;

import exception.MessagesException;
import exception.NoDimensionsMapFound;
import model.coordinates.Coordinates;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LineParserTest {

    @Test
    void getMapDimensionsEmptyExpectNoDimensionsMapFound() {
        List<String> lines = new ArrayList<>();

        NoDimensionsMapFound exception = assertThrows(NoDimensionsMapFound.class, () -> {
            LineParser.getMapDimensions(lines);
        });
        String expectedMessage = MessagesException.NoDimensionsMapFound.getMsg();
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void getMapDimensionsInvalidExpectNoDimensionsMapFound() {
        List<String> lines = new ArrayList<>();
        lines.add("C - 3");
        NoDimensionsMapFound exception = assertThrows(NoDimensionsMapFound.class, () -> {
            LineParser.getMapDimensions(lines);
        });
        String expectedMessage = MessagesException.NoDimensionsMapFound.getMsg();
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void getMapDimensionsZeroExpectNoDimensionsMapFound() {
        List<String> lines = new ArrayList<>();
        lines.add("C - 0 - 0");
        NoDimensionsMapFound exception = assertThrows(NoDimensionsMapFound.class, () -> {
            LineParser.getMapDimensions(lines);
        });
        String expectedMessage = MessagesException.NoDimensionsMapFound.getMsg();
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void getMapDimensionsValidExpectCoordinates() throws NoDimensionsMapFound {
        List<String> lines = new ArrayList<>();
        lines.add("C - 2 - 5");
        Coordinates coordinates = new Coordinates(2,5);

        assertTrue(coordinates.equals(LineParser.getMapDimensions(lines)));
    }
}
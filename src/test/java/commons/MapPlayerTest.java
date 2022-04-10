package commons;

import exception.InvalidInputFileException;
import exception.NoDimensionsMapFound;
import model.adventurer.Direction;
import model.coordinates.Coordinates;
import model.map.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.MapUtils;

import static org.junit.jupiter.api.Assertions.*;

class MapPlayerTest {
    private Map map;
    private Map bigMap;

    @BeforeEach
    void setUp() {
        try {
            this.map = MapBuilder.readMap("src\\test\\resources\\input\\mapPlayerTestPlayMapOk");
            this.bigMap = MapBuilder.readMap("src\\test\\resources\\input\\mapPlayerTestPlayBigMapOk");
        } catch (NoDimensionsMapFound | InvalidInputFileException e) {
            fail(e.getCause());
        }
    }

    @Test
    void playTreasureMapExpectOk() {
        try{
            MapPlayer.playTreasureMap(map);

            //Verification du bon positionnement des aventuriers en fin de partie
            assertEquals(map.getListAdventurers().get(0).getRecoveredTreasures(), 2);
            assertEquals(map.getListAdventurers().get(0).getCoordinates(), new Coordinates(1,2));
            assertEquals(map.getListAdventurers().get(0).getDirection(), Direction.SOUTH);

            assertEquals(map.getListAdventurers().get(1).getRecoveredTreasures(), 2);
            assertEquals(map.getListAdventurers().get(1).getCoordinates(), new Coordinates(0,2));
            assertEquals(map.getListAdventurers().get(1).getDirection(), Direction.SOUTH);

            //On verifie que tous les aventuriers ont bien joue tous leurs mouvements
            assertTrue(MapUtils.isAllAdventurersPlayed(map));

        } catch(Exception e){
            fail(e.getCause());
        }
    }

    @Test
    void playTreasureMapBigMapExpectOk() {
        try{
            MapPlayer.playTreasureMap(bigMap);

            //Verification du bon positionnement des aventuriers en fin de partie
            assertEquals(bigMap.getListAdventurers().get(0).getRecoveredTreasures(), 0);
            assertEquals(bigMap.getListAdventurers().get(0).getCoordinates(), new Coordinates(4,1));
            assertEquals(bigMap.getListAdventurers().get(0).getDirection(), Direction.EAST);

            assertEquals(bigMap.getListAdventurers().get(1).getRecoveredTreasures(), 1);
            assertEquals(bigMap.getListAdventurers().get(1).getCoordinates(), new Coordinates(0,6));
            assertEquals(bigMap.getListAdventurers().get(1).getDirection(), Direction.SOUTH);

            assertEquals(bigMap.getListAdventurers().get(2).getRecoveredTreasures(), 1);
            assertEquals(bigMap.getListAdventurers().get(2).getCoordinates(), new Coordinates(0,4));
            assertEquals(bigMap.getListAdventurers().get(2).getDirection(), Direction.SOUTH);

            assertEquals(bigMap.getListAdventurers().get(3).getRecoveredTreasures(), 0);
            assertEquals(bigMap.getListAdventurers().get(3).getCoordinates(), new Coordinates(5,5));
            assertEquals(bigMap.getListAdventurers().get(3).getDirection(), Direction.SOUTH);

            //On verifie que tous les aventuriers ont bien joue tous leurs mouvements
            assertTrue(MapUtils.isAllAdventurersPlayed(bigMap));

        } catch(Exception e){
            fail(e.getCause());
        }
    }


}
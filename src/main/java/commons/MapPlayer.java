package commons;

import exception.ElementOutOfTheMapException;
import exception.IncorrectMovementException;
import model.adventurer.Adventurer;
import model.adventurer.Movement;
import model.coordinates.Coordinates;
import model.map.Map;
import utils.AdventurerUtils;
import utils.MapUtils;

import java.util.List;

public abstract class MapPlayer {


    public static void playTreasureMap(Map map) throws ElementOutOfTheMapException, IncorrectMovementException {

        List<Adventurer> listAdventurers = map.getListAdventurers();
        boolean continueToPlay = true;

        while(continueToPlay){
            for(Adventurer adventurer: listAdventurers){
                if(!adventurer.isOutOfMovements()){
                    playMovement(map, adventurer);
                }
            }
            if(MapUtils.isAllAdventurersPlayed(map)){
                continueToPlay = false;
            }
        }
    }

    public static void playMovement(Map map, Adventurer adventurer) throws ElementOutOfTheMapException, IncorrectMovementException {
        Movement movement = adventurer.getNextMovement();
        System.out.println(movement.getValue());
        if(movement.equals(Movement.FORWARD)){
            Coordinates coordAfterMovement = AdventurerUtils.getCoordinatesAfterMovement(adventurer);

            if(MapUtils.isCoordinatesInMap(map, coordAfterMovement) && !MapUtils.isSquareMountainOrTaken(map, coordAfterMovement)){
                adventurer.setCoordinates(coordAfterMovement);
                System.out.println(adventurer.getCoordinates().getCoordinateX());
                System.out.println(adventurer.getCoordinates().getCoordinateY());
                map.getSquare(coordAfterMovement).isTaken();
                System.out.println("case valid to move");
                if(MapUtils.isTreasureSquare(map, coordAfterMovement)){
                    System.out.println("treasure found");
                    adventurer.getTreasure();
                    MapUtils.removeTreasure(map, coordAfterMovement);
                }
            } else {
                System.out.println("case not valid to move");
                //throw new IncorrectMovementException("Mouvement non autorise et ignore");
            }

        } else {
            adventurer.turnDirection(movement);
        }
        adventurer.removeNextMovement();
    }
}

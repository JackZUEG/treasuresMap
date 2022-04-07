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
                    try{
                        playMovement(map, adventurer);
                    } catch(IncorrectMovementException e){
                        System.out.println(e.getMessage());
                        break;
                    }
                }
            }
            if(MapUtils.isAllAdventurersPlayed(map)){
                continueToPlay = false;
            }
        }
    }

    private static void playMovement(Map map, Adventurer adventurer) throws ElementOutOfTheMapException, IncorrectMovementException {
        Movement movement = adventurer.getNextMovement();

        if(movement.equals(Movement.FORWARD)){
            Coordinates coordAfterMovement = AdventurerUtils.getCoordinatesAfterMovement(adventurer);

            if(MapUtils.isCoordinatesInMap(map, coordAfterMovement) && !MapUtils.isSquareMountainOrTaken(map, coordAfterMovement)){
                map.getSquare(adventurer.getCoordinates()).setTaken(false);

                adventurer.setCoordinates(coordAfterMovement);
                map.getSquare(coordAfterMovement).setTaken(true);

                if(MapUtils.isTreasureSquare(map, coordAfterMovement)){
                    adventurer.getTreasure();
                    MapUtils.removeTreasure(map, coordAfterMovement);
                }
            } else {
                adventurer.removeNextMovement();
                throw new IncorrectMovementException("Un mouvement du joueur "+adventurer.getName()+" non autorise a ete ignore");
            }

        } else {
            adventurer.turnDirection(movement);
        }
        adventurer.removeNextMovement();
    }
}

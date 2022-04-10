package commons;

import exception.IncorrectDirectionException;
import exception.IncorrectMovementException;
import exception.MessagesException;
import model.adventurer.Adventurer;
import model.adventurer.Movement;
import model.coordinates.Coordinates;
import model.map.Map;
import utils.AdventurerUtils;
import utils.MapUtils;

import java.util.List;

/**
 * Classe abstraite facilitant la lecture de la carte
 */
public abstract class MapPlayer {

    /**
     * Methode permettant de faire jouer les aventuriers
     * @param map carte qui doit etre jouee
     */
    public static void playTreasureMap(Map map) {

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

    /**
     * Methode permettant de jouer le mouvement d'un aventurier
     * @param map carte qui doit etre jouee
     * @param adventurer aventurier qui joue
     * @exception IncorrectMovementException mouvement incorrect d'un aventurier
     */
    private static void playMovement(Map map, Adventurer adventurer) throws IncorrectMovementException {
        Movement movement = adventurer.getNextMovement();

        if(movement.equals(Movement.FORWARD)){
            Coordinates coordAfterMovement = AdventurerUtils.getCoordinatesAfterMovement(adventurer);

            if(MapUtils.isCoordinatesInMap(map, coordAfterMovement) && MapUtils.isSquareValidToMove(map, coordAfterMovement)){
                map.getSquare(adventurer.getCoordinates()).setTaken(false);

                adventurer.setCoordinates(coordAfterMovement);
                map.getSquare(coordAfterMovement).setTaken(true);

                if(MapUtils.isTreasureSquare(map, coordAfterMovement)){
                    adventurer.getTreasure();
                    MapUtils.removeTreasure(map, coordAfterMovement);
                }
            } else {
                adventurer.removeNextMovement();
                throw new IncorrectMovementException(MessagesException.IncorrectMovementException.getMsg());
            }

        } else {
            adventurer.turnDirection(movement);
        }
        adventurer.removeNextMovement();
    }
}

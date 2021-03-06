package utils;

import exception.IncorrectDirectionException;
import exception.IncorrectMovementException;
import exception.MessagesException;
import model.adventurer.Adventurer;
import model.adventurer.Direction;
import model.adventurer.Movement;
import model.coordinates.Coordinates;

/**
 * Classe abstraite de methodes liees a un aventurier
 */
public abstract class AdventurerUtils {

    /**
     * Methode permettant de recuperer le mouvement a partir d'une lettre
     * @param movement mouvement a retrouver
     * @return Movement
     * @exception IncorrectMovementException mouvement incorrect
     */
    public static Movement getMovement(char movement) throws IncorrectMovementException {
        switch(movement){
            case 'A':
                return Movement.FORWARD;
            case 'G':
                return Movement.TURNLEFT;
            case 'D':
                return Movement.TURNRIGHT;
            default:
                throw new IncorrectMovementException(MessagesException.IncorrectMovementException.getMsg());
        }
    }

    /**
     * Methode permettant de recuperer la direction a partir d'une lettre
     * @param direction direction a retrouver
     * @return Direction
     * @exception IncorrectDirectionException direction incorrecte
     */
    public static Direction getDirection(String direction) throws IncorrectDirectionException {
        switch(direction){
            case "N":
                return Direction.NORTH;
            case "S":
                return Direction.SOUTH;
            case "O":
                return Direction.WEST;
            case "E":
                return Direction.EAST;
            default:
                throw new IncorrectDirectionException(MessagesException.IncorrectDirectionException.getMsg());
        }
    }

    /**
     * Methode permettant de recuperer les coordonnees qu'aurait un aventurier apres avoir effectue son prochain mouvement
     * @param adventurer aventurier qui doit changer de case
     * @return Coordinates
     */
    public static Coordinates getCoordinatesAfterMovement(Adventurer adventurer){
        Coordinates coordinates = new Coordinates(adventurer.getCoordinates());
        if(adventurer.getNextMovement() == Movement.FORWARD){
            switch(adventurer.getDirection()){
                case NORTH:
                    coordinates.setCoordinateY(coordinates.getCoordinateY() - 1);
                    break;
                case SOUTH:
                    coordinates.setCoordinateY(coordinates.getCoordinateY() + 1);
                    break;
                case WEST:
                    coordinates.setCoordinateX(coordinates.getCoordinateX() - 1);
                    break;
                case EAST:
                    coordinates.setCoordinateX(coordinates.getCoordinateX() + 1);
                    break;
            }
        }
        return coordinates;
    }
}

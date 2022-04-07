package utils;

import exception.IncorrectDirectionException;
import exception.IncorrectMovementException;
import model.adventurer.Adventurer;
import model.adventurer.Direction;
import model.adventurer.Movement;
import model.coordinates.Coordinates;

public abstract class AdventurerUtils {

    public static Movement getMovement(char movement) throws IncorrectMovementException {
        switch(movement){
            case 'A':
                return Movement.FORWARD;
            case 'G':
                return Movement.TURNLEFT;
            case 'D':
                return Movement.TURNRIGHT;
            default:
                throw new IncorrectMovementException("Le mouvement est incorrect");
        }
    }

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
                throw new IncorrectDirectionException("La direction est incorrecte");
        }
    }


    public static Coordinates getCoordinatesAfterMovement(Adventurer adventurer){
        Coordinates coordinates = adventurer.getCoordinates();
        if(adventurer.getNextMovement() == Movement.FORWARD){
            switch(adventurer.getDirection()){
                case NORTH:
                    coordinates.setCoordinateX(coordinates.getCoordinateX() - 1);
                case SOUTH:
                    coordinates.setCoordinateX(coordinates.getCoordinateX() + 1);
                case WEST:
                    coordinates.setCoordinateX(coordinates.getCoordinateY() - 1);
                case EAST:
                    coordinates.setCoordinateX(coordinates.getCoordinateY() + 1);
            }
        }
        return coordinates;
    }
}

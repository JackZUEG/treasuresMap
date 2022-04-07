package utils;

import exception.ElementOutOfTheMapException;
import model.adventurer.Adventurer;
import model.coordinates.Coordinates;
import model.map.Map;
import model.square.Square;
import model.square.SquareTreasure;
import model.square.SquareType;

public abstract class MapUtils {

    public static boolean isCoordinatesInMap(Map map, Coordinates coordinates){
        Coordinates coordinatesMap = map.getCoordinates();
        if(coordinatesMap.getCoordinateX() > coordinates.getCoordinateX()
                && coordinatesMap.getCoordinateY() > coordinates.getCoordinateY()
                && 0 <= coordinates.getCoordinateX()
                && 0 <= coordinates.getCoordinateY()){
            return true;
        } else {
            return false;
        }
    }

    public static boolean isSquareMountainOrTaken(Map map, Coordinates coordinates) throws ElementOutOfTheMapException {
        Square square = map.getSquare(coordinates);
        if(square.getSquareType() == SquareType.MOUNTAIN || square.isTaken()){
            return true;
        } else{
            return false;
        }
    }

    public static boolean isTreasureSquare(Map map, Coordinates coordinates) throws ElementOutOfTheMapException {
        Square square = map.getSquare(coordinates);
        if(square.getSquareType() == SquareType.TREASURE){
            return true;
        } else{
            return false;
        }
    }

    public static Map removeTreasure(Map map, Coordinates coordinates) throws ElementOutOfTheMapException {
        SquareTreasure square = (SquareTreasure) map.getSquare(coordinates);
        square.removeTreasure();
        return map;
    }

    public static boolean isAllAdventurersPlayed(Map map){
        for(Adventurer adventurer: map.getListAdventurers()){
            if(!adventurer.isOutOfMovements()){
                return false;
            }
        }
        return true;
    }
}

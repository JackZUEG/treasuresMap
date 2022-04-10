package utils;

import model.adventurer.Adventurer;
import model.coordinates.Coordinates;
import model.map.Map;
import model.square.Square;
import model.square.SquareTreasure;
import model.square.SquareType;

/**
 * Classe abstraite de methodes liees a une carte
 */
public abstract class MapUtils {

    /**
     * Methode permettant de verifier que les coordonnees sont dans la carte
     * @param map carte a verifier
     * @param coordinates coordonnees a verifier
     * @return boolean vrai si les coordonnees sont dans la carte, faux sinon
     */
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

    /**
     * Methode permettant de verifier que la case est visitable (ni montagne ni occupee)
     * @param map carte a verifier
     * @param coordinates coordonnees a verifier
     * @return boolean vrai si les coordonnees sont valides, faux sinon
     */
    public static boolean isSquareValidToMove(Map map, Coordinates coordinates) {
        Square square = map.getSquare(coordinates);
        if(square != null){
            if(square.getSquareType() == SquareType.MOUNTAIN || square.isTaken()){
                return false;
            } else{
                return true;
            }
        }
        return false;
    }

    /**
     * Methode permettant de verifier que la case est un tresor
     * @param map carte a verifier
     * @param coordinates coordonnees a verifier
     * @return boolean vrai si la case est un tresor, faux sinon
     */
    public static boolean isTreasureSquare(Map map, Coordinates coordinates) {
        Square square = map.getSquare(coordinates);
        if(square.getSquareType() == SquareType.TREASURE){
            return true;
        } else{
            return false;
        }
    }

    /**
     * Methode permettant d'enlever un tresor d'une case
     * @param map carte a modifier
     * @param coordinates coordonnees du tresor
     * @return Map avec le tresor en moins
     */
    public static Map removeTreasure(Map map, Coordinates coordinates) {
        SquareTreasure square = (SquareTreasure) map.getSquare(coordinates);
        square.removeTreasure();
        return map;
    }

    /**
     * Methode permettant de verifier que tous les aventuriers ont jouer
     * @param map carte a verifier
     * @return Boolean vrai si tout le monde a joue, faux sinon
     */
    public static boolean isAllAdventurersPlayed(Map map){
        for(Adventurer adventurer: map.getListAdventurers()){
            if(!adventurer.isOutOfMovements()){
                return false;
            }
        }
        return true;
    }
}
